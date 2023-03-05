package com.Audisankara.asit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class WelcomeActivity extends AppCompatActivity {

    private CardView cvGetStarted;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        Dialog dialogone = new Dialog(this);
        dialogone.setContentView(R.layout.termsandconditions);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
        activity = WelcomeActivity.this;
        cvGetStarted = findViewById(R.id.cvGetStarted);
        cvGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialogone.show();
            }
        });
        cvGetStarted.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,FacultyLogin.class));
                return true;
            }
        });

        CardView cv = dialogone.findViewById(R.id.cvAgree);
        TextView terms = dialogone.findViewById(R.id.tvTerms);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,SignInActivity.class));
                finish();
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "cant avail right now ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}