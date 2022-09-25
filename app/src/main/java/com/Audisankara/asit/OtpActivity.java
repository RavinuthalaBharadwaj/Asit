package com.Audisankara.asit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OtpActivity extends AppCompatActivity {

    private CardView cvVerifyOtp;
    private TextView tvSendAnotherOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        cvVerifyOtp = findViewById(R.id.cvVerifyOtp);

        cvVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtpActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}