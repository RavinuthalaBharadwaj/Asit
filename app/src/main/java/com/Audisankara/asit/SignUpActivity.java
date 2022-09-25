package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this,WelcomeActivity.class));
        finish();
    }

}