package com.Audisankara.asit;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Audisankara.asit.Adaptors.NotificationAdaptor;
import com.Audisankara.asit.Adaptors.TechTalkRecycler;
import com.Audisankara.asit.Models.NotificationModel;
import com.Audisankara.asit.Models.TechTalkRecentModel;
import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecentFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecentFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RecentFrag newInstance(String param1, String param2) {
        RecentFrag fragment = new RecentFrag();
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

    Session session;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LottieAnimationView lottieAnimationView;
    private RecyclerView NotificationRecycler,TechTalkRecycler,ReceiptRecycler;

    private com.Audisankara.asit.Adaptors.TechTalkRecycler techTalkRecyclerAdaptor;
    private NotificationAdaptor notificationAdaptor;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ArrayList<NotificationModel> notificationModelArrayList;
    private ArrayList<TechTalkRecentModel> techTalkRecentModelArrayList;

    private ArrayList<String> Updates,TechTalks;

    private TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        session = new Session(requireActivity());
        //if(session.getBoolean("is_user_upload_receipt")){
          //  getChildFragmentManager().beginTransaction().replace(R.id.receiptlyt,new Recent_ReceiptFrag()).commit();
        //}
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recent, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refreshlyt);
        NotificationRecycler = view.findViewById(R.id.NotificationRecyclerer);
        TechTalkRecycler = view.findViewById(R.id.TechTalkRecycler);

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UPDATES");

        GetUpdates();

        tv = view.findViewById(R.id.tv);
        Link link = new Link("click here")
                .setTextColor(Color.parseColor("#259B24"))                  // optional, defaults to holo blue
                .setTextColorOfHighlightedLink(Color.parseColor("#0D3D0C")) // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)                                       // optional, defaults to true
                .setBold(true);
        LinkBuilder.on(tv).addLink(link).build();

        Animation animation = AnimationUtils.makeInAnimation(view.getContext(),true);
        animation.setDuration(500);
        LinearLayout layout = view.findViewById(R.id.lyrecent);
        layout.startAnimation(animation);

        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //GetUpdates();
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
        return  view;
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
        GetTechTalks();
    }

    private void GetTechTalks() {
        TechTalks = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("CSEB_TECHTALKS").child(session.getData(Constant.ROLLNUMBER));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    TechTalks.add(String.valueOf(ds.getValue()));
                   // techTalkRecentModelArrayList.add(String.valueOf(ds.getValue()));
                }
                UpdateTechTalkRecycler();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UpdateTechTalkRecycler() {
        techTalkRecentModelArrayList = new ArrayList<>();
        for(int i = 0 ;i<TechTalks.size();i++) {
            techTalkRecentModelArrayList.add(new TechTalkRecentModel(TechTalks.get(i)));
        }
        techTalkRecyclerAdaptor = new TechTalkRecycler(techTalkRecentModelArrayList,requireActivity());
        TechTalkRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        TechTalkRecycler.setHasFixedSize(true);
        TechTalkRecycler.setAdapter(techTalkRecyclerAdaptor);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}