package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GiveAttendance extends AppCompatActivity {

    private EditText etPresent;
    private EditText etAbsent;
    private CardView cvSubmit;
    private TextView RollNumber;

    private DatabaseReference databaseReference,instance;
    private FirebaseDatabase firebaseDatabase;
    private static dataclass dataclass;
    private String RollNumberFromIntent;
    private ArrayList<String> YesterdayData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_attendance);

        Bundle bundle = getIntent().getExtras();
        etAbsent = findViewById(R.id.etAbsent);
        etPresent = findViewById(R.id.etPresent);
        cvSubmit = findViewById(R.id.cvSubmit);
        RollNumber = findViewById(R.id.tvRollNumber);

        //things better not to touch
        RollNumberFromIntent = getIntent().getStringExtra("RollNumber");
        assert RollNumberFromIntent!= null;
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();


        //get yesterday data first
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK,-1);
        Date date = calendar.getTime();
        String YesterdayDate = sdf.format(date).concat(String.valueOf(date.getDate()));
        System.out.println(sdf.format(date));
        databaseReference = firebaseDatabase.getReference().child("CSE_B_ATTENDANCE").child(RollNumberFromIntent).child(YesterdayDate);
        YesterdayData = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    YesterdayData.add(String.valueOf(ds.getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        calendar.add(Calendar.DAY_OF_WEEK,1);
        date = calendar.getTime();
        String TodayDate = sdf.format(date).concat(String.valueOf(date.getDate()));
        databaseReference = firebaseDatabase.getReference().child("CSE_B_ATTENDANCE").child(RollNumberFromIntent).child(TodayDate);
        System.out.println("today dat : "+TodayDate);
        RollNumber.setText(RollNumberFromIntent);

        cvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> map = new HashMap<>();
                try {
                    System.out.println("data value : " + YesterdayData.get(0));
                    int AbsentsAddBeforePush = (Integer.parseInt(YesterdayData.get(0)) + Integer.parseInt(etAbsent.getText().toString()));
                    System.out.println("ab"+AbsentsAddBeforePush);
                    int PresentsAddBeforePush = (Integer.parseInt(YesterdayData.get(1)) + Integer.parseInt(etPresent.getText().toString()));
                    System.out.println("pre"+PresentsAddBeforePush);
                    map.put("absent", String.valueOf(AbsentsAddBeforePush));
                    map.put("present", String.valueOf(PresentsAddBeforePush));
                    map.put("zpresent",etPresent.getText().toString());
                }catch(Exception e) {
                    int AbsentsAddBeforePush = Integer.parseInt(etAbsent.getText().toString());
                    int PresentsAddBeforePush = Integer.parseInt(etPresent.getText().toString());
                    map.put("absent", String.valueOf(AbsentsAddBeforePush));
                    map.put("present", String.valueOf(PresentsAddBeforePush));
                    map.put("zpresent",etPresent.getText().toString());
                }
               // GiveAttendance.dataclass = new dataclass(String.valueOf(),etAbsent.getText().toString());
                databaseReference.setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(GiveAttendance.this, "Updated", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(GiveAttendance.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }


    private class dataclass {
        private String yAbsent;
        private String xPresent;

        public dataclass(String xPresent, String yAbsent) {
            this.xPresent=xPresent;
            this.yAbsent = yAbsent;
        }


        public String getPresent() {
            return xPresent;
        }

        public void setPresent(String xpresent) {
            xPresent = xpresent;
        }

        public String getAbsent() {
            return yAbsent;
        }

        public void setAbsent(String yabsent) {
            this.yAbsent = yabsent;
        }


    }

}