package com.example.renancardoso.aspectscontrol;

import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.menu_add_aspect);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"fab add clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Realm.init(this);
        testingSave();

    }

    private void testingSave() {
        Grades grade1 = new Grades();
        grade1.setId(4);
        grade1.setGrade(100);
        grade1.setTag("Planing");
        grade1.setCreatedAt(new Date());

        Grades grade2 = new Grades();
        grade2.setId(5);
        grade2.setGrade(50);
        grade2.setTag("Planing");
        grade2.setCreatedAt(new Date());

        Grades grade3 = new Grades();
        grade3.setGrade(100);
        grade3.setTag("Health");
        grade3.setCreatedAt(new Date());

        Aspects memory = new Aspects();
        memory.setId(1);
        memory.setName("Memory");
        memory.setStatus(2);
        memory.setGrades(new RealmList<Grades>());
        memory.getGrades().add(grade1);
        memory.getGrades().add(grade2);

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(memory);

        realm.commitTransaction();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
