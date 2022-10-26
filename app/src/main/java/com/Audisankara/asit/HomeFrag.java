package com.Audisankara.asit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.Audisankara.asit.Adaptors.SearchAdaptor;
import com.Audisankara.asit.Adaptors.SearchAdaptorForHome;
import com.Audisankara.asit.Fragments.NotificationFragmnet;
import com.Audisankara.asit.Fragments.WebPageLoader;
import com.Audisankara.asit.Models.SearchModel;
import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.RecylerViewInterface;
import com.Audisankara.asit.helper.Session;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.owl93.dpb.CircularProgressView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFrag extends Fragment implements RecylerViewInterface {

    public HomeFrag() {
        // Required empty public constructor
    }


    private CardView CvAttendance,CvTechTalk,CvSyllabus,CvResult,CvPreviousPapers,CvMaterials,CvReceiptUpload,CvUpdateOne,CvUpdateTwo,CvUpdateThree,CvUpdateFour;
    private TextView tvUsername,TotalTechTalkUploads,TotalReceipts,TotalAssignments;
    private CardView CvAssignmnets;
    private NestedScrollView scrollView;
    public static EditText etSearch;
    private CardView cvSearch;
    private RecyclerView searchRecycler;
    private SearchAdaptorForHome searchAdaptorForHome;
    private ArrayList<SearchModel> searchModelArrayList;
    private Dialog dialog;
    private RelativeLayout NotificationButtonLyt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CircularProgressView circularProgressView;
    private ProgressBar two,three,four;
    private ArrayList<String> DataOfClassesHeld;
    public static LinearLayout InvisibleLyt;
    private ImageView imgprevious,imgmaterilas,updateone,updatetwo,updatethree,updatefour,MainImageCard,SmallCardOne,SmallCardTwo,LongCardOne,LongCardTwo;
    private ProgressBar pbClassesHeld,pbClassesAttended,pbClassesAbsents;
    private TextView tvClassesHeld,tvClassAttended,tvClassesAbsents;
    Session session ;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_new_test, container, false);

        etSearch = view.findViewById(R.id.etSearch);
        cvSearch = view.findViewById(R.id.search);
        InvisibleLyt = view.findViewById(R.id.InvisibleLyt);
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        animation.setDuration(460);
        RelativeLayout layout = view.findViewById(R.id.lytHome);
        layout.startAnimation(animation);

        pbClassesHeld = view.findViewById(R.id.ProgressclassHeld);
        pbClassesAttended = view.findViewById(R.id.ProgressclassAttended);
        pbClassesAbsents = view.findViewById(R.id.ProgressAbsents);
        tvClassesHeld = view.findViewById(R.id.tvHeld);
        tvClassAttended = view.findViewById(R.id.tvAttended);
        tvClassesAbsents = view.findViewById(R.id.tvAbsents);
        CvAttendance = view.findViewById(R.id.cvAttendance);


        two = view.findViewById(R.id.progresstwo);
        searchRecycler = view.findViewById(R.id.SearchRecyclerView);
        three = view.findViewById(R.id.progress3);
        four = view.findViewById(R.id.progress4);
        CvTechTalk = view.findViewById(R.id.CvTechTalk);
        TotalReceipts = view.findViewById(R.id.tvTotalReceipts);
        TotalTechTalkUploads = view.findViewById(R.id.TechTalkProgress);
        circularProgressView = view.findViewById(R.id.HomeScreenDailyProgress);
        swipeRefreshLayout = view.findViewById(R.id.refreshlyt);
        scrollView = view.findViewById(R.id.homescroll);
        CvSyllabus = view.findViewById(R.id.cvSyllabus);
        TotalAssignments = view.findViewById(R.id.tvTotalAssign);
        CvReceiptUpload = view.findViewById(R.id.cvReceiptUpload);
        tvUsername = view.findViewById(R.id.tvUsername);
        CvResult = view.findViewById(R.id.cvResults);
        CvAssignmnets = view.findViewById(R.id.cvAssignments);

        CvUpdateOne = view.findViewById(R.id.cvUpdateOne);
        CvUpdateTwo = view.findViewById(R.id.cvUpdateTwo);
        CvUpdateThree = view.findViewById(R.id.cvUpdateThree);
        CvUpdateFour = view.findViewById(R.id.cvUpdateFour);


        CvPreviousPapers = view.findViewById(R.id.cvPreviousPapers);
        CvMaterials = view.findViewById(R.id.cvMaterials);
        imgprevious = view.findViewById(R.id.imgPrevios);
        imgmaterilas = view.findViewById(R.id.imgMaterilas);
        updateone = view.findViewById(R.id.updateone);
        updatetwo = view.findViewById(R.id.updatetwo);
        updatethree = view.findViewById(R.id.updatethree);
        updatefour = view.findViewById(R.id.updatefour);


        MainImageCard = view.findViewById(R.id.ImgMainCard);
        SmallCardOne = view.findViewById(R.id.SmallCardOne);
        SmallCardTwo = view.findViewById(R.id.SmallCardTwo);
        LongCardOne = view.findViewById(R.id.LongCardOne);
        LongCardTwo = view.findViewById(R.id.LongCardTwo);
        scrollView.setSmoothScrollingEnabled(true);
        session = new Session(requireActivity());

        NotificationButtonLyt = view.findViewById(R.id.lytNotification);

        etSearch.getText().clear();


        three.animate();
        four.animate();

        dialog = new Dialog(requireActivity());
        dialog.setContentView(R.layout.updates_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(MainActivity.chipNavigationBar.getVisibility() == View.VISIBLE) {
                    MainActivity.chipNavigationBar.setVisibility(View.INVISIBLE);
                    TransitionManager.beginDelayedTransition(MainActivity.chipNavigationBar);
                }
                if(!s.toString().isEmpty() && s.toString().length() >= 2 ){
                    TransitionManager.beginDelayedTransition(cvSearch,new AutoTransition().addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            transition.setDuration(300).addTarget(cvSearch);
                            if (transition.getDuration() == 150) {
                                transition.setDuration(2900).addTarget(cvSearch);
                            }
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {

                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    }));
                    InvisibleLyt.setVisibility(View.VISIBLE);
                }
                if(s.toString().isEmpty()) {
                    TransitionManager.endTransitions(MainActivity.chipNavigationBar);
                    MainActivity.chipNavigationBar.setVisibility(View.VISIBLE);
                    TransitionManager.endTransitions(cvSearch);
                    InvisibleLyt.setVisibility(View.GONE);
                }
            }
        });

        //Recycler view
        searchRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        searchModelArrayList = new ArrayList<>();
        searchModelArrayList.add(new SearchModel("Syllabus","Get the syllabus of Present Semester\nof Btech 3rd year .","Academics",R.color.CustomPurple));
        searchModelArrayList.add(new SearchModel("Receipts","Ever stand in line for hours,\nto submit piece of paper?","Exam cell",R.color.CustomRed));
        searchModelArrayList.add(new SearchModel("Previous Papers","Get the Model papers\nof last semester .","Academics",R.color.CustomPurple));
        searchModelArrayList.add(new SearchModel("Materials","Skipped so many classes?\nno worry's we got you","Academics",R.color.CustomPurple));
        searchModelArrayList.add(new SearchModel("Attendance","Worrying about attendance?\nnah not anymore ✨.","Scoring",R.color.teal));
        searchModelArrayList.add(new SearchModel("Assignments","Writting assignmnets is now\neasier than before","Academics",R.color.CustomPurple));
        searchModelArrayList.add(new SearchModel("Results","One step all it takes\nTo see your HardWork","Scoring",R.color.teal));
        searchAdaptorForHome = new SearchAdaptorForHome(searchModelArrayList,this);
        searchRecycler.setAdapter(searchAdaptorForHome);
        searchRecycler.setHasFixedSize(true);


        //Glide things
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.mesh8))
                .into(imgprevious);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.mesh8))
                .into(imgmaterilas);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.update4))
                .into(updateone);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.update3))
                .into(updatetwo);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.update5))
                .into(updatethree);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.update4))
                .into(updatefour);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.meshten))
                .into(MainImageCard);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.mesh9))
                .into(SmallCardOne);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.mesh12))
                .into(SmallCardTwo);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.mesh13))
                .into(LongCardOne);
        Glide.with(requireContext())
                .load(getResources().getDrawable(R.drawable.meshten))
                .into(LongCardTwo);
        
        //firebase 
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        ArrayList<String> data = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        String dayOfTheWeek = sdf
                .format(d)
                .concat(String.valueOf(d.getDate()));
        databaseReference = firebaseDatabase
                .getReference()
                .child("CSE_B_ATTENDANCE")
                .child(session.getData(Constant.ROLLNUMBER))
                .child(dayOfTheWeek);
        databaseReference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            data.add(String.valueOf(dataSnapshot.getValue()));
                        }
                        RetriveClassesHeldData();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        databaseReference = firebaseDatabase
                .getReference()
                        .child("CONSTANTS");
        ArrayList<String> TodayAttendanceArray = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //refreshlyt
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    //for attendance
                    circularProgressView.animateProgressChange(Float.parseFloat(data.get(2)), 5000);
                    circularProgressView.setText(data.get(2).concat("/7"));
                    //for Vertical Progress
                    //data.get(0) is for absents ..
                    //data.get(1) is for presents ..
                    //data.get(2) is for presents but for circular progress
                    pbClassesAttended.setProgress(Integer.parseInt(data.get(1)),true);
                    tvClassAttended.setText(data.get(1));
                    pbClassesAbsents.setProgress(Integer.parseInt(data.get(0)),true);
                    tvClassesAbsents.setText(data.get(0));
                    pbClassesHeld.setProgress(Integer.parseInt(DataOfClassesHeld.get(0)),true);
                    tvClassesHeld.setText(DataOfClassesHeld.get(0));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                    circularProgressView.animateProgressChange(2, 5000);
                    circularProgressView.setText("N/A");
                    pbClassesAttended.setProgress(3,true);
                    tvClassAttended.setText("N/A");
                    pbClassesAbsents.setProgress(2,true);
                    tvClassesAbsents.setText("N/A");
                    pbClassesHeld.setProgress(Integer.parseInt("0"),true);
                    tvClassesHeld.setText("0");
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(InvisibleLyt.getVisibility() == View.VISIBLE) {
                    InvisibleLyt.setVisibility(View.INVISIBLE);
                }
                if(!etSearch.getText().toString().equals("")) {
                    etSearch.getText().clear();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            circularProgressView.animateProgressChange(Float.parseFloat(data.get(2)),5000);
                            circularProgressView.setText(data.get(2).concat("/7"));

                            pbClassesAttended.setProgress(Integer.parseInt(data.get(1)),true);
                            tvClassAttended.setText(data.get(1));
                            pbClassesAbsents.setProgress(Integer.parseInt(data.get(0)),true);
                            tvClassesAbsents.setText(data.get(0));

                            pbClassesHeld.setProgress(Integer.parseInt(DataOfClassesHeld.get(0)),true);
                            tvClassesHeld.setText(DataOfClassesHeld.get(0));
                        }catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            if(data.isEmpty() && DataOfClassesHeld.isEmpty()) {
                                circularProgressView.animateProgressChange(4,5000);
                                circularProgressView.setText("N/A");
                                pbClassesAttended.setProgress(3,true);
                                tvClassAttended.setText("N/A");
                                pbClassesAbsents.setProgress(2,true);
                                tvClassesAbsents.setText("N/A");
                                pbClassesHeld.setProgress(Integer.parseInt("0"),true);
                                tvClassesHeld.setText("0");
                            }
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        tvUsername.setText(session.getData(Constant.NAME));


        CvSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new Syllabus(),"syllabusfrag")
                                        .addToBackStack(null)
                                                .commit();
            }
        });
        CvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new AttendaceFrag(),"attendancefrag")
                        .addToBackStack(null)
                        .commit();
            }
        });
        CvReceiptUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new UploadReceipt(),"uploadreceiptfrag")

                                        .addToBackStack(null)
                                                .commit();
                if(MainActivity.chipNavigationBar.getVisibility() == View.VISIBLE) {
                    MainActivity.chipNavigationBar.setVisibility(View.GONE);
                }
            }
        });
        CvMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),BooksActivity.class));
            }
        });
        CvSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),BooksActivity.class));
            }
        });
        CvPreviousPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),BooksActivity.class));
            }
        });
        CvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new WebPageLoader()).addToBackStack("null").commit();
            }
        });
        CvTechTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new TechTalkFrag()).addToBackStack(null).commit();
            }
        });
        CvUpdateOne.setOnClickListener(new View.OnClickListener() {
            private TextView Tile,Des;
            private ImageView imgUpdate;
            @SuppressLint({"CheckResult", "UseCompatLoadingForDrawables"})
            @Override
            public void onClick(View v) {
                Tile = dialog.findViewById(R.id.tvtitle);
                Des = dialog.findViewById(R.id.updatedes);
                imgUpdate = dialog.findViewById(R.id.imgUpdate);
                Glide.with(dialog.getContext())
                                .load(getResources().getDrawable(R.drawable.update4));
                Tile.setText("Thiry year classes for Asit\\nStudents are about to start soon");
                Des.setText("After sucessfull Completeion of 2nd year ,3rd year class for 20H batch are\\nabout to start soon.");
                dialog.show();
            }
        });
        CvAssignmnets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),BooksActivity.class));
            }
        });
        view.findViewById(R.id.AttendanceCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new AttendaceFrag()).addToBackStack("null").commit();
            }
        });
        NotificationButtonLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity
                        .fm
                        .beginTransaction()
                        .replace(R.id.container,new NotificationFragmnet())
                        .addToBackStack("null")
                        .commit();
            }
        });
        return view;
    }


    private void RetriveClassesHeldData() {
        databaseReference = firebaseDatabase
                .getReference()
                .child("CONSTANTS");
        DataOfClassesHeld = new ArrayList<>();
        databaseReference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()) {
                            DataOfClassesHeld.add(String.valueOf(ds.getValue()));
                        }
                        System.out.println(DataOfClassesHeld.size());
                        session.setData("held",DataOfClassesHeld.get(0));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onItemClick(int position, Context context) {

        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
                startActivity(new Intent(requireContext(),BooksActivity.class));
                break;
            case 4:
                MainActivity.fm.beginTransaction().replace(R.id.container,new AttendaceFrag()).addToBackStack(null).commit();
                break;
            case 5:
                Uri uri = Uri.parse("http://202.53.75.138:2001/asit/student.php?%20=%20student%20results%20for%20ASIT");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
                break;
            case 6:
                MainActivity.fm.beginTransaction().replace(R.id.container,new UploadReceipt()).addToBackStack(null).commit();
                break;
        }
    }

    private void ProgressBars() {
        three.setProgress(session.getInt("TotalReceipts"),true);
        TotalReceipts.setText(String.valueOf(session.getInt("TotalReceipts")));
        two.setProgress(session.getInt("TotalTechUploads"),true);
        TotalTechTalkUploads.setText(String.valueOf(session.getInt("TotalTechUploads")));
        four.setProgress(session.getInt("ClickedAssign"),true);
        TotalAssignments.setText(String.valueOf(session.getInt("ClickedAssign")));
    }
    @Override
    public void onResume() {
        super.onResume();
        etSearch.getText().clear();
        etSearch.setActivated(false);
        etSearch.setCursorVisible(false);
        RetriveClassesHeldData();
        ProgressBars();
    }

    @Override
    public void onStart() {
        super.onStart();
        RetriveClassesHeldData();
        ProgressBars();
    }

}