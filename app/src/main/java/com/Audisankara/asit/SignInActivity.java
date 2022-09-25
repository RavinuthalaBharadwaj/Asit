package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    Activity activity ;
    CardView SignIn ;
    EditText RollNumber ;
    EditText password ;
    TextView Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignIn = findViewById(R.id.cvSignin);
         RollNumber = findViewById(R.id.etRollNumber);
         password = findViewById(R.id.etPassword);
         Signup = findViewById(R.id.tvSignup);
        activity = SignInActivity.this;
        SignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignup:
                startActivity(new Intent(activity,SignUpActivity.class));
                finish();
            case R.id.cvSignin:
                if(RollNumber.getText().toString().isEmpty()) {
                    RollNumber.setError("College Id");
                    RollNumber.requestFocus();
                }else if(password.getText().toString().isEmpty()) {
                    password.setError("password");
                    password.requestFocus();
                }else{
                    startActivity(new Intent(activity,OtpActivity.class));
                    finish();
                }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(activity,WelcomeActivity.class));
        finish();
    }
}