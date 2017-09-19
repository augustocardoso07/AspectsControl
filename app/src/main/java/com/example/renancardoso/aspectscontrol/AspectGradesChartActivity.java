package com.example.renancardoso.aspectscontrol;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.realm.implementation.RealmBarDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AspectGradesChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_grades_chart);

        String aspectID = getIntent().getExtras().getString("aspectId");

        BarChart chart = (BarChart) findViewById(R.id.bc_aspect_chart);
        Realm realm = Realm.getDefaultInstance();
        final Aspects aspect = realm.where(Aspects.class).equalTo("name", aspectID).findFirst();

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
                String date = sdf.format(aspect.getGrades().get((int) value).getCreatedAt());
                return date;
            }
        };

        List<BarEntry> entries = new ArrayList<>();
        float i = 0;
        for (Grades grade: aspect.getGrades()) {
            entries.add(new BarEntry( i++ ,grade.getGrade()));
        }
        BarDataSet set = new BarDataSet(entries, "Grades");
        BarData data = new BarData(set);

        chart.setData(data);

        chart.setFitBars(true);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.getAxisLeft().setAxisMaximum(11);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisRight().setAxisMaximum(11);
        chart.getAxisRight().setAxisMinimum(0);
        chart.getXAxis().setValueFormatter(formatter);
        chart.getXAxis().setGranularity(1f);
        chart.invalidate();

    }
}
