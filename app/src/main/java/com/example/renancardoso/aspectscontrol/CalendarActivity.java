package com.example.renancardoso.aspectscontrol;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.example.renancardoso.aspectscontrol.Models.RoutineDates;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Realm realm = Realm.getDefaultInstance();

        final RealmResults<RoutineDates> routineDates = realm.where(RoutineDates.class).findAll();

        ArrayList<CalendarDay> oldCollection = new ArrayList<>();

        for (RoutineDates d: routineDates) {
            oldCollection.add(CalendarDay.from(d.getDate()));
        }

        final ArrayList<CalendarDay> days = oldCollection;

        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.mcv_aspects_routine);
        DayViewDecorator decorator = new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return days.contains(day);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new DotSpan(5,0));
            }
        };
        mcv.addDecorator(decorator);
        //mcv.selectRange(CalendarDay.from(2017,9,19), CalendarDay.from(2017,9,29));
//        mcv.setDateSelected(CalendarDay.from(2017,9,18), true);
//        mcv.setDateSelected(CalendarDay.from(2017,9,20), true);
//        mcv.setDateSelected(CalendarDay.from(2017,9,22), true);
//        mcv.setDateSelected(CalendarDay.from(2017,9,24), true);
//        mcv.setDateSelected(CalendarDay.from(2017,9,25), true);
//        mcv.setDateSelected(CalendarDay.from(2017,9,27), true);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 9, 18))
                .commit();
    }
}
