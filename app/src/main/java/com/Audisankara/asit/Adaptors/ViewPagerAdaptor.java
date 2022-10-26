package com.Audisankara.asit.Adaptors;




import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.Audisankara.asit.AssignmentsFrag;
import com.Audisankara.asit.BooksMain;
import com.Audisankara.asit.MaterilasFragment;
import com.Audisankara.asit.PreviousPapersFarg;
import com.Audisankara.asit.R;
import com.Audisankara.asit.SyllabusFragmnet;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerAdaptor extends FragmentPagerAdapter {

    private String Subject;
    Bundle bundle ;
    private TabLayout tabLayout;
    private Context ctx;
    public ViewPagerAdaptor(FragmentManager fm, String Subject, TabLayout tabLayout, Context ctx) {
        super(fm);
        this.Subject = Subject;
        bundle = new Bundle();
        this.tabLayout = tabLayout;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if(position == 0) {
            switch (Subject) {
                case "Cyber Security" : {
                    //for Syllabus
                    bundle.putString("syllabuslink","gs://audisankara-institute.appspot.com/Syllabus/CyberSecurity.png");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT" : {
                    bundle.putString("syllabuslink","FlatLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System" : {
                    bundle.putString("syllabuslink","ControlSystemsLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining" : {
                    bundle.putString("syllabuslink","DataMiningLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks" : {
                    bundle.putString("syllabuslink","ComputerNetworksLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv" : {
                    bundle.putString("syllabuslink","UhvLink");
                    fragment = new SyllabusFragmnet();
                    fragment.setArguments(bundle);
                    break;
                }
            }
        }else if(position == 1) {
            fragment = new AssignmentsFrag();
            switch (Subject) {
                case "Cyber Security" :{
                    bundle.putString("AssignLink","CyberSec");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "FLAT" : {
                    bundle.putString("AssignLink","Flat");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Control System" : {
                    bundle.putString("AssignLink","ControlSystems");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Data Mining" : {
                    bundle.putString("AssignLink","DataMining");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Computer Networks" : {
                    bundle.putString("AssignLink","ComputerNetworks");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
                case "Uhv" : {
                    bundle.putString("AssignLink","Uhv");
                    fragment = new AssignmentsFrag();
                    fragment.setArguments(bundle);
                    break;
                }
            }
        }else if(position == 2) {
            fragment = new MaterilasFragment();
        }else if(position == 3) {
            fragment = new PreviousPapersFarg();
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0) {
            title = "Syllabus";
        }
        else if (position == 1)
            title = "Assignment";
        else if (position == 2)
            title = "Materials";
        else  if(position == 3)
            title = "Previous Papers";
        return title;
    }
}