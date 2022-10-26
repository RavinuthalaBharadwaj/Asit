package com.Audisankara.asit;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.ghanshyam.graphlibs.Graph;
import com.ghanshyam.graphlibs.GraphData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.owl93.dpb.CircularProgressView;
import com.owl93.dpb.DeterminateProgressViewListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendaceFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendaceFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttendaceFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendaceFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendaceFrag newInstance(String param1, String param2) {
        AttendaceFrag fragment = new AttendaceFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView Username,RollNumber;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference forAttendance;
    private Session session;
    private int SwipeCount;
    private TextView tvHeld,tvAttended,tvSkipped;
    private ProgressBar pbHeld,pbAttended,pbSkipped;
    private CircularProgressView circularProgressView,circularProgressView2;
    private ArrayList<String> data;
    Dialog dialog;
    private int TotalAttendance ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendace, container, false);
        circularProgressView = view.findViewById(R.id.PercentageProgress);
        tvHeld = view.findViewById(R.id.tvHeld);
        tvAttended = view.findViewById(R.id.tvAttended);
        tvSkipped = view.findViewById(R.id.tvSkipped);
        pbHeld = view.findViewById(R.id.pbHeld);
        pbAttended = view.findViewById(R.id.pbAttended);
        pbSkipped = view.findViewById(R.id.pbSkipped);
        dialog = new Dialog(requireActivity());
        dialog.setContentView(R.layout.attendance_dilaogue);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        swipeRefreshLayout = view.findViewById(R.id.refreshlyt);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        session = new Session(requireActivity());
        Username = view.findViewById(R.id.tvUsername);
        RollNumber = view.findViewById(R.id.tvRollNumber);
        RollNumber.setText(session.getData(Constant.ROLLNUMBER));
        Username.setText(session.getData(Constant.NAME));
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GatherData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GatherData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        if (MainActivity.chipNavigationBar.getVisibility() == View.VISIBLE) {
            MainActivity.chipNavigationBar.setVisibility(View.GONE);
        }
        return view;
    }

    private void GatherData() {
        data = new ArrayList<>();
        databaseReference = firebaseDatabase
                .getReference()
                .child("TOTAL_ATTENDANCE")
                .child(session.getData(Constant.ROLLNUMBER));
        databaseReference
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    data.add(String.valueOf(dataSnapshot.getValue()));
                }
                updateData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void updateData() {
        try {
            circularProgressView.setText(data.get(2));
            circularProgressView.animateProgressChange(Integer.parseInt(data.get(2)),5000);
            pbHeld.setProgress(Integer.parseInt(data.get(1)),true);
            pbAttended.setProgress(Integer.parseInt(data.get(0)),true);
            pbSkipped.setProgress(Integer.parseInt(data.get(3)),true);
            tvHeld.setText(data.get(1).concat(" /125"));
            tvAttended.setText(data.get(0).concat(" /125"));
            tvSkipped.setText(data.get(3).concat(" /125"));
        } catch (Exception e) {
            circularProgressView.animateProgressChange(60,5000);
            pbHeld.setProgress(30,true);
            pbAttended.setProgress(60,true);
            pbSkipped.setProgress(80,true);
            tvHeld.setText("N/A".concat(" /125"));
            tvAttended.setText("N/A".concat(" /125"));
            tvSkipped.setText("N/A".concat(" /125"));
        }
    }

}