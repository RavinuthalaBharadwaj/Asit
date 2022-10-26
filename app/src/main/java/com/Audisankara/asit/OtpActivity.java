package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {

    private CardView cvVerifyOtp;
    private TextView tvSendAnotherOtp,tvPhoneNumber;
    private FirebaseAuth mAuth;
    private String verificationId;
    private DatabaseReference dbReference;
    private OtpTextView otpTextView;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        cvVerifyOtp = findViewById(R.id.cvVerifyOtp);
        otpTextView = findViewById(R.id.otpTextView);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvSendAnotherOtp = findViewById(R.id.tvSendAnother);
        mAuth = FirebaseAuth.getInstance();
        session = new Session(this);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.CustomBlack));
        }
        String number = getIntent().getStringExtra(Constant.PHONENUMBER);
        String phone = "+91" + number;
        tvPhoneNumber.setText(phone);
        sendVerification(phone);
        cvVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpTextView.getOTP().isEmpty()) {
                    otpTextView.requestFocus();
                    Toast.makeText(OtpActivity.this, "Enter otp", Toast.LENGTH_SHORT).show();
                }else if(otpTextView.getOTP().length() < 6) {
                    Toast.makeText(OtpActivity.this, "Invalid otp", Toast.LENGTH_SHORT).show();
                }else{
                   verifyCode(otpTextView.getOTP().toString());
                }
            }
        });

        tvSendAnotherOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerification(phone);
            }
        });
    }


    private void SignInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                                session.setBoolean("is_logged_in",true);
                                showDialog();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(OtpActivity.this, MainActivity.class));
                                        finish();
                                    }
                                },3000);
                        }else{
                            Toast.makeText(OtpActivity.this, "Unable to verify try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        session.setBoolean("is_logged_in",false);
                        startActivity(new Intent(OtpActivity.this,WelcomeActivity.class));
                        finish();
                    }
                });
    }
    private void sendVerification(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBack)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            verifyCode(code);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
      SignInWithCredential(credential);
    }

    private void showDialog() {
        Dialog dialog = new Dialog(OtpActivity.this);
        dialog.setContentView(R.layout.verified_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "wait otp verify automatically", Toast.LENGTH_SHORT).show();
    }
}