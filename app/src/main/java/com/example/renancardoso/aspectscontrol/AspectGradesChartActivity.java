package com.example.renancardoso.aspectscontrol;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.Models.Grades;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.realm.implementation.RealmBarDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AspectGradesChartActivity extends AppCompatActivity {


    Realm realm;
    BarChart barChart;
    LineChart lineChart;
    String aspectID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_grades_chart);

        aspectID = getIntent().getExtras().getString("aspectId");

        barChart = (BarChart) findViewById(R.id.bc_aspect_chart);
        lineChart = (LineChart) findViewById(R.id.lc_aspect_chart);
        realm = Realm.getDefaultInstance();

        final Aspects aspect = realm.where(Aspects.class).equalTo("name", aspectID).findFirst();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String date = sdf.format(aspect.getGrades().get((int) value).getCreatedAt());
                return date;
            }
        };

        drawBarChart(aspect, formatter);
        //drawLineChart(aspect, formatter);

    }

    private void drawLineChart(Aspects aspect, IAxisValueFormatter formatter) {
        lineChart.setViewPortOffsets(0, 0, 0, 0);
        lineChart.setBackgroundColor(Color.rgb(104, 241, 175));

        // no description text
        lineChart.getDescription().setEnabled(false);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300);

        XAxis x = lineChart.getXAxis();
        x.setEnabled(false);
        x.setValueFormatter(formatter);

        YAxis y = lineChart.getAxisLeft();

        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        lineChart.getAxisRight().setEnabled(false);
        
        setData(aspect);

        lineChart.getLegend().setEnabled(false);

        lineChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        lineChart.invalidate();


    }

    private void setData(Aspects aspect) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int i = 0;
        for (Grades grade: aspect.getGrades()) {
            yVals.add(new Entry(i++, grade.getGrade()));
        }

        LineDataSet set1;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)lineChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "Grades");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);

            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            lineChart.setData(data);
        }
    }

    private void drawBarChart(Aspects aspect, IAxisValueFormatter formatter) {
        lineChart.setVisibility(View.INVISIBLE);
        List<BarEntry> entries = new ArrayList<>();
        float i = 0;
        for (Grades grade: aspect.getGrades()) {
            entries.add(new BarEntry( i++ ,grade.getGrade()));
        }
        BarDataSet set = new BarDataSet(entries, "Grades");

        BarData data = new BarData(set);

        //data.setBarWidth(0.80f); // important
        barChart.setData(data);
        //chart.setScaleMinima(2f, 1f); // important

        barChart.setFitBars(true);
        barChart.setScaleYEnabled(false);

        barChart.setDrawGridBackground(false);
        barChart.getAxisLeft().setAxisMaximum(11);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMaximum(11);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getXAxis().setValueFormatter(formatter);
        barChart.getXAxis().setGranularity(1f);
        barChart.setFitBars(true);

        barChart.animateXY(2000, 2000);
        barChart.invalidate();
    }
}
