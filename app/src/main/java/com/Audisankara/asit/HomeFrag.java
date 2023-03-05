package com.Audisankara.asit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.Audisankara.asit.Adaptors.SearchAdaptor;
import com.Audisankara.asit.Adaptors.SearchAdaptorForHome;
import com.Audisankara.asit.Models.HomeScreenViewPagerModel;
import com.Audisankara.asit.Models.SearchModel;
import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.RecylerViewInterface;
import com.Audisankara.asit.helper.Session;
import com.Audisankara.asit.objects.BottomSheets;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.owl93.dpb.CircularProgressView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HomeFrag extends Fragment implements RecylerViewInterface {

    public HomeFrag() {
        // Required empty public constructor
    }


    private CardView CvAttendance,CvTechTalk,CvSyllabus,CvResult,CvPreviousPapers,CvMaterials,CvReceiptUpload;
    private TextView tvUsername,TotalTechTalkUploads,TotalReceipts,TotalAssignments;
    private CardView CvAssignmnets;
    private NestedScrollView scrollView;
    public static EditText etSearch;
    private CardView cvSearch,CvSpecialCard;
    private ImageView imgdown;
    private Handler handler;
    private RecyclerView searchRecycler;
    private SearchAdaptorForHome searchAdaptorForHome;
    private ArrayList<SearchModel> searchModelArrayList;
    private Dialog dialog;
    private BottomSheetDialog bottomSheetDialog;
    private SearchAdaptor adaptor;
    private RelativeLayout NotificationButtonLyt;
    private LinearLayout invisiblleLytOfWeeklyAttendance;
    private TextView invisibleTextOfWeeklyAttendance;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CircularProgressView circularProgressView;
    private ProgressBar two,three,four;
    private ArrayList<String> DataOfClassesHeld;
    private ArrayList<HomeScreenViewPagerModel> homeScreenViewPagerModelArrayList;
    public static LinearLayout InvisibleLyt;
    private ImageView imgprevious,imgmaterilas,updateone,updatetwo,updatethree,updatefour,MainImageCard,SmallCardOne,SmallCardTwo,LongCardOne,LongCardTwo;
    private ProgressBar pbClassesHeld,pbClassesAttended,pbClassesAbsents;
    private TextView tvClassesHeld,tvClassAttended,tvClassesAbsents;
    private RelativeLayout TechTalkAvail,BooksAvail,ReceiptAvail,BooksAvailForAssign;
    //for Subject cards
    private CardView CvSubjectOne,CvSubjectTwo,CvSubjectThree,CvSubjectFour,CvSubjectFive,CvSubjectSix;
    private TextView AsSubject1, AsSubject2, AsSubject3, AsSubject4, AsSubject5, AsSubject6, MSubject1,MSubject2,MSubject3,MSubject4,MSubject5,MSubject6;
    Session session ;
    int count = 0;
    @RequiresApi(api = Build.VERSION_CODES.S)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_new_test, container, false);

        session = new Session(requireActivity());
        CvSpecialCard = view.findViewById(R.id.SpecialCard);
        etSearch = view.findViewById(R.id.etSearch);
        cvSearch = view.findViewById(R.id.search);
        InvisibleLyt = view.findViewById(R.id.InvisibleLyt);
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        animation.setDuration(460);
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        RelativeLayout layout = view.findViewById(R.id.lytHome);
        layout.startAnimation(animation);
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();


        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.new_feautrea_dialouge);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().setDimAmount(0.5f);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Dialog finalDialog = dialog;

        Dialog dialog2 = new Dialog(requireContext());
        dialog2.setContentView(R.layout.new_feautrea_dialouge_flow_2);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog2.setCancelable(false);
        dialog2.getWindow().setDimAmount(0.5f);
        dialog2.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.findViewById(R.id.cvNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                dialog2.show();
            }
        });
        dialog2.findViewById(R.id.TvFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
                session.setBoolean("intro_done",true);
            }
        });
        if(session.getBoolean("intro_done")) {
            dialog.dismiss();
        }else{
            dialog.show();
        }

        bottomSheetDialog.setContentView(R.layout.mid_exams_bottomsheet);
        CvSpecialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        //Bottom Sheet

        CardView TimeTable = bottomSheetDialog.findViewById(R.id.CvTimeTable);
        CardView SeatMap = bottomSheetDialog.findViewById(R.id.CvSeatMap);
        Intent i1 = new Intent(requireActivity(),SyllabusViewerActivity.class);

        TimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getReference().child("TimeTables").child("TimeTable");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(String.valueOf(snapshot.getValue()).equals("null")) {
                            BottomSheets.INSTANCE.notAvailableBottomSheet(requireActivity());
                        }else{
                            i1.putExtra("SyllabusLink",String.valueOf(snapshot.getValue()));
                            startActivity(i1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        SeatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getReference().child("TimeTables").child("SeatMap");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(String.valueOf(snapshot.getValue()).equals("null")) {
                            BottomSheets.INSTANCE.notAvailableBottomSheet(requireContext());
                        }else{
                            i1.putExtra("SyllabusLink",String.valueOf(snapshot.getValue()));
                            startActivity(i1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



        TechTalkAvail = view.findViewById(R.id.TechTalkaAvailabilty);
        BooksAvail = view.findViewById(R.id.BooksAvailabilty);
        ReceiptAvail = view.findViewById(R.id.ReceiptAvailabilty);
        two = view.findViewById(R.id.progresstwo);
        searchRecycler = view.findViewById(R.id.SearchRecyclerView);
        three = view.findViewById(R.id.progress3);
        four = view.findViewById(R.id.progress4);
        CvTechTalk = view.findViewById(R.id.CvTechTalk);
        TotalReceipts = view.findViewById(R.id.tvTotalReceipts);
        TotalTechTalkUploads = view.findViewById(R.id.TechTalkProgress);
        swipeRefreshLayout = view.findViewById(R.id.refreshlyt);
        scrollView = view.findViewById(R.id.homescroll);
        CvSyllabus = view.findViewById(R.id.cvSyllabus);
        TotalAssignments = view.findViewById(R.id.tvTotalAssign);
        CvReceiptUpload = view.findViewById(R.id.cvReceiptUpload);
        tvUsername = view.findViewById(R.id.tvUsername);
        CvResult = view.findViewById(R.id.cvResults);
        CvAssignmnets = view.findViewById(R.id.cvAssignments);




        CvPreviousPapers = view.findViewById(R.id.cvPreviousPapers);
        CvMaterials = view.findViewById(R.id.cvMaterials);
        BooksAvailForAssign = view.findViewById(R.id.BooksAvailabiltyForAssig);

        SmallCardOne = view.findViewById(R.id.SmallCardOne);
        SmallCardTwo = view.findViewById(R.id.SmallCardTwo);
        LongCardOne = view.findViewById(R.id.LongCardOne);
        LongCardTwo = view.findViewById(R.id.LongCardTwo);
        scrollView.setSmoothScrollingEnabled(true);


        CvSubjectOne = view.findViewById(R.id.CvSubjectOne);
        CvSubjectTwo = view.findViewById(R.id.CvSubjectTwo);
        CvSubjectThree = view.findViewById(R.id.CvSubjectThree);
        CvSubjectFour = view.findViewById(R.id.CvSubjectFour);
        CvSubjectFive = view.findViewById(R.id.CvSubjectFive);
        CvSubjectSix = view.findViewById(R.id.CvSubjectSix);

        AsSubject1 = view.findViewById(R.id.SubjectOneAvailAssign);
        AsSubject2 = view.findViewById(R.id.SubjectTwoAvailAssign);
        AsSubject3 = view.findViewById(R.id.SubjectThreeAvailAssign);
        AsSubject4 = view.findViewById(R.id.SubjectFourAvailAssign);
        AsSubject5 = view.findViewById(R.id.SubjectFiveAvailAssign);
        AsSubject6 = view.findViewById(R.id.SubjectSixAvailAssign);

        MSubject1 = view.findViewById(R.id.SubjectOneAvailMater);
        MSubject2 = view.findViewById(R.id.SubjectTwoAvailMater);
        MSubject3 = view.findViewById(R.id.SubjectThreeAvailMater);
        MSubject4 = view.findViewById(R.id.SubjectFourAvailMater);
        MSubject5 = view.findViewById(R.id.SubjectFiveAvailMater);
        MSubject6 = view.findViewById(R.id.SubjectSixAvailMater);

        NotificationButtonLyt = view.findViewById(R.id.lytNotification);

        etSearch.getText().clear();


        three.animate();
        four.animate();

        dialog = new Dialog(requireActivity());
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
                    fliter(s.toString());
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


        //Subject cvs

        Intent i = new Intent(requireActivity(),BooksMain.class);
        Bundle b = new Bundle();
        CvSubjectOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname","Cyber Security");
                i.putExtras(b);
                startActivity(i);
            }
        });
        CvSubjectTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname","Data Mining");
                i.putExtras(b);
                startActivity(i);
            }
        });
        CvSubjectThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname","FLAT");
                i.putExtras(b);
                startActivity(i);
            }
        });
        CvSubjectFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname","Uhv");
                i.putExtras(b);
                startActivity(i);
            }
        });
        CvSubjectFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname", "Computer Networks");
                i.putExtras(b);
                startActivity(i);
            }
        });
        CvSubjectSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.putString("subjectname", "Control System");
                i.putExtras(b);
                startActivity(i);
            }
        });

        try {
            AsSubject1.setText(session.getData("Cyber"));
            AsSubject2.setText(session.getData("DM"));
            AsSubject3.setText(session.getData("Flat"));
            AsSubject4.setText(session.getData("Uh"));
            AsSubject5.setText(session.getData("Computer"));
            AsSubject6.setText(session.getData("Control"));
        }catch (Exception E) {
            AsSubject1.setText("0");
            AsSubject2.setText("0");
            AsSubject3.setText("0");
            AsSubject4.setText("0");
            AsSubject5.setText("0");
            AsSubject6.setText("0");
        }
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
        searchRecycler.setHasFixedSize(true);
        searchRecycler.setAdapter(searchAdaptorForHome);


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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(InvisibleLyt.getVisibility() == View.VISIBLE) {
                    InvisibleLyt.setVisibility(View.INVISIBLE);
                }
                if(!etSearch.getText().toString().equals("")) {
                    etSearch.getText().clear();
                }
                FetchAvailAssign();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        tvUsername.setText(session.getData(Constant.NAME));
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
                Intent i = new Intent(requireContext(),WebPage.class);
                i.putExtra("LinkTo" +
                        "Load","http://202.53.75.138:2001/asit/student.php?%20=%20student%20results%20for%20ASIT");
                startActivity(i);
            }
        });
        CvTechTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container,new TechTalkFrag()).addToBackStack(null).commit();
            }
        });
        CvAssignmnets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(),BooksActivity.class));
            }
        });

        NotificationButtonLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity
                        .fm
                        .beginTransaction()
                        .addToBackStack("null")
                        .commit();
            }
        });
        FetchAvailAssign();
        return view;
    }


    @Override
    public void onItemClick(int position, Context context) {

        switch (position) {
            case 1:
                MainActivity.fm.beginTransaction().replace(R.id.container,new UploadReceipt()).addToBackStack(null).commit();
                break;
            case 2:
            case 0:
            case 3:
            case 5:
                startActivity(new Intent(requireContext(),BooksActivity.class));
                break;
            case 4:
                MainActivity.fm.beginTransaction().replace(R.id.container,new AttendaceFrag()).addToBackStack(null).commit();
                break;
            case 6:
                Intent i = new Intent(requireContext(),WebPage.class);
                i.putExtra("LinkTOLoad","http://202.53.75.138:2001/asit/student.php?%20=%20student%20results%20for%20ASIT");
                startActivity(i);
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
    private void FetchAvailAssign() {
        ArrayList<String> list = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("TotalAssign");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    list.add(String.valueOf(ds.getValue()));
                                    System.out.println(list.size()+"assign data");
                                }
                                AsSubject1.setText(String.valueOf(list.get(1)));
                                AsSubject2.setText(String.valueOf(list.get(2)));
                                AsSubject3.setText(String.valueOf(list.get(3)));
                                AsSubject4.setText(String.valueOf(list.get(4)));
                                AsSubject5.setText(String.valueOf(list.get(0)));
                                AsSubject6.setText(String.valueOf(list.get(5)));
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("TotalMate");
        ArrayList<String> list1 = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    list1.add(String.valueOf(ds.getValue()));
                    System.out.println(list.size()+"assign data");
                }
                MSubject1.setText(String.valueOf(list1.get(1)));
                MSubject2.setText(String.valueOf(list1.get(2)));
                MSubject3.setText(String.valueOf(list1.get(3)));
                MSubject4.setText(String.valueOf(list1.get(4)));
                MSubject5.setText(String.valueOf(list1.get(0)));
                MSubject6.setText(String.valueOf(list1.get(5)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        etSearch.getText().clear();
        etSearch.setActivated(false);
        etSearch.setCursorVisible(false);
        tvUsername.setText(session.getData(Constant.NAME));
        ProgressBars();
        GetAvailStatusFromFb();
        FetchAvailAssign();
    }

    @Override
    public void onStart() {
        super.onStart();
        ProgressBars();
        GetAvailStatusFromFb();
    }
    private void fliter(String toString) {
        ArrayList<SearchModel> filteredlist = new ArrayList<SearchModel>();
        for(SearchModel item :searchModelArrayList ) {
            if(item.getName().toLowerCase().contains(toString.toLowerCase())) {
                filteredlist.add(item);
            }
        }

        if(filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "not found", Toast.LENGTH_SHORT).show();
        }else{
            searchAdaptorForHome.filterList(filteredlist);
        }
    }

    private void GetAvailStatusFromFb() {
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("TechTalk");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(Objects.equals(snapshot.getValue(Boolean.class), true)) {
                    TechTalkAvail.setVisibility(View.GONE);
                    CvTechTalk.setClickable(true);
                }else{
                    TechTalkAvail.setVisibility(View.VISIBLE);
                    CvTechTalk.setClickable(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("Books");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(Objects.equals(snapshot.getValue(Boolean.class), true)) {
                    BooksAvail.setVisibility(View.GONE);
                    BooksAvailForAssign.setVisibility(View.GONE);
                    CvAssignmnets.setClickable(true);
                    CvMaterials.setClickable(true);
                    CvSyllabus.setClickable(true);
                    CvPreviousPapers.setClickable(true);
                }else{
                    BooksAvail.setVisibility(View.VISIBLE);
                    BooksAvailForAssign.setVisibility(View.VISIBLE);
                    CvAssignmnets.setClickable(false);
                    CvMaterials.setClickable(false);
                    CvSyllabus.setClickable(false);
                    CvPreviousPapers.setClickable(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("Receipt");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(Objects.equals(snapshot.getValue(Boolean.class), true)) {
                    ReceiptAvail.setVisibility(View.GONE);
                    CvReceiptUpload.setClickable(true);
                }else{
                    ReceiptAvail.setVisibility(View.VISIBLE);
                    CvReceiptUpload.setClickable(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("HomeScreen").child("SemesterCard");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(Objects.equals(snapshot.getValue(Boolean.class), true)) {
                    CvSpecialCard.setVisibility(View.VISIBLE);
                }else{
                    CvSpecialCard.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}