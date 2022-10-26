package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Session;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.InetAddress;

import pl.droidsonroids.gif.GifImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Session session = new Session(Splash.this);



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



        Dialog dialogone = new Dialog(this);
        dialogone.setContentView(R.layout.nointernt);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if(isConnected()) {
                        if(session.getBoolean("is_logged_in")) {
                            startActivity(new Intent(Splash.this,MainActivity.class));
                        }else{
                            startActivity(new Intent(Splash.this,WelcomeActivity.class));
                        }
                    }else{

                         dialogone.show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },2000);
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }
}