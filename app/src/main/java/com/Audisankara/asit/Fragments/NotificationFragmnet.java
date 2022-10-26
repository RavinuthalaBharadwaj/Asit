package com.Audisankara.asit.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Audisankara.asit.Adaptors.NotificationAdaptor;
import com.Audisankara.asit.Models.NotificationModel;
import com.Audisankara.asit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.klinker.android.link_builder.Link;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationFragmnet extends Fragment {


    public NotificationFragmnet() {
        // Required empty public constructor
    }


    private RecyclerView NotificationRecycler;
    private NotificationAdaptor notificationAdaptor;
    FirebaseDatabase firebaseDatabase;
    private ArrayList<String> Updates,NotificationLinks;
    DatabaseReference databaseReference;
    private ArrayList<NotificationModel> notificationModelArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_notification_fragmnet, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UPDATES");
        NotificationRecycler = view.findViewById(R.id.NotificationRecyclerer);
        GetUpdates();

        Link link = new Link("click here")
                .setTextColor(Color.parseColor("#259B24"))                  // optional, defaults to holo blue
                .setTextColorOfHighlightedLink(Color.parseColor("#0D3D0C")) // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)                                       // optional, defaults to true
                .setBold(true);
        return  view;
    }

    private void GetNotificationLinks() {
        NotificationLinks = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("LINKS_FOR_NOTIFICATIONS");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    NotificationLinks.add(String.valueOf(ds.getValue()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void GetUpdates() {
        Updates = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Updates.add(String.valueOf(ds.getValue()));
                }
                UpdateRecycler();
                System.out.println(Updates.get(0));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UpdateRecycler() {
        notificationModelArrayList = new ArrayList<>();
        for(int i =0 ;i< Updates.size();i++) {
            notificationModelArrayList.add(new NotificationModel(Updates.get(i)));
        }
        notificationAdaptor = new NotificationAdaptor(notificationModelArrayList,null);
        NotificationRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        NotificationRecycler.setAdapter(notificationAdaptor);
        NotificationRecycler.setHasFixedSize(true);
    }
}