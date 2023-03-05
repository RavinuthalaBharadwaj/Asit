package com.Audisankara.asit;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SomeThingWentWrongActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private TextView tv,tvbtntext;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("MainApp").child("UpdateLink");
        setContentView(R.layout.activity_some_thing_went_wrong);
        lottieAnimationView = findViewById(R.id.errorlottie);
        tv = findViewById(R.id.errortext);
        tvbtntext = findViewById(R.id.tvbtntext);
        lottieAnimationView.setAnimation(getIntent().getIntExtra("Lottie",0));
        tv.setText(getIntent().getStringExtra("text"));
        if(tv.getText().toString().contains("Updated")) {
            findViewById(R.id.btnUpdate).setVisibility(View.VISIBLE);
        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url = String.valueOf(snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        tvbtntext.setText(getIntent().getStringExtra("btntext"));

            findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getIntent().getStringExtra("btntext").equals("Update Now")) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    if(getIntent().getStringExtra("btntext").equals("Exit")) {
                        finish();
                    }
                }
            });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}