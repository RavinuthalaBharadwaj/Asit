package com.Audisankara.asit;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class GiveAttendanceFor15Days extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText etHeld,etAttended,etPercentage;
    private CardView cvSubmit;
    private TextView tvRollNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_attendance_for15_days);
        String RollNumber = getIntent().getStringExtra("RollNumber");
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("TOTAL_ATTENDANCE").child(RollNumber);
        Toast.makeText(this, getIntent().getStringExtra("RollNumber"), Toast.LENGTH_SHORT).show();
        etHeld = findViewById(R.id.etHeld);
        etAttended = findViewById(R.id.etAttended);
        etPercentage = findViewById(R.id.etPercentage);
        cvSubmit = findViewById(R.id.cvSubmit);
        tvRollNumber = findViewById(R.id.tvRollNumber);
        tvRollNumber.setText(RollNumber);
        cvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etHeld.getText().toString().isEmpty() && !etAttended.getText().toString().isEmpty() && !etPercentage.getText().toString().isEmpty()) {
                    RegisterDatatoFireBase();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.CustomPurple));
        }
    }

    private void RegisterDatatoFireBase() {
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("Attended",etAttended.getText().toString());
        dataMap.put("Held",etHeld.getText().toString());
        dataMap.put("Percentage",etPercentage.getText().toString());
        int Skipped = (Integer.parseInt(etHeld.getText().toString()) - Integer.parseInt(etAttended.getText().toString()));
        dataMap.put("Skipped",String.valueOf(Skipped));
        databaseReference.setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(GiveAttendanceFor15Days.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}