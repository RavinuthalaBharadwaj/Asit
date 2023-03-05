package com.Audisankara.asit;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.Audisankara.asit.Fragments.ClassesFragment;
import com.Audisankara.asit.helper.Session;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private HomeFrag homeFrag;
    private SearchFrag SearchFrag;
    private ProfileFrag ProfileFrag;
    public static ChipNavigationBar chipNavigationBar;
    public static FragmentManager fm;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFrag = new HomeFrag();
        SearchFrag = new SearchFrag();
        ProfileFrag = new ProfileFrag();
        fm = getSupportFragmentManager();
        session = new Session(this);
        chipNavigationBar = findViewById(R.id.bootomNavBar);
        chipNavigationBar.animate();
        chipNavigationBar.setItemSelected(R.id.Home,true,true);
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ClassesFragment()).commit();
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
            if (chipNavigationBar.getVisibility() == View.GONE) {
                chipNavigationBar.setVisibility(View.VISIBLE);
            } else {
                //Syllabus syllabus = (Syllabus) fm.findFragmentByTag("syllabusfrag");
                DevloperFrag developerfrag = (com.Audisankara.asit.DevloperFrag) fm.findFragmentByTag("developerfrag");
                ProfileFrag profileFrag = (com.Audisankara.asit.ProfileFrag) fm.findFragmentByTag("profilefrag");
            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();

    }

}