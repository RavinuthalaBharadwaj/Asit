package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.concurrent.CompletionService;

public class BooksActivity extends AppCompatActivity implements View.OnClickListener{

    public static FragmentManager fm;
    private CardView cvFirstYear,cvSecondYear,cvThirdYear,cvFinalYear;
    private CardView CvFirstYearOptionOne,CvFirstYearOptionTwo,
    CvSecondYearOptionOne,CvSecondYearOptionTwo,
            CvThirdYearOptionOne,CvThirdYearOptionTwo,
    CvFinalYearOptionOne,CvFinalYearOptionTwo;
    private LinearLayout LytFirstYear,LytSecondYear,LytThirdYear,LytFinalYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        //main cv
        cvFirstYear = findViewById(R.id.cvfirstyear);
        cvSecondYear = findViewById(R.id.CvSecondYear);
        cvThirdYear = findViewById(R.id.cvThirdYear);
        cvFinalYear = findViewById(R.id.cvFinalYear);
        //invisible lyt
        LytFirstYear = findViewById(R.id.optionlyt1styear);
        LytSecondYear = findViewById(R.id.optionlytsecondyear);
        LytThirdYear = findViewById(R.id.lytThirdYear);
        LytFinalYear = findViewById(R.id.lytFinalYear);
        //options of linear lyt
        CvFirstYearOptionOne = findViewById(R.id.cvOptionOneFirstYear);
        CvFirstYearOptionTwo = findViewById(R.id.CvFirstYearOptionTwo);
        CvSecondYearOptionOne = findViewById(R.id.cvsecondyearoptionone);
        CvSecondYearOptionTwo = findViewById(R.id.cvsecondyearoptiontwo);
        CvThirdYearOptionOne = findViewById(R.id.cvThirdYearOptionOne);
        CvThirdYearOptionTwo = findViewById(R.id.cvThirdYearOptionTwo);

        fm = getSupportFragmentManager();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        cvFirstYear.setOnClickListener(this);
        cvSecondYear.setOnClickListener(this);
        cvThirdYear.setOnClickListener(this);
        cvFinalYear.setOnClickListener(this);
        //fm.beginTransaction().replace(R.id.container,new MaterilasFragment()).commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvfirstyear:
                if(LytFinalYear.getVisibility() ==View.VISIBLE || LytThirdYear.getVisibility() == View.VISIBLE || LytSecondYear.getVisibility() == View.VISIBLE) {
                    LytThirdYear.setVisibility(View.GONE);
                    LytSecondYear.setVisibility(View.GONE);
                    LytFirstYear.setVisibility(View.GONE);
                }
                if(LytFirstYear.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cvFirstYear, new AutoTransition().addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            transition.setDuration(300).addTarget(cvFirstYear);
                            if(transition.getDuration() == 150) {
                                transition.setDuration(1900).addTarget(cvFirstYear);
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
                    LytFirstYear.setVisibility(View.VISIBLE);
                }else{
                    TransitionManager.beginDelayedTransition(cvFirstYear,new AutoTransition());
                    LytFirstYear.setVisibility(View.GONE);
                    CvFirstYearOptionOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                        }
                    });

                    CvFirstYearOptionTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.CvSecondYear:
                if(LytFinalYear.getVisibility() ==View.VISIBLE || LytThirdYear.getVisibility() == View.VISIBLE || LytFirstYear.getVisibility() == View.VISIBLE) {
                    LytFinalYear.setVisibility(View.GONE);
                    LytThirdYear.setVisibility(View.GONE);
                    LytFirstYear.setVisibility(View.GONE);

                    if (LytSecondYear.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(cvSecondYear, new AutoTransition().addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(Transition transition) {
                                transition.setDuration(300).addTarget(cvSecondYear);
                                if (transition.getDuration() == 150) {
                                    transition.setDuration(1900).addTarget(cvSecondYear);
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
                        LytSecondYear.setVisibility(View.VISIBLE);
                    } else {
                        TransitionManager.beginDelayedTransition(cvSecondYear, new AutoTransition());
                        cvSecondYear.setVisibility(View.GONE);
                        CvSecondYearOptionOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                            }
                        });

                        CvSecondYearOptionTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                }
            case R.id.cvThirdYear:

                if(LytFinalYear.getVisibility() ==View.VISIBLE || LytFirstYear.getVisibility() == View.VISIBLE || LytSecondYear.getVisibility() == View.VISIBLE) {
                    LytFinalYear.setVisibility(View.GONE);
                    LytSecondYear.setVisibility(View.GONE);
                    LytFirstYear.setVisibility(View.GONE);
                }
                if(LytThirdYear.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cvThirdYear, new AutoTransition().addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            transition.setDuration(300).addTarget(cvThirdYear);
                            if(transition.getDuration() == 150) {
                                transition.setDuration(1900).addTarget(cvThirdYear);
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
                    LytThirdYear.setVisibility(View.VISIBLE);
                }else {
                    TransitionManager.beginDelayedTransition(cvThirdYear,new AutoTransition());
                    LytThirdYear.setVisibility(View.GONE);
                    CvThirdYearOptionOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(BooksActivity.this,BooksHomeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("SubjectOne","CyberSecurity");
                            bundle.putString("SubjectTwo","FLAT");
                            bundle.putString("SubjectThree","Control System's");
                            bundle.putString("SubjectFour","Data Mining");
                            bundle.putString("SubjectFive","Computer Networks");
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    CvThirdYearOptionTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(BooksActivity.this, "available after semester one results", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
            case R.id.cvFinalYear:
                if(LytSecondYear.getVisibility() ==View.VISIBLE || LytThirdYear.getVisibility() == View.VISIBLE || LytFirstYear.getVisibility() == View.VISIBLE) {
                    LytSecondYear.setVisibility(View.GONE);
                    LytThirdYear.setVisibility(View.GONE);
                    LytFirstYear.setVisibility(View.GONE);

                    if (LytFinalYear.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(cvFinalYear, new AutoTransition().addListener(new Transition.TransitionListener() {
                            @Override
                            public void onTransitionStart(Transition transition) {
                                transition.setDuration(300).addTarget(cvFinalYear);
                                if (transition.getDuration() == 150) {
                                    transition.setDuration(1900).addTarget(cvFinalYear);
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
                        LytFinalYear.setVisibility(View.VISIBLE);
                    } else {
                        TransitionManager.beginDelayedTransition(cvFinalYear, new AutoTransition());
                        cvFinalYear.setVisibility(View.GONE);
                        CvFinalYearOptionOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                            }
                        });

                        CvFinalYearOptionTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BooksActivity.this, "only 3rd year syllabus available", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                }
        }

    }
}