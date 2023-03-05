package com.Audisankara.asit;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TotalAttendance extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerViewAdaptor;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private ArrayList<DataClass> dataClassArrayList;
    private ArrayList<String> RollNumbers;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);
        recyclerView = findViewById(R.id.AttendanceListRecyclerView);
        swipeRefreshLayout = findViewById(R.id.refreshlyt);
        RetriveDataFromFireBase();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.CustomRed));
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RetriveDataFromFireBase();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    private void RetriveDataFromFireBase() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("cse");
        dataClassArrayList = new ArrayList<>();
        RollNumbers = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    RollNumbers.add(String.valueOf(ds.getKey()));
                }

                for(int i =0 ; i< RollNumbers.size() ;i++) {
                    dataClassArrayList.add(new DataClass(RollNumbers.get(i)));
                    recyclerViewAdaptor = new RecyclerView(dataClassArrayList,TotalAttendance.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(recyclerViewAdaptor);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TotalAttendance.this));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private class DataClass {
        private String RollNumber;

        public DataClass(String rollNumber) {
            RollNumber = rollNumber;
        }

        public String getRollNumber() {
            return RollNumber;
        }

        public void setRollNumber(String rollNumber) {
            RollNumber = rollNumber;
        }
    }
    class RecyclerView extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView.viewholder> {
        private ArrayList<DataClass> dataClassArrayList;
        private Context context;
        public RecyclerView(ArrayList<DataClass> dataClassArrayList,Context context) {
            this.dataClassArrayList = dataClassArrayList;
            this.context = context;
        }
        @NonNull
        @Override
        public RecyclerView.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.faculty_attendance_model,parent,false);
            return new viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.viewholder holder, int position) {
            DataClass model = dataClassArrayList.get(position);
            holder.tvRollNumber.setText(model.getRollNumber());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(TotalAttendance.this,GiveAttendanceFor15Days.class);
                    i.putExtra("RollNumber",model.getRollNumber());
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataClassArrayList.size();
        }

        class viewholder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

            private TextView tvRollNumber;
            public viewholder(@NonNull View itemView) {
                super(itemView);
                tvRollNumber = itemView.findViewById(R.id.StudentRollNumber);
            }
        }
    }

}
