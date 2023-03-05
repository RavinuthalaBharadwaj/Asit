package com.Audisankara.asit;

import android.animation.Animator;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechTalkFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechTalkFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TechTalkFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechTalkFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TechTalkFrag newInstance(String param1, String param2) {
        TechTalkFrag fragment = new TechTalkFrag();
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


    private EditText etLink,etSubject,etTopic;
    private RelativeLayout innertwo;
    private LottieAnimationView lottieAnimationView,lottiefortopic,lottieforsubject;
    private LinearLayout innerThree;
    private CardView innerfour,MainCard;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Session session;
    private int uploadscount=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tech_talk, container, false);
        etLink = view.findViewById(R.id.etLink);
        innertwo = view.findViewById(R.id.innertwo);
        lottieAnimationView = view.findViewById(R.id.lottie);
        innerThree = view.findViewById(R.id.innerthree);
        innerfour = view.findViewById(R.id.innerfour);
        session = new Session(requireActivity());
        etTopic = view.findViewById(R.id.etTopic);
        lottieforsubject = view.findViewById(R.id.lottieforsubject);
        lottiefortopic = view.findViewById(R.id.lottiefortopic);
        etSubject = view.findViewById(R.id.etSubject);
        MainCard = view.findViewById(R.id.tech);

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();
        etLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty() && s.toString().startsWith("https://drive.google.com/")){
                    TransitionManager.beginDelayedTransition(MainCard,new AutoTransition().addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            transition.setDuration(300).addTarget(MainCard);
                            if (transition.getDuration() == 150) {
                                transition.setDuration(1900).addTarget(MainCard);
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
                    innertwo.setVisibility(View.VISIBLE);
                }
            }
        });
        lottieAnimationView.setSpeed(1);
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                TransitionManager.beginDelayedTransition(MainCard,new AutoTransition().addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                        transition.setDuration(300).addTarget(MainCard);
                        if (transition.getDuration() == 150) {
                            transition.setDuration(1900).addTarget(MainCard);
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
                innerThree.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        etTopic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() < 3) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lottiefortopic.setSpeed(1);
                            lottiefortopic.playAnimation();
                        }
                    },1300);
                }
            }
        });
        etSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty() && s.length()<4) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lottieforsubject.setSpeed(1);
                            lottieforsubject.playAnimation();
                        }
                    },1200);
                    TransitionManager.beginDelayedTransition(MainCard,new AutoTransition().addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            transition.setDuration(300).addTarget(MainCard);
                            if (transition.getDuration() == 150) {
                                transition.setDuration(1900).addTarget(MainCard);
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
                    innerfour.setVisibility(View.VISIBLE);
                }
            }
        });

        innerfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitDataToFireBase();
                innerfour.setClickable(false);
            }
        });
        return view;
    }

    private void SubmitDataToFireBase() {
        databaseReference = firebaseDatabase
                .getReference()
                .child("CSEB_TECHTALKS")
                .child(new Session(requireActivity()).getData(Constant.ROLLNUMBER))
                .child(etSubject
                        .getText()
                        .toString()
                        .trim()
                        .toUpperCase());
        Map<String,Object> params = new HashMap<>();
        params.put(etTopic.getText().toString().toUpperCase(),etLink.getText().toString());
        databaseReference.updateChildren(params)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()) {
                       Toast.makeText(requireActivity(), "Upload Done", Toast.LENGTH_SHORT).show();
                       uploadscount = session.getInt("TotalTechUploads") + 1;
                       session
                               .setInt("TotalTechUploads",uploadscount);
                       Toast.makeText(requireActivity(), String.valueOf(session.getInt("TotalTechUploads")), Toast.LENGTH_LONG).show();
                   }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        innerfour.setClickable(true);
        if(session.getInt("TotalTechUploads") == 6) {
            Toast.makeText(requireContext(),"Max limit reached",Toast.LENGTH_LONG).show();
            etLink.setEnabled(false);
        }
    }
}