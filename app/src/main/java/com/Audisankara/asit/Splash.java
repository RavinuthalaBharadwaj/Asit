package com.Audisankara.asit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Objects;

public class Splash extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Session session;
    private Dialog dialogone,dialogTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new Session(Splash.this);

       float density = getResources().getDisplayMetrics().density;
        System.out.println("density"+density);

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("MainApp").child("inservice");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }

        AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(1200);
        animation.setFillAfter(true);
        findViewById(R.id.img).startAnimation(animation);



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if((Objects.equals(snapshot.getValue(Boolean.class), true))) {
                    ShowDialog();
                }else{
                    startActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialogone = new Dialog(this);
        dialogone.setContentView(R.layout.nointernt);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
    }

    private void ShowDialog() {
        Intent i = new Intent(Splash.this,SomeThingWentWrongActivity.class);
        i.putExtra("Lottie",(R.raw.serverdown));
        i.putExtra("text","If its not you, its us .\nwe Fixing some things here \nCome Back Later ");
        i.putExtra("btntext","Exit");
        startActivity(i);
        finish();
    }
    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    private void startActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    databaseReference = firebaseDatabase.getReference().child("OptionsAvailabilitys").child("MainApp").child("Version");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.getValue(String.class).equals("Phoenix-0.0.7-Patch-3")) {
                                Intent i = new Intent(Splash.this,SomeThingWentWrongActivity.class);
                                i.putExtra("Lottie",R.raw.update);
                                i.putExtra("text","This is not the Updated Version.\nTap on the UpdateNow\nFor the latest Version");
                                i.putExtra("btntext","Update Now");
                                startActivity(i);
                                finish();
                            }else {
                                try {
                                    if (isConnected()) {
                                        if (session.getBoolean("is_logged_in")
                                                && (session.getData(Constant.ROLLNUMBER).startsWith("202H")
                                                || session.getData(Constant.PHONENUMBER).startsWith("212H1A"))) {
                                            startActivity(new Intent(Splash.this, MainActivity.class));
                                            finish();
                                        } else if (session.getBoolean("is_logged_in")
                                                && (session.getData(Constant.ROLLNUMBER).startsWith("212H"))) {
                                         //   startActivity(new Intent(Splash.this, MainActivityForSecondYear.class));
                                            finish();
                                        }else{
                                            startActivity(new Intent(Splash.this,WelcomeActivity.class));
                                            finish();
                                        }
                                    }else {
                                        dialogone.show();
                                    }
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        },2000);
    }
}