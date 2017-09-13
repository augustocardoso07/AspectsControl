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

    public static void testingSave() {
        Grades grade1 = new Grades();

        grade1.setGrade(100);
        grade1.setTag("Planing");
        grade1.setCreatedAt(new Date());

        Grades grade2 = new Grades();

        grade2.setGrade(50);
        grade2.setTag("Planing");
        grade2.setCreatedAt(new Date());

        Grades grade3 = new Grades();
        grade3.setGrade(100);
        grade3.setTag("Health");
        grade3.setCreatedAt(new Date());

        Aspects memory = new Aspects();

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
}
