package com.example.renancardoso.aspectscontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        Button addAspect = (Button) findViewById(R.id.bt_add_aspect);
        final EditText newAspect = (EditText) findViewById(R.id.et_new_aspect_name);
        final EditText newTag = (EditText) findViewById(R.id.et_new_aspect_tag);


        addAspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aspectName = newAspect.getText().toString().trim();
                String aspectTag = newTag.getText().toString().trim();
                boolean err = false;

                if (aspectName.isEmpty()) {
                    newAspect.setError("Aspect name can't be blank");
                    err = true;
                }

                if (aspectTag.isEmpty()) {
                    newTag.setError("Aspect tag can't be blank");
                    err = true;
                }

                if (err) return;

                Aspects aspect = new Aspects();
                aspect.setName(aspectName);
                aspect.setTag(aspectTag);
                aspect.setCreatedAt(new Date());

                Realm realm = Realm.getDefaultInstance();
                try {
                    realm.beginTransaction();
                    realm.copyToRealm(aspect);
                    realm.commitTransaction();

                    MyUtil.toast(NewAspect.this, aspect.getName() + " save whit success!");
                } finally {
                    realm.close();
                    //finish();
                }
            }
        });
    }

}
