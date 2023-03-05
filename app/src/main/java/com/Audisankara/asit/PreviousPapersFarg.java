package com.Audisankara.asit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.Audisankara.asit.objects.BottomSheets;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousPapersFarg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousPapersFarg extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PreviousPapersFarg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviousPapersFarg.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviousPapersFarg newInstance(String param1, String param2) {
        PreviousPapersFarg fragment = new PreviousPapersFarg();
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

    private CardView cvMidOne,cvMidTwo;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<String> LinkList ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_previous_papers_farg, container, false);
        cvMidOne =  view.findViewById(R.id.cvMidone);
        cvMidTwo =  view.findViewById(R.id.cvMidTwo);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("SemesterFive");
        LinkList = new ArrayList<>();
        switch (getArguments().getString("AssignLink")) {
            case "CyberSec": {
                databaseReference.child("CyberSecurity")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
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
                case "Flat" : {
                    databaseReference.child("Flat")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot ds : snapshot.getChildren()) {
                                        LinkList.add(String.valueOf(ds.getValue()));
                                    }
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
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                    break;
            }
            }
        cvMidOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(),PdfViewActivity.class);
                if(LinkList.get(11).equals("null")) {
                    BottomSheets.INSTANCE.notAvailableBottomSheet(requireContext());
                }else{
                    i.putExtra("PdfName",LinkList.get(11));
                    startActivity(i);
                    //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(1),"Assign Two","dir",false));
                }
            }
        });
        cvMidTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(),PdfViewActivity.class);
                if(LinkList.get(12).equals("null")) {
                    BottomSheets.INSTANCE.notAvailableBottomSheet(requireContext());
                }else{
                    i.putExtra("PdfName",LinkList.get(11));
                    startActivity(i);
                    //startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(requireContext(),LinkList.get(1),"Assign Two","dir",false));
                }
            }
        });
       return  view;
    }
}