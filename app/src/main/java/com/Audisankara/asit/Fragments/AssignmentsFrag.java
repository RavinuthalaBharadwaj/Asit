package com.Audisankara.asit.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.Audisankara.asit.PdfViewActivity;
import com.Audisankara.asit.R;
import com.Audisankara.asit.helper.Session;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssignmentsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentsFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AssignmentsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignmentsFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignmentsFrag newInstance(String param1, String param2) {
        AssignmentsFrag fragment = new AssignmentsFrag();
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

    private CardView cv1,cv2,cv3,cv4,cv5;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<String> LinkList;
    private Session session;
    private BottomSheetDialog dialog;
    private ImageView img1,img2,img3,img4,img5;
    private int AssignCount =0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assignments, container, false);
        cv1 = view.findViewById(R.id.cvA1);
        cv2 = view.findViewById(R.id.cvA2);
        cv3 = view.findViewById(R.id.cvA3);
        cv4 = view.findViewById(R.id.cvA4);
        cv5 = view.findViewById(R.id.cvA5);

        dialog = new BottomSheetDialog(requireActivity());
        dialog.setContentView(R.layout.not_available);
        session = new Session(requireActivity());
        session.setInt("ClickedAssign",AssignCount);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("SemesterFive");
        LinkList = new ArrayList<>();
        switch (getArguments().getString("AssignLink")) {
            case "CyberSec" : {
                databaseReference.child("CyberSecurity")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                    session.setInt("Cyber",LinkList.size());
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            }
            case "Flat" : {
                databaseReference.child("Flat")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                }
                                session.setInt("Flat",LinkList.size());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            }
            case "ControlSystems" : {
                databaseReference.child("ControlSystems")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                }
                                session.setInt("Control",LinkList.size());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            }
                case "DataMining" : {
                    databaseReference.child("DataMining")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot ds : snapshot.getChildren()) {
                                        LinkList.add(String.valueOf(ds.getValue()));
                                    }
                                    session.setInt("DM",LinkList.size());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                    break;
            }

            case "ComputerNetworks" : {
                databaseReference.child("ComputerNetworks")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                }
                                session.setInt("Computer",LinkList.size());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            }
            case "Uhv" : {
                databaseReference.child("Uhv")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                }
                                session.setInt("Uh",LinkList.size());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                break;
            }
                case "JAVA" : {
                    databaseReference = firebaseDatabase.getReference().child("SecondYear").child("SemesterOne").child("Assignments").child("Java");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                LinkList.add(String.valueOf(ds.getValue()));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                }
                    case "SE" : {
                        databaseReference = firebaseDatabase.getReference().child("SecondYear").child("SemesterOne").child("Assignments").child("Se");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    LinkList.add(String.valueOf(ds.getValue()));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                }
            case "COA" : {
                databaseReference = firebaseDatabase.getReference().child("SecondYear").child("SemesterOne").child("Assignments").child("Coa");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(String.valueOf(ds.getValue()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            }
            case "PBNM" : {
                databaseReference = firebaseDatabase.getReference().child("SecondYear").child("SemesterOne").child("Assignments").child("Pbnm");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(String.valueOf(ds.getValue()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            }
            case "DBMS" : {
                databaseReference = firebaseDatabase.getReference().child("SecondYear").child("SemesterOne").child("Assignments").child("Dbms");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LinkList.add(String.valueOf(ds.getValue()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            }

            }
        LinearLayout lyt = view.findViewById(R.id.lyt);
        Intent i = new Intent(requireContext(), PdfViewActivity.class);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LinkList.get(0).equals("null")) {
                    dialog.show();
                }else{
                    i.putExtra("PdfName",LinkList.get(0));
                    startActivity(i);
                    //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(0),"Assign one","dir",false));
                    AssignCount = AssignCount + 1;
                    session.setInt("ClickedAssign",AssignCount);
                }
                //bundle.putSerializable("CyberSecurityAssignLinks",(Serializable) LinkList);
                //i.putExtra("Bundle",bundle);
                //startActivity(i);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LinkList.get(1).equals("null")) {
                    dialog.show();
                }else{
                    i.putExtra("PdfName",LinkList.get(1));
                    startActivity(i);
                    //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(1),"Assign Two","dir",false));
                    AssignCount = AssignCount + 1;
                    session.setInt("ClickedAssign",AssignCount);
                }
                //bundle.putSerializable("CyberSecurityAssignLinks",(Serializable) LinkList);
                //i.putExtra("Bundle",bundle);
                //startActivity(i);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LinkList.get(2).equals("null")) {
                    dialog.show();
                }else{

                    i.putExtra("PdfName",LinkList.get(2));
                    startActivity(i);
                }
                //bundle.putSerializable("CyberSecurityAssignLinks",(Serializable) LinkList);
                //i.putExtra("Bundle",bundle);
                //startActivity(i);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LinkList.get(3).equals("null")) {
                    dialog.show();
                }else{
                    AssignCount = AssignCount + 1;
                    session.setInt("ClickedAssign",AssignCount);
                    i.putExtra("PdfName",LinkList.get(3));
                    startActivity(i);
                   // startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(3),"Assign Four","dir",false));
                }
                //bundle.putSerializable("CyberSecurityAssignLinks",(Serializable) LinkList);
                //i.putExtra("Bundle",bundle);
                //startActivity(i);
            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LinkList.get(4).equals("null")) {
                    session.setInt("ClickedAssign",AssignCount);
                    dialog.show();
                }else{
                    AssignCount = AssignCount + 1;
                    i.putExtra("PdfName",LinkList.get(4));
                    startActivity(i);
                    //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(4),"Assign one","dir",false));
                }
                //bundle.putSerializable("CyberSecurityAssignLinks",(Serializable) LinkList);
                //i.putExtra("Bundle",bundle);
                //startActivity(i);
            }
        });
        return view;
    }
}