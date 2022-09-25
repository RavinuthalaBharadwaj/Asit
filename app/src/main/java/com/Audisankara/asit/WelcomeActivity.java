package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvSignIn;
    private CardView cvGetStarted;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        activity = WelcomeActivity.this;
        tvSignIn = findViewById(R.id.tvSignin);
        cvGetStarted = findViewById(R.id.cvGetStarted);
        tvSignIn.setOnClickListener(v-> {
            startActivity(new Intent(activity,SignInActivity.class));
            finish();
        });
        cvGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity,SignUpActivity.class));
                finish();
            }
        });

    }
}