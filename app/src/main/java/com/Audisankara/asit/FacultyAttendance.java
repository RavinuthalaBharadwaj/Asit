package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Audisankara.asit.GiveAttendance;
import com.Audisankara.asit.R;
import com.Audisankara.asit.helper.AttendanceViewInterface;
import com.Audisankara.asit.helper.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FacultyAttendance extends AppCompatActivity implements AttendanceViewInterface {

    private RecyclerView recyclerView;
    private FacultyAttendanceAdaptor facultyAttendanceAdaptor;
    private Activity activity;
    private SwipeRefreshLayout refreshLayout;
    ArrayList<String> RollNumbers = new ArrayList<>();
    private DatabaseReference databaseReference,x;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<FacultyAttendanceModel> facultyAttendanceModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_attendance);
        activity = FacultyAttendance.this;
        facultyAttendanceModelArrayList = new ArrayList<>();
        refreshLayout = findViewById(R.id.refreshlyt);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("cse");
        recyclerView = findViewById(R.id.AttendanceListRecyclerView);
        FireBaseOperations();
        recyclerView
                .setLayoutManager(new LinearLayoutManager(activity));
        recyclerView
                .setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FireBaseOperations();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    public void FireBaseOperations() {


        databaseReference
                .orderByChild("cse")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                            RollNumbers.add(ds.getKey());
                        }

                        for(int i=0;i<RollNumbers.size();i++) {
                            facultyAttendanceModelArrayList.add(new FacultyAttendanceModel(RollNumbers.get(i)));
                            facultyAttendanceAdaptor = new FacultyAttendanceAdaptor(activity,facultyAttendanceModelArrayList,FacultyAttendance.this);
                            recyclerView
                                    .setAdapter(facultyAttendanceAdaptor);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public void onClick(int position, Context context,TextView textView) {

        Bundle bundle = new Bundle();
        Intent i = new Intent(FacultyAttendance.this, GiveAttendance.class);
        switch (textView.getText().toString()) {
            case "202H1A0578" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0578");
                i.putExtras(bundle);
                break;
            case "202H1A0579" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0579");
                i.putExtras(bundle);
                break;
            case "202H1A0580" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0580");
                i.putExtras(bundle);
                break;
            case "202H1A0581" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0581");
                i.putExtras(bundle);
                break;
            case "202H1A0582" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0582");
                i.putExtras(bundle);
                break;
            case "202H1A0583" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0583");
                i.putExtras(bundle);
                break;
            case "202H1A0584" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0584");
                i.putExtras(bundle);
                break;
            case "202H1A0585" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0585");
                i.putExtras(bundle);
                break;
            case "202H1A0586" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0586");
                i.putExtras(bundle);
                break;
            case "202H1A0587" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0587");
                i.putExtras(bundle);
                break;
            case "202H1A0588" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0588");
                i.putExtras(bundle);
                break;
            case "202H1A0589" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0589");
                i.putExtras(bundle);
                break;
            case "202H1A0590" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0590");
                i.putExtras(bundle);
                break;
            case "202H1A0591" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0591");
                i.putExtras(bundle);
                break;
            case "202H1A0592" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0592");
                i.putExtras(bundle);
                break;
            case "202H1A0593" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0593");
                i.putExtras(bundle);
                break;
            case "202H1A0594" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0594");
                i.putExtras(bundle);
                break;
            case "202H1A0595" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0595");
                i.putExtras(bundle);
                break;
            case "202H1A0596" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0596");
                i.putExtras(bundle);
                break;
            case "202H1A0597" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0597");
                i.putExtras(bundle);
                break;
            case "202H1A0598" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0598");
                i.putExtras(bundle);
                break;
            case "202H1A0599" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A0599");
                i.putExtras(bundle);
                break;
            case "202H1A05A0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A0");
                i.putExtras(bundle);
                break;
            case "202H1A05A1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A1");
                i.putExtras(bundle);
                break;
            case "202H1A05A2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A2");
                i.putExtras(bundle);
                break;
            case "202H1A05A3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A3");
                i.putExtras(bundle);
                break;
            case "202H1A05A4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A4");
                i.putExtras(bundle);
                break;
            case "202H1A05A5" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A5");
                i.putExtras(bundle);
                break;
            case "202H1A05A6" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A6");
                i.putExtras(bundle);
                break;
            case "202H1A05A7" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A7");
                i.putExtras(bundle);
                break;
            case "202H1A05A8" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A8");
                i.putExtras(bundle);
                break;
            case "202H1A05A9" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05A9");
                i.putExtras(bundle);
                break;
            case "202H1A05B0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B0");
                i.putExtras(bundle);
                break;
            case "202H1A05B1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B1");
                i.putExtras(bundle);
                break;
            case "202H1A05B2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B2");
                i.putExtras(bundle);
                break;
            case "202H1A05B3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B3");
                i.putExtras(bundle);
                break;
            case "202H1A05B4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B4");
                i.putExtras(bundle);
                break;
            case "202H1A05B5" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B5");
                i.putExtras(bundle);
                break;
            case "202H1A05B6" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B6");
                i.putExtras(bundle);
                break;
            case "202H1A05B7" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B7");
                i.putExtras(bundle);
                break;
            case "202H1A05B8" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B8");
                i.putExtras(bundle);
                break;
            case "202H1A05B9" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05B9");
                i.putExtras(bundle);
                break;
            case "202H1A05C0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C0");
                i.putExtras(bundle);
                break;
            case "202H1A05C1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C1");
                i.putExtras(bundle);
                break;
            case "202H1A05C2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C2");
                i.putExtras(bundle);
                break;
            case "202H1A05C3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C3");
                i.putExtras(bundle);
                break;
            case "202H1A05C4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C4");
                i.putExtras(bundle);
                break;
            case "202H1A05C5" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C5");
                i.putExtras(bundle);
                break;
            case "202H1A05C6" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C6");
                i.putExtras(bundle);
                break;
            case "202H1A05C7" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C7");
                i.putExtras(bundle);
                break;
            case "202H1A05C8" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C8");
                i.putExtras(bundle);
                break;
            case "202H1A05C9" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05C9");
                i.putExtras(bundle);
                break;
            case "202H1A05D0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D0");
                i.putExtras(bundle);
                break;
            case "202H1A05D1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D1");
                i.putExtras(bundle);
                break;
            case "202H1A05D2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D2");
                i.putExtras(bundle);
                break;
            case "202H1A05D3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D3");
                i.putExtras(bundle);
                break;
            case "202H1A05D4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D4");
                i.putExtras(bundle);
                break;
            case "202H1A05D5" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D5");
                i.putExtras(bundle);
                break;
            case "202H1A05D6" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D6");
                i.putExtras(bundle);
                break;
            case "202H1A05D7" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D7");
                i.putExtras(bundle);
                break;
            case "202H1A05D8" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D8");
                i.putExtras(bundle);
                break;
            case "202H1A05D9" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05D9");
                i.putExtras(bundle);
                break;
            case "202H1A05E0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E0");
                i.putExtras(bundle);
                break;
            case "202H1A05E1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E1");
                i.putExtras(bundle);
                break;
            case "202H1A05E2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E2");
                i.putExtras(bundle);
                break;
            case "202H1A05E3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E3");
                i.putExtras(bundle);
                break;
            case "202H1A05E4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E4");
                i.putExtras(bundle);
                break;
            case "202H1A05E5" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E5");
                i.putExtras(bundle);
                break;
            case "202H1A05E6" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E6");
                i.putExtras(bundle);
                break;
            case "202H1A05E7" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E7");
                i.putExtras(bundle);
                break;
            case "202H1A05E8" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E8");
                i.putExtras(bundle);
                break;
            case "202H1A05E9" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05E9");
                i.putExtras(bundle);
                break;
            case "202H1A05F0" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05F0");
                i.putExtras(bundle);
                break;
            case "202H1A05F1" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05F1");
                i.putExtras(bundle);
                break;
            case "202H1A05F2" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05F2");
                i.putExtras(bundle);
                break;
            case "202H1A05F3" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05F3");
                i.putExtras(bundle);
                break;
            case "202H1A05F4" :
                bundle.putString(Constant.ROLLNUMBER,"202H1A05F4");
                i.putExtras(bundle);
                break;
        }
        startActivity(i);
    }


    private static class FacultyAttendanceModel {
        private String Attendance;
        public FacultyAttendanceModel(String Attendance) {
            this.Attendance = Attendance;
        }

        public String getAttendance() {
            return Attendance;
        }

        public void setAttendance(String attendance) {
            Attendance = attendance;
        }
    }

    private class FacultyAttendanceAdaptor extends RecyclerView.Adapter<FacultyAttendanceAdaptor.viewholder> {

        private ArrayList<FacultyAttendanceModel> facultyAttendanceModelArrayList;
        private Context ctx;
        public final AttendanceViewInterface attendanceViewInterface;
        private View layoutInflater;
        public FacultyAttendanceAdaptor(Context ctx,
                                        ArrayList<FacultyAttendanceModel> facultyAttendanceModelArrayList,
                                        AttendanceViewInterface attendanceViewInterface) {
            this.ctx = ctx;
            this.facultyAttendanceModelArrayList = facultyAttendanceModelArrayList;
            this.attendanceViewInterface = attendanceViewInterface;
        }
        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            layoutInflater = LayoutInflater.from(ctx)
                    .inflate(R.layout.faculty_attendance_model,parent,false);
            return new viewholder(layoutInflater,attendanceViewInterface);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {
            FacultyAttendanceModel attendanceModel = facultyAttendanceModelArrayList.get(position);
            holder.StudentRollNumber.setText(attendanceModel.getAttendance());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FacultyAttendance.this,GiveAttendance.class);
                    i.putExtra("RollNumber",attendanceModel.getAttendance());
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return facultyAttendanceModelArrayList.size();
        }

        private class viewholder extends RecyclerView.ViewHolder {

            private TextView StudentRollNumber;

            public viewholder(@NonNull View itemView, AttendanceViewInterface attendanceViewInterface) {
                super(itemView);
                StudentRollNumber = itemView.findViewById(R.id.StudentRollNumber);
                Context context = itemView.getContext();
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(attendanceViewInterface != null) {
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION) {
                                attendanceViewInterface.onClick(position,context,StudentRollNumber);
                            }
                        }
                    }
                });
            }
        }
    }
}