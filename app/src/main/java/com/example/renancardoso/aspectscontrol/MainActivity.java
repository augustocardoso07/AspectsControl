package com.example.renancardoso.aspectscontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import com.example.renancardoso.aspectscontrol.Adapters.AspectsAdapter;
import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.facebook.stetho.Stetho;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    AspectsAdapter adapter;
    ListView allAspects;
    RealmResults<Aspects> aspectsFromRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.menu_add_aspect);
        FloatingActionButton fabStartRoutine = (FloatingActionButton) findViewById(R.id.menu_start_notes_routine);
        FloatingActionButton fabCalendar = (FloatingActionButton) findViewById(R.id.menu_calendar);

        fabCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NewAspect.class), 0);
            }
        });

        fabStartRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAspectForGrade(0);
            }
        });

        realm = Realm.getDefaultInstance();
        //MyUtil.generateFiveAspects();
        allAspects = (ListView) findViewById(R.id.lt_aspects);
        aspectsFromRealm = realm.where(Aspects.class).findAll();
        adapter = new AspectsAdapter(this, aspectsFromRealm);
        allAspects.setAdapter(adapter);

        allAspects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, AspectGradesChartActivity.class);
                TextView aspectId = (TextView) view.findViewById(R.id.tv_aspect_item_name);
                intent.putExtra("aspectId", aspectId.getText().toString());
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    private void showAspectForGrade(final int position) {
        if (position >= aspectsFromRealm.size()) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Aspects aspect = aspectsFromRealm.get(position);
        builder.setTitle("Qual a sua nota para: " + aspect.getName());
        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.grade_setter, null);
        builder.setView(v);

        final NumberPicker np = (NumberPicker) v.findViewById(R.id.np_aspect_grade);
        np.setMinValue(0);
        np.setMaxValue(10);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyUtil.toast(MainActivity.this, "" + np.getValue());
                Grades grade = new Grades();
                grade.setGrade(np.getValue());
                grade.setCreatedAt(new Date());

                realm.beginTransaction();
                aspect.getGrades().add(grade);
                realm.commitTransaction();
                showAspectForGrade(position + 1);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {

            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
            Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
