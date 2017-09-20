package com.example.renancardoso.aspectscontrol;

import android.content.Context;
import android.widget.Toast;

import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.Models.Grades;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by RenanCardoso on 13/09/2017.
 */

public class MyUtil {

    public static void toast(Context c, CharSequence t) {
        Toast.makeText(c, t, Toast.LENGTH_SHORT).show();
    }

    public static void generateAspects(int nUsers, int nGrades) {

        Realm realm = Realm.getDefaultInstance();
        if (realm.where(Aspects.class).findAll().size() >= 1) return;

        for (int i = 0; i <= nUsers; i++) {

            Aspects aspect = new Aspects();

            aspect.setName("Aspect " + i);
            aspect.setGrades(new RealmList<Grades>());

            for (int ii = 0; ii <= nGrades; ii++)  {
                Grades grade = new Grades();
                grade.setGrade((int)(Math.random() * 11));
                grade.setCreatedAt(new Date());

                aspect.getGrades().add(grade);
            }

            try {
                realm.beginTransaction();
                realm.copyToRealm(aspect);
                realm.commitTransaction();
            } finally {

            }
        }

    }
}
