package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationBarView;
    private HomeFrag homeFrag;
    private SearchFrag SearchFrag;
    private ListFrag OverViewFrag;
    private ProfileFrag ProfileFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationBarView = findViewById(R.id.bootomNavBar);
        homeFrag = new HomeFrag();
        SearchFrag = new SearchFrag();
        OverViewFrag = new ListFrag();
        ProfileFrag = new ProfileFrag();
        navigationBarView.setSelectedItemId(R.id.Home);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.Home:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFrag).commit();
                      break;
                    case R.id.Search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,SearchFrag).commit();
                        break;
                    case R.id.OverView:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,OverViewFrag).commit();
                        break;
                    case R.id.Profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ProfileFrag).commit();
                }
                return true;
            }
        });
    }
}