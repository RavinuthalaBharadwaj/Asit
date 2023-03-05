package com.Audisankara.asit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FacultyHome extends AppCompatActivity {

    private CardView Attendance,For15Days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        Attendance = findViewById(R.id.cvAttendance);
        For15Days = findViewById(R.id.cv15DaysAttendance);
        Attendance
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyHome.this, FacultyAttendance.class));
            }
        });
        For15Days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyHome.this,TotalAttendance.class));
            }
        });
    }
}