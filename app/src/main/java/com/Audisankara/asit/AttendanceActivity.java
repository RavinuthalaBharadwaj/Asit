package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class AttendanceActivity extends AppCompatActivity {

    private PieChart piechart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        piechart = findViewById(R.id.piechart);
        piechart.addPieSlice(new PieModel("present",Integer.parseInt("20"), Color.parseColor("#6A5EEA")));
        piechart.startAnimation();
    }
}