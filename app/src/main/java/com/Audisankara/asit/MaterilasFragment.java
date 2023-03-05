package com.Audisankara.asit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterilasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterilasFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MaterilasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaterilasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaterilasFragment newInstance(String param1, String param2) {
        MaterilasFragment fragment = new MaterilasFragment();
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

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,ShiftAbleReference;
    private CardView cv1,cv2,cv3,cv4,cv5;
    private ArrayList<String> LinkList;
    private ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_materilas, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("SemesterFive");
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
        LinkList = new ArrayList<>();
        cv1 = view.findViewById(R.id.cvMaterialsUnitOne);
        cv2 = view.findViewById(R.id.cvMaterialsUnitTwo);
        cv3 = view.findViewById(R.id.cvMaterialsUnitThree);
        cv4 = view.findViewById(R.id.cvMaterialsUnitFour);
        cv5 = view.findViewById(R.id.cvMaterialsUnitFive);
        scrollView = view.findViewById(R.id.scrollable);

        assert getArguments()!=null;
        switch (getArguments().getString("AssignLink")) {
            case "CyberSec" : {
                ShiftAbleReference = databaseReference.child("CyberSecurity");
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(String.valueOf(ds.getValue()));
                        }
                        Toast.makeText(requireActivity(), String.valueOf(LinkList.size()), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
            case "Flat" : {
                ShiftAbleReference = databaseReference.child("Flat");
                LinkList = new ArrayList<>();
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(ds.getValue(String.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
            case "ControlSystems" : {
                ShiftAbleReference = databaseReference.child("ControlSystems");
                LinkList = new ArrayList<>();
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(ds.getValue(String.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
            case "DataMining" : {
                ShiftAbleReference = databaseReference.child("DataMining");
                LinkList = new ArrayList<>();
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(ds.getValue(String.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
            case "ComputerNetworks" : {
                ShiftAbleReference = databaseReference.child("ComputerNetworks");
                LinkList = new ArrayList<>();
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(ds.getValue(String.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
            case "Uhv" : {
                ShiftAbleReference = databaseReference.child("Uhv");
                LinkList = new ArrayList<>();
                ShiftAbleReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(ds.getValue(String.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            break;
        }
        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        cv4.setOnClickListener(this);
        cv5.setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(requireActivity(),PdfViewActivity.class);
        switch (v.getId()) {
            case R.id.cvMaterialsUnitOne:
                if(LinkList.get(5).equals("null")) {
                    scrollView.fling(2);
                    Snackbar.make(scrollView,"Sorry not available",Snackbar.ANIMATION_MODE_SLIDE).show();
                }else{
                        i.putExtra("PdfName",LinkList.get(5));
                        startActivity(i);
                        //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(1),"Assign Two","dir",false));
                }
                break;
            case R.id.cvMaterialsUnitTwo:
                if(LinkList.get(6).equals("null")) {
                    scrollView.fling(2);
                    Snackbar.make(scrollView,"Sorry not available",Snackbar.ANIMATION_MODE_SLIDE).show();
                }else{
                    i.putExtra("PdfName",LinkList.get(6));
                    startActivity(i);
                }
                break;
            case R.id.cvMaterialsUnitThree:
                if(LinkList.get(7).equals("null")) {
                    scrollView.fling(2);
                    Snackbar.make(scrollView,"Sorry not available",Snackbar.ANIMATION_MODE_SLIDE).show();
                }else{
                    i.putExtra("PdfName",LinkList.get(7));
                    startActivity(i);
                }
                break;
            case R.id.cvMaterialsUnitFour:
                if(LinkList.get(8).equals("null")) {
                    scrollView.fling(2);
                    Snackbar.make(scrollView,"Sorry not available",Snackbar.ANIMATION_MODE_SLIDE).show();
                }else{
                    i.putExtra("PdfName",LinkList.get(8));
                    startActivity(i);
                }
                break;
            case R.id.cvMaterialsUnitFive:
                if(LinkList.get(9).equals("null")) {
                    scrollView.fling(2);
                    Snackbar.make(scrollView,"Sorry not available",Snackbar.ANIMATION_MODE_SLIDE).show();
                }else{
                    i.putExtra("PdfName",LinkList.get(9));
                    startActivity(i);
                }
                break;
        }
    }
}