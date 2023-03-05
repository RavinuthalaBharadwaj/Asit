package com.Audisankara.asit;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.Audisankara.asit.Adaptors.ViewPagerAdaptor;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class BooksMain extends AppCompatActivity {

    public ViewPagerAdaptor viewPagerAdaptor;
    public ViewPager2 viewPager2;
    private CardView cvExpand;
    public static FragmentManager fm;
    protected LinearLayout invisiblelyt;
    private TextView tvSubjectName;
    private DotsIndicator dotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_main);

        viewPager2 = findViewById(R.id.viewpager);
        fm = getSupportFragmentManager();
        Bundle bundle = getIntent().getExtras();
        cvExpand = findViewById(R.id.cvExpand);
        dotsIndicator = findViewById(R.id.dotIndicator);
        String SubjectName = bundle.getString("subjectname");
        invisiblelyt = findViewById(R.id.InvisibleLyt);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager(),getLifecycle(),SubjectName,cvExpand,invisiblelyt);
        viewPager2.setAdapter(viewPagerAdaptor);
        viewPager2.beginFakeDrag();
        tvSubjectName = findViewById(R.id.tvSubjectName);
        tvSubjectName.setText(SubjectName);
        dotsIndicator.attachTo(viewPager2);

    }
}