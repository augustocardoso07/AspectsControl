package com.example.renancardoso.aspectscontrol;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Realm realm = Realm.getDefaultInstance();

        //RealmResults<Grades> results = realm.where(Grades.class).fi

        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.mcv_aspects_routine);
        DayViewDecorator decorator = new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                if (day.equals(CalendarDay.from(2017,9,19))) return true;
                if (day.equals(CalendarDay.from(2017,9,21))) return true;
                if (day.equals(CalendarDay.from(2017,9,23))) return true;
                if (day.equals(CalendarDay.from(2017,9,25))) return true;
                if (day.equals(CalendarDay.from(2017,9,27))) return true;
                return false;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new DotSpan(5,0));
            }
        };
        mcv.addDecorator(decorator);
        //mcv.selectRange(CalendarDay.from(2017,9,19), CalendarDay.from(2017,9,29));
        mcv.setDateSelected(CalendarDay.from(2017,9,18), true);
        mcv.setDateSelected(CalendarDay.from(2017,9,20), true);
        mcv.setDateSelected(CalendarDay.from(2017,9,22), true);
        mcv.setDateSelected(CalendarDay.from(2017,9,24), true);
        mcv.setDateSelected(CalendarDay.from(2017,9,25), true);
        mcv.setDateSelected(CalendarDay.from(2017,9,27), true);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 9, 18))
                .commit();
    }
}
