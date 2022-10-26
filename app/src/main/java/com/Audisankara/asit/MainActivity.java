package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private HomeFrag homeFrag;
    private SearchFrag SearchFrag;
    private ListFrag OverViewFrag;
    private ProfileFrag ProfileFrag;
    public static ChipNavigationBar chipNavigationBar;
    public static FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFrag = new HomeFrag();
        SearchFrag = new SearchFrag();
        OverViewFrag = new ListFrag();
        ProfileFrag = new ProfileFrag();
        fm = getSupportFragmentManager();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        chipNavigationBar = findViewById(R.id.bootomNavBar);
        chipNavigationBar.animate();
        chipNavigationBar.setItemSelected(R.id.Home,true,true);
        chipNavigationBar.showBadge(R.id.Profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFrag).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.Home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFrag).commit();
                        break;
                    case R.id.Search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,SearchFrag).commit();
                        break;
                    case R.id.Recnt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new RecentFrag()).commit();
                        break;
                    case R.id.OverView:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,OverViewFrag).commit();
                        break;
                    case R.id.Profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ProfileFrag,"profilefrag").commit();
                        break;
                }
            }
        });


    }
    @Override
    public void onBackPressed() {

        if(!HomeFrag.etSearch.getText().toString().isEmpty()) {
            HomeFrag.etSearch.getText().clear();
        }
        if(HomeFrag.InvisibleLyt.getVisibility() ==View.VISIBLE) {
            HomeFrag.InvisibleLyt.setVisibility(View.INVISIBLE);
        }
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            if(fm.getBackStackEntryCount() ==0 ) {
                Toast.makeText(this, "lasto ne", Toast.LENGTH_SHORT).show();
            }
            if (chipNavigationBar.getVisibility() == View.GONE) {
                chipNavigationBar.setVisibility(View.VISIBLE);
            } else {
                Syllabus syllabus = (Syllabus) fm.findFragmentByTag("syllabusfrag");
                DevloperFrag developerfrag = (com.Audisankara.asit.DevloperFrag) fm.findFragmentByTag("developerfrag");
                ProfileFrag profileFrag = (com.Audisankara.asit.ProfileFrag) fm.findFragmentByTag("profilefrag");
                if (syllabus != null && syllabus.isVisible()) {
                    HomeFrag homeFrag = new HomeFrag();
                    MainActivity.fm.beginTransaction().replace(R.id.container, homeFrag, "homefrag").commit();
                } else if (developerfrag != null && developerfrag.isVisible()) {
                    assert profileFrag != null;
                    MainActivity.fm.beginTransaction().replace(R.id.container, profileFrag).commit();
                } else if (profileFrag != null && profileFrag.isVisible()) {
                    if (chipNavigationBar.getVisibility() == View.GONE) {
                        chipNavigationBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

}