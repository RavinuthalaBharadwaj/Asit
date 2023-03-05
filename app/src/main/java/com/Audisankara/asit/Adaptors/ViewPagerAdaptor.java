package com.Audisankara.asit.Adaptors;


import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.Audisankara.asit.Fragments.AssignmentsFrag;
import com.Audisankara.asit.MaterilasFragment;
import com.Audisankara.asit.PreviousPapersFarg;
import com.Audisankara.asit.SyllabusFragmnet;

public class ViewPagerAdaptor extends FragmentStateAdapter {
    private String Subject;
    Bundle bundle ;
    private CardView cv;
    private LinearLayout lyt;
    public ViewPagerAdaptor(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String Subject, CardView cv, LinearLayout lyt) {
        super(fragmentManager,lifecycle);
        this.Subject = Subject;
        bundle = new Bundle();
        this.cv = cv;
        this.lyt = lyt;

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            switch (Subject) {
                case "DBMS": {
                    bundle.putString("syllabuslink", "DBMSLINK");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "SE": {
                    bundle.putString("syllabuslink", "SELINK");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "JAVA": {
                    bundle.putString("syllabuslink", "https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FJAVA.png?alt=media&token=4dab450b-dea9-41ce-9081-46abe0aca183");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }

                case "COA": {
                    bundle.putString("syllabuslink", "https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FCOA.png?alt=media&token=59b2c0d6-0d3f-466f-9059-e1ab3064484c");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "PBNM": {
                    bundle.putString("syllabuslink", "https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FProbabilty.png?alt=media&token=1e3a6b36-05c6-4e94-85b5-51a7d1981dea");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                //3rd year
                case "Cyber Security": {
                    //for Syllabus
                    bundle.putString("syllabuslink", "gs://audisankara-institute.appspot.com/Syllabus/CyberSecurity.png");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT": {
                    bundle.putString("syllabuslink", "FlatLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System": {
                    bundle.putString("syllabuslink", "ControlSystemsLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining": {
                    bundle.putString("syllabuslink", "DataMiningLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks": {
                    bundle.putString("syllabuslink", "ComputerNetworksLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv": {
                    bundle.putString("syllabuslink", "UhvLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
            }
            lyt.setVisibility(View.VISIBLE);
            TransitionManager.beginDelayedTransition(cv, new AutoTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    transition.setDuration(1000).addTarget(cv);
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
        } else if (position == 1) {
            lyt.setVisibility(View.GONE);
            TransitionManager.beginDelayedTransition(cv, new AutoTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    transition.setDuration(1000).addTarget(cv);
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

            fragment = new AssignmentsFrag();
            switch (Subject) {

                case "DBMS": {
                    bundle.putString("AssignLink", "DBMS");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "SE": {
                    bundle.putString("AssignLink", "SE");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "JAVA": {
                    bundle.putString("AssignLink", "JAVA");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "PBNM": {
                    bundle.putString("AssignLink", "PBNM");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "COA": {
                    bundle.putString("AssignLink", "COA");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Cyber Security": {
                    bundle.putString("AssignLink", "CyberSec");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT": {
                    bundle.putString("AssignLink", "Flat");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System": {
                    bundle.putString("AssignLink", "ControlSystems");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining": {
                    bundle.putString("AssignLink", "DataMining");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks": {
                    bundle.putString("AssignLink", "ComputerNetworks");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv": {
                    bundle.putString("AssignLink", "Uhv");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
            }
        } else if (position == 2) {
            TransitionManager.beginDelayedTransition(cv, new AutoTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    transition.setDuration(1000).addTarget(cv);
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
            fragment = new MaterilasFragment();
            switch (Subject) {
                case "Cyber Security": {
                    bundle.putString("AssignLink", "CyberSec");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT": {
                    bundle.putString("AssignLink", "Flat");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System": {
                    bundle.putString("AssignLink", "ControlSystems");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining": {
                    bundle.putString("AssignLink", "DataMining");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks": {
                    bundle.putString("AssignLink", "ComputerNetworks");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv": {
                    bundle.putString("AssignLink", "Uhv");
                    fragment = new MaterilasFragment();
                    fragment.setArguments(bundle);
                    break;
                }
            }
        } else if (position == 3) {
            TransitionManager.beginDelayedTransition(cv, new AutoTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    transition.setDuration(1000).addTarget(cv);
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
            fragment = new PreviousPapersFarg();
            switch (Subject) {
                case "Cyber Security": {
                    bundle.putString("AssignLink", "CyberSec");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT": {
                    bundle.putString("AssignLink", "Flat");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System": {
                    bundle.putString("AssignLink", "ControlSystems");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining": {
                    bundle.putString("AssignLink", "DataMining");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks": {
                    bundle.putString("AssignLink", "ComputerNetworks");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv": {
                    bundle.putString("AssignLink", "Uhv");
                    fragment = new PreviousPapersFarg();
                    fragment.setArguments(bundle);
                    break;
                }
            }
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}