package com.example.renancardoso.aspectscontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renancardoso.aspectscontrol.Models.Aspects;

import java.util.Date;

import io.realm.Realm;

public class NewAspect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_aspect);

        Button addAspect = (Button) findViewById(R.id.add_aspect);
        final EditText newAspect = (EditText) findViewById(R.id.new_aspect_name);


        addAspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Aspects aspect = new Aspects();
                aspect.setName(newAspect.getText().toString());
                aspect.setCreatedAt(new Date());

                Realm realm = Realm.getDefaultInstance();
                try {
                    realm.beginTransaction();
                    realm.copyToRealm(aspect);
                    realm.commitTransaction();

                    toast(aspect.getName() + " save whit success!");
                } finally {
                    realm.close();
                    finish();
                }
            }
        });
    }

    private void toast(CharSequence text) {
        Toast.makeText(NewAspect.this, text, Toast.LENGTH_SHORT).show();
    }
}
