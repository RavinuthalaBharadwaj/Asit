package com.Audisankara.asit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

public class BooksActivity extends AppCompatActivity implements View.OnClickListener{

    public static FragmentManager fm;
    LinearLayout lyt ;
    CardView cv ;
    private CardView CvFirstYearOptionOne,CvFirstYearOptionTwo,
    CvSecondYearOptionOne,CvSecondYearOptionTwo,
            CvThirdYearOptionOne,CvThirdYearOptionTwo,
    CvFinalYearOptionOne,CvFinalYearOptionTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        //options of linear lyt
        CvFirstYearOptionOne = findViewById(R.id.cvOptionOneFirstYear);
        CvFirstYearOptionTwo = findViewById(R.id.CvFirstYearOptionTwo);
        CvSecondYearOptionOne = findViewById(R.id.cvsecondyearoptionone);
        CvSecondYearOptionTwo = findViewById(R.id.cvsecondyearoptiontwo);
        CvThirdYearOptionOne = findViewById(R.id.cvThirdYearOptionOne);
        CvThirdYearOptionTwo = findViewById(R.id.cvThirdYearOptionTwo);
        CvFinalYearOptionOne = findViewById(R.id.FinalYearOptionOne);
        CvFinalYearOptionTwo = findViewById(R.id.FinalYearOptionTwo);
        lyt = findViewById(R.id.InvisibleLyt);

        fm = getSupportFragmentManager();

        cv = findViewById(R.id.cvExpand);
        CvFirstYearOptionOne.setOnClickListener(this);
        CvFirstYearOptionTwo.setOnClickListener(this);
        CvSecondYearOptionOne.setOnClickListener(this);
        CvSecondYearOptionTwo.setOnClickListener(this);
        CvThirdYearOptionOne.setOnClickListener(this);
        CvThirdYearOptionTwo.setOnClickListener(this);
        CvFinalYearOptionOne.setOnClickListener(this);
        CvFinalYearOptionTwo.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }



        //fm.beginTransaction().replace(R.id.container,new MaterilasFragment()).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
            lyt.setVisibility(View.VISIBLE);
            TransitionManager.beginDelayedTransition(cv,new AutoTransition().setDuration(1000).addTarget(cv));
    }
    @Override
    public void onPause() {
        super.onPause();
        lyt.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(cv,new AutoTransition().setDuration(1000).addTarget(cv));

    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(BooksActivity.this, BooksHomeActivity.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.cvOptionOneFirstYear:
                Toast.makeText(this, "Only 2nd year and 3rd Year available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.CvFirstYearOptionTwo:
                Toast.makeText(this, "Only 2nd year and 3rd Year available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cvsecondyearoptionone:
                bundle.putString("SubjectOne", "Data base\nManagement System's");
                bundle.putString("SubjectTwo", "Software\nEngineering");
                bundle.putString("SubjectThree", "Java");
                bundle.putString("SubjectFour", "Ps & Nm");
                bundle.putString("SubjectFive", "Computer Architecture\nOrganization");
                bundle.putString("SubjectSix", "Optional");
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvsecondyearoptiontwo:
                Toast.makeText(this, "Only 2nd year and 3rd Year available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cvThirdYearOptionOne:
                bundle.putString("SubjectOne", "CyberSecurity");
                bundle.putString("SubjectTwo", "FLAT");
                bundle.putString("SubjectThree", "Control System's");
                bundle.putString("SubjectFour", "Data Mining");
                bundle.putString("SubjectFive", "Computer Networks");
                bundle.putString("SubjectSix","Uhv");
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvThirdYearOptionTwo:
                Toast.makeText(this, "Available after 4 months", Toast.LENGTH_SHORT).show();
                break;
            case R.id.FinalYearOptionOne:
                Toast.makeText(this, "Only 2nd year and 3rd Year available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.FinalYearOptionTwo:
                Toast.makeText(this, "Only 2nd year and 3rd Year available", Toast.LENGTH_SHORT).show();
                break;

        }
    }

}