package com.Audisankara.asit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DevloperFrag extends Fragment {

    public DevloperFrag() {
        // Required empty public constructor
    }
    private ImageView Github,instagram,whatsapp,mail;
    PackageManager packageManager;
    Intent i ;
    Session session;
    Uri instauri = Uri.parse("https://instagram.com/x_.cruelworld._x?igshid=YmMyMTA2M2Y=");
    Uri gituri = Uri.parse("https://github.com/RavinuthalaBharadwaj");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_devloper, container, false);
        whatsapp = view.findViewById(R.id.imgWhatsapp);
        instagram = view.findViewById(R.id.imginstagram);
        Github = view.findViewById(R.id.imggithub);
        mail = view.findViewById(R.id.email);
        LinearLayout lyt = view.findViewById(R.id.lyt);
        Animation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        lyt.startAnimation(animation);
        session = new Session(requireActivity());
        packageManager = getContext().getPackageManager();
        i = new Intent(Intent.ACTION_VIEW);
        String message = "Hey there,\nLook like you would like to \nContact me. Yep i got .. \nPhonenumber:9985416664 .This is my number See ya again.";

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.homefragfont));
        }
        String PhoneNumber = "+91".concat(session.getData(Constant.PHONENUMBER));
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                try {
                    String url = "https://api.whatsapp.com/send?phone="+PhoneNumber+"&text="+ URLEncoder.encode(message,"UTF-8");
                     i.setPackage("com.whatsapp");
                     i.setData(Uri.parse(url));
                     if(i.resolveActivity(packageManager)!=null) {
                         getContext().startActivity(i);
                     }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        //for instagram
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_VIEW,instauri);
                i.setPackage("com.instagram.android");
                startActivity(i);
            }
        });

        //for github
        Github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_VIEW,gituri);
                i.setPackage("com.android.chrome");
                startActivity(i);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","bharadwajbharath890@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Well tell your Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "just type what issues you are facing with");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        return  view;
    }
}