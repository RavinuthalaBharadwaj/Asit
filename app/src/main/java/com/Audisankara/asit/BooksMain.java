package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.Adaptors.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class BooksMain extends AppCompatActivity {

    public ViewPagerAdaptor viewPagerAdaptor;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public static FragmentManager fm;
    private TextView tvSubjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablyt);
        fm = getSupportFragmentManager();
        Bundle bundle = getIntent().getExtras();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        tabLayout.setupWithViewPager(viewPager);
        String SubjectName = bundle.getString("subjectname");
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager(),SubjectName,tabLayout,this);
        Toast.makeText(this, SubjectName, Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_font,null);
        for(int i=0;i<tabLayout.getTabCount();i++) {
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        viewPager.setAdapter(viewPagerAdaptor);
        tvSubjectName = findViewById(R.id.tvSubjectName);
        tvSubjectName.setText(SubjectName);
    }
}