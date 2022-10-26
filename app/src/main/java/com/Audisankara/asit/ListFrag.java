package com.Audisankara.asit;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

public class ListFrag extends Fragment implements View.OnClickListener{


    String url1 = "2Dkxrov1t64";
    String url2 = "txanyHNTEXw";
    String url3 = "uswalwNlxeY";
    String url4 = "fEwXvi1CxIs";
    String url5 = "CdDdwwPV0Qw";
    String url6 = "6mezJjDcIFY";
    String url7 = "CbnrN1ZLIxE";
    String url8 = "fkifo5pnU2Q";
    private String thubmail1 = "https://img.youtube.com/vi/"+ url1 +"/0.jpg";
    private String thubmail2 = "https://img.youtube.com/vi/"+ url2 +"/0.jpg";
    private String thubmail3 = "https://img.youtube.com/vi/"+ url3 +"/0.jpg";
    private String thubmail4 = "https://img.youtube.com/vi/"+ url4 +"/0.jpg";
    private String thubmail5 = "https://img.youtube.com/vi/"+ url5 +"/0.jpg";
    private String thubmail6 = "https://img.youtube.com/vi/"+ url6 +"/0.jpg";
    private String thubmail7 = "https://img.youtube.com/vi/"+ url7 +"/0.jpg";
    private String thubmail8 = "https://img.youtube.com/vi/"+ url8 +"/0.jpg";
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    private Uri uriChannel,uriVideo1,uriVideo2,uriVideo3,uriVideo4,uriVideo5,uriVideo6,uriVideo7,uriVideo8;
    private CardView channel,video1,video2,video3,video4,video5,video6,video7,video8;
    Intent i ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LottieAnimationView lottieAnimationView ;
        // Inflate the layout for this fragment


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
        i = new Intent(Intent.ACTION_VIEW);
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Animation animation = AnimationUtils.makeInAnimation(view.getContext(),true);
        animation.setDuration(500);
        RelativeLayout layout = view.findViewById(R.id.lytyt);
        layout.startAnimation(animation);
        img1 = view.findViewById(R.id.video1);
        img2 = view.findViewById(R.id.video2);
        img3 = view.findViewById(R.id.video3);
        img4 = view.findViewById(R.id.video4);
        img5 = view.findViewById(R.id.video5);
        img6 = view.findViewById(R.id.video6);
        img7 = view.findViewById(R.id.video7);
        img8 = view.findViewById(R.id.video8);

        channel = view.findViewById(R.id.cvchannel);
        video1 = view.findViewById(R.id.cv1);
        video2 = view.findViewById(R.id.cv2);
        video3 = view.findViewById(R.id.cv3);
        video4 = view.findViewById(R.id.cv4);
        video5 = view.findViewById(R.id.cv5);
        video6 = view.findViewById(R.id.cv6);
        video7 = view.findViewById(R.id.cv7);
        video8 = view.findViewById(R.id.cv8);

        Glide.with(requireContext()).load(thubmail1).into(img1);
        Glide.with(requireContext()).load(thubmail2).into(img2);
        Glide.with(requireContext()).load(thubmail3).into(img3);
        Glide.with(requireContext()).load(thubmail4).into(img4);
        Glide.with(requireContext()).load(thubmail5).into(img5);
        Glide.with(requireContext()).load(thubmail6).into(img6);
        Glide.with(requireContext()).load(thubmail7).into(img7);
        Glide.with(requireContext()).load(thubmail8).into(img8);

        channel.setOnClickListener(this);
        video1.setOnClickListener(this);
        video2.setOnClickListener(this);
        video3.setOnClickListener(this);
        video4.setOnClickListener(this);
        video5.setOnClickListener(this);
        video6.setOnClickListener(this);
        video7.setOnClickListener(this);
        video8.setOnClickListener(this);

        uriChannel = Uri.parse("https://www.youtube.com/channel/UCK_fD4bLh97CNrQD_qVYixw");
        uriVideo1 = Uri.parse("https://www.youtube.com/watch?v=2Dkxrov1t64");
        uriVideo2 = Uri.parse("https://www.youtube.com/watch?v=txanyHNTEXw");
        uriVideo3 = Uri.parse("https://www.youtube.com/watch?v=uswalwNlxeY");
        uriVideo4 = Uri.parse("https://www.youtube.com/watch?v=fEwXvi1CxIs");
        uriVideo5 = Uri.parse("https://www.youtube.com/watch?v=CdDdwwPV0Qw");
        uriVideo6 = Uri.parse("https://www.youtube.com/watch?v=6mezJjDcIFY");
        uriVideo7 = Uri.parse("https://www.youtube.com/watch?v=CbnrN1ZLIxE");
        uriVideo8 = Uri.parse("https://www.youtube.com/watch?v=fkifo5pnU2Q");

        return  view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.cvchannel:
                i.setData(uriChannel);
                startActivity(i);
                break;
            case R.id.cv1:
                i.setData(uriVideo1);
                startActivity(i);
                break;
            case R.id.cv2:
                i.setData(uriVideo2);
                startActivity(i);
                break;
            case R.id.cv3:
                i.setData(uriVideo3);
                startActivity(i);
                break;
            case R.id.cv4:
                i.setData(uriVideo4);
                startActivity(i);
                break;
            case R.id.cv5:
                i.setData(uriVideo5);
                startActivity(i);
                break;
            case R.id.cv6:
                i.setData(uriVideo6);
                startActivity(i);
                break;
            case R.id.cv7:
                i.setData(uriVideo7);
                startActivity(i);
                break;
            case R.id.cv8:
                i.setData(uriVideo8);
                startActivity(i);
                break;

        }
    }
}