package com.Audisankara.asit;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFrag extends Fragment implements View.OnClickListener {

    public ProfileFrag() {
        // Required empty public constructor
    }


    private CardView CvAboutDev, CvChangePassword, CvVisitWebsite, CvLogOut;
    private TextView Username, RollNumber;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference updateuser;
    private LinearLayout lyt;
    private Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        lyt = view.findViewById(R.id.lytprofile);
        Animation animation = AnimationUtils.makeInAnimation(view.getContext(),true);
        animation.setDuration(500);
        lyt.startAnimation(animation);

        CvAboutDev = view.findViewById(R.id.cvAboutDev);
        CvLogOut = view.findViewById(R.id.cvLogout);
        CvChangePassword = view.findViewById(R.id.cvChangePassword);
        CvVisitWebsite = view.findViewById(R.id.cvVisitSite);
        Username = view.findViewById(R.id.tvName);
        RollNumber = view.findViewById(R.id.tvRollNumber);
        session = new Session(requireActivity());

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("cse");
        updateuser = databaseReference.child(session.getData(Constant.ROLLNUMBER));
        CvLogOut.setOnClickListener(this);
        CvChangePassword.setOnClickListener(this);
        CvVisitWebsite.setOnClickListener(this);

        Username.setText(session.getData(Constant.NAME));
        RollNumber.setText(session.getData(Constant.ROLLNUMBER));
        CvAboutDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.container, new DevloperFrag(), "developerfrag")
                        .addToBackStack(null)
                        .commit();
                if (MainActivity.chipNavigationBar.getVisibility() == View.VISIBLE) {
                    MainActivity.chipNavigationBar.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvLogout:
                session.logoutUser(requireActivity());
                break;
            case R.id.cvVisitSite:
                Uri uri = Uri.parse("https://www.audisankara.ac.in/");
                Intent I = new Intent(Intent.ACTION_VIEW);
                I.setData(uri);
                startActivity(I);
                break;
            case R.id.cvChangePassword:
                CardView changepassword;
                EditText OldPassword, NewPassword;
                ImageView imgCancel;
                Dialog dialog = new Dialog(requireActivity());
                dialog.setContentView(R.layout.change_password);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.show();
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                changepassword = dialog.findViewById(R.id.proceed);
                OldPassword = dialog.findViewById(R.id.etOldPassword);
                NewPassword = dialog.findViewById(R.id.etNewPassword);
                imgCancel = dialog.findViewById(R.id.imgCancel);
                imgCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                changepassword.setOnClickListener(new View.OnClickListener() {
                    private String newpassword;

                    @Override
                    public void onClick(View v) {
                        if (OldPassword.getText().toString().trim().equals(session.getData(Constant.PASSWORD)) && !(NewPassword.getText().toString().isEmpty())) {
                            newpassword = NewPassword.getText().toString().trim();
                            dialog.dismiss();
                            UpdatePassword(newpassword);
                        } else {
                            Toast.makeText(requireActivity(), "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }

    private void UpdatePassword(String newPassword) {
        updateuser.child(Constant.PASSWORD).setValue(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                           // session.setData(Constant.PASSWORD,newPassword);
                            Toast.makeText(requireActivity(), "Password Changed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "Unable to update Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}