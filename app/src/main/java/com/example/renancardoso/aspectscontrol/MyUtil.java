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

    public static void generateFiveAspects() {

        Realm realm = Realm.getDefaultInstance();


        for (int i = 0; i <= 5; i++) {

            Grades grade1 = new Grades();
            grade1.setGrade((int)(Math.random() * 11));
            grade1.setCreatedAt(new Date());

            Grades grade2 = new Grades();
            grade2.setGrade((int)(Math.random() * 11));
            grade2.setCreatedAt(new Date());

            Grades grade3 = new Grades();
            grade3.setGrade((int)(Math.random() * 11));
            grade3.setCreatedAt(new Date());

            Aspects aspect = new Aspects();

            aspect.setName("Aspect " + i);
            aspect.setGrades(new RealmList<Grades>());
            aspect.getGrades().add(grade1);
            aspect.getGrades().add(grade2);
            aspect.getGrades().add(grade3);

            realm.beginTransaction();
            realm.copyToRealm(aspect);
            realm.commitTransaction();
        }

    }
}
