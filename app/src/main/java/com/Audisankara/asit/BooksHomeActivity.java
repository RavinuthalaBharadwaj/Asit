package com.Audisankara.asit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BooksHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView SubjectOne,SubjectTwo,SubjectThree,SubjectFour,SubjectFive,SubjectSix;
    private CardView lytSubjectOne,lytSubjectTwo,lytSubjectThree,lytSubjectFour,lytSubjectFive,lytSubjectSix;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_home);
        SubjectOne = findViewById(R.id.SubjectOne);
        SubjectTwo = findViewById(R.id.SubjectTwo);
        SubjectThree = findViewById(R.id.SubjectThree);
        SubjectFour = findViewById(R.id.SubjectFour);
        SubjectFive = findViewById(R.id.SubjectFive);
        SubjectSix = findViewById(R.id.SubjectSix);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        Animation animation = AnimationUtils.makeInAnimation(this,true);
        findViewById(R.id.lyt).startAnimation(animation);

        bundle = getIntent().getExtras();
        SubjectOne.setText(bundle.getString("SubjectOne"));
        SubjectTwo.setText(bundle.getString("SubjectTwo"));
        SubjectThree.setText(bundle.getString("SubjectThree"));
        SubjectFour.setText(bundle.getString("SubjectFour"));
        SubjectFive.setText(bundle.getString("SubjectFive"));
        SubjectSix.setText(bundle.getString("SubjectSix"));

        lytSubjectOne = findViewById(R.id.cvSubjectOne);
        lytSubjectTwo = findViewById(R.id.cvSubjectTwo);
        lytSubjectThree = findViewById(R.id.cvSubjectThree);
        lytSubjectFour = findViewById(R.id.cvSubjectFour);
        lytSubjectFive = findViewById(R.id.cvSubjectFive);
        lytSubjectSix = findViewById(R.id.cvSubjectSix);

        lytSubjectOne.setOnClickListener(this);
        lytSubjectTwo.setOnClickListener(this);
        lytSubjectThree.setOnClickListener(this);
        lytSubjectFour.setOnClickListener(this);
        lytSubjectFive.setOnClickListener(this);
        lytSubjectSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.cvSubjectOne:
                //for syllabus
                if(bundle.getString("SubjectOne").equals("Data base\nManagement System's")){
                    bundle.putString("subjectname","DBMS");
                }else {
                    bundle.putString("subjectname","Cyber Security");
                }
                i = new Intent(BooksHomeActivity.this,BooksMain.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvSubjectTwo:
                if(bundle.getString("SubjectTwo").equals("Software Engineering")) {
                    bundle.putString("subjectname","SE");
                }else{
                    bundle.putString("subjectname","FLAT");
                }
                i = new Intent(BooksHomeActivity.this,BooksMain.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvSubjectThree:
                if(bundle.getString("SubjectThree").equals("Java")) {
                    bundle.putString("subjectname","JAVA");
                }else {
                    bundle.putString("subjectname", "Control System");
                }
                i = new Intent(BooksHomeActivity.this,BooksMain.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvSubjectFour:
                if(bundle.getString("SubjectFour").equals("Ps & Nm")) {
                    bundle.putString("subjectname","PSNM");
                }else{
                    bundle.putString("subjectname","Data Mining");
                }
                i = new Intent(BooksHomeActivity.this,BooksMain.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvSubjectFive:
                if(bundle.getString("SubjectFive").equals("Computer Architecture\nOrganization")) {
                    bundle.putString("subjectname","COA");
                }else {
                    bundle.putString("subjectname", "Computer Networks");
                }
                i = new Intent(BooksHomeActivity.this,BooksMain.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.cvSubjectSix:
                if(bundle.getString("SubjectSix").equals("Uhv")) {
                    bundle.putString("subjectname","Uhv");
                    bundle.putString("Syllabuslink","link5");
                    i = new Intent(BooksHomeActivity.this,BooksMain.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "This Optional is optional for 2nd years", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}