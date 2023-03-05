package com.Audisankara.asit;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.Audisankara.asit.Fragments.AccountSettingFrag;
import com.Audisankara.asit.Fragments.ChangeLog_Frag;
import com.Audisankara.asit.databinding.FragmentProfileNewBinding;
import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFrag extends Fragment implements View.OnClickListener {

    public ProfileFrag() {
        // Required empty public constructor
    }


    private FragmentProfileNewBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference updateuser;
    private Session session;
    int option_selected= -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileNewBinding.inflate(getLayoutInflater(),container,false);

        Animation animation = AnimationUtils.makeInAnimation(binding.lytprofile.getContext(),true);
        animation.setDuration(500);
        binding.lytprofile.startAnimation(animation);

        session = new Session(requireActivity());

        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("cse");
        updateuser = databaseReference.child(session.getData(Constant.ROLLNUMBER));
        binding.tvName.setText(session.getData(Constant.NAME));
        binding.tvRollNumber.setText(session.getData(Constant.ROLLNUMBER));

        binding.LytAccountSettings.setOnClickListener(this);
        binding.LytActivityStatus.setOnClickListener(this);
        binding.LytChangeLog.setOnClickListener(this);
        binding.LytContactUs.setOnClickListener(this);
        binding.LytVersion.setOnClickListener(this);
        binding.LytWebsite.setOnClickListener(this);
        binding.LytAboutDev.setOnClickListener(this);
        binding.cvLogout.setOnClickListener(this);
        binding.ProfileRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(session.getBoolean("active")) {
                    binding.activityDot.setAnimation(R.raw.active_dot);
                    binding.activityDot.loop(true);
                    binding.activityDot.playAnimation();
                    binding.activityDot.setSpeed(1f);
                    binding.tvActiveText.setText("Active");
                }else {
                    binding.activityDot.setAnimation(R.raw.offline_dot);
                    binding.tvActiveText.setText("InActive");
                    binding.activityDot.playAnimation();
                    binding.activityDot.setSpeed(1f);
                    binding.activityDot.loop(true);
                }
                binding.ProfileRefresh.setRefreshing(false);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvLogout:
                session.logoutUser(requireActivity());
                break;
            case R.id.LytWebsite:
                Uri uri = Uri.parse("https://www.audisankara.ac.in/");
                Intent I = new Intent(Intent.ACTION_VIEW);
                I.setData(uri);
                startActivity(I);
                break;
            case R.id.LytAccountSettings:
               MainActivity.fm.beginTransaction().replace(R.id.container,new AccountSettingFrag()).
                       addToBackStack("null").commit();
               break;
            case R.id.LytActivityStatus:
                CardView cvActiveOutline,cvInactiveOutLine,cvSaveChanges;
                TextView tvActiveText;
                Animation animation = new AlphaAnimation(0.0f,1.0f);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
                dialog.setContentView(R.layout.set_activity_bottomsheet);
                cvActiveOutline = dialog.findViewById(R.id.cvActiveOutLine);
                cvInactiveOutLine = dialog.findViewById(R.id.cvInActiveOutLine);
                tvActiveText = dialog.findViewById(R.id.Activetext);
                cvSaveChanges = dialog.findViewById(R.id.cvSaveChanges);
                if(session.getBoolean("active")) {
                    cvActiveOutline.setCardBackgroundColor(getResources().getColor(R.color.ActiveOutline));
                    tvActiveText.setText("Others can see you as active in the application");
                    option_selected=0;
                }else{
                    cvInactiveOutLine.setCardBackgroundColor(getResources().getColor(R.color.CustomRed));
                    tvActiveText.setText("Others can see you as inactive in the application");
                    option_selected=1;
                }
                cvActiveOutline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cvInactiveOutLine.setCardBackgroundColor(getResources().getColor(R.color.CustomBlack));
                        cvActiveOutline.setCardBackgroundColor(getResources().getColor(R.color.ActiveOutline));
                        tvActiveText.startAnimation(animation);
                        tvActiveText.setText("Others can see you as active in the application");
                        option_selected = 0;
                    }
                });
                cvInactiveOutLine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cvActiveOutline.setCardBackgroundColor(getResources().getColor(R.color.CustomBlack));
                        cvInactiveOutLine.setCardBackgroundColor(getResources().getColor(R.color.CustomRed));
                        tvActiveText.startAnimation(animation);
                        tvActiveText.setText("Others can see you as inactive in the application");
                        option_selected = 1;
                    }
                });
                cvSaveChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(option_selected==-1) {
                            Toast.makeText(requireContext(), "Select a option", Toast.LENGTH_SHORT).show();
                        }
                         if(option_selected == 0) {
                            session.setBoolean("active",true);
                        }
                            if(option_selected == 1) {
                                session.setBoolean("active",false);
                            }
                        dialog.dismiss();
                        if(session.getBoolean("active")) {
                            binding.activityDot.setAnimation(R.raw.active_dot);
                            binding.activityDot.loop(true);
                            binding.activityDot.playAnimation();
                            binding.activityDot.setSpeed(1f);
                            binding.tvActiveText.setText("Active");
                        }else {
                            binding.activityDot.setAnimation(R.raw.offline_dot);
                            binding.tvActiveText.setText("InActive");
                            binding.activityDot.playAnimation();
                            binding.activityDot.setSpeed(1f);
                            binding.activityDot.loop(true);
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.LytChangeLog:
                MainActivity.fm.beginTransaction().replace(R.id.container,new ChangeLog_Frag()).addToBackStack("null").commit();
                break;
            case R.id.LytContactUs:
                Toast.makeText(requireContext(), "Available Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.LytVersion:
                Toast.makeText(requireContext(), "Asit-V0.0.7-Phoenix (Patch-3)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.LytAboutDev:
                MainActivity.fm.beginTransaction().replace(R.id.container,new DevloperFrag()).addToBackStack("null").commit();
                break;

        }
    }
    private void UpdateUsername(String newPassword) {
        updateuser.child(Constant.UPDATENAME).setValue(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                           session.setData(Constant.NAME,newPassword);
                            session.setBoolean("badgeVisited",true);
                            Toast.makeText(requireActivity(), "Name Changed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "Unable to Change the name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void UpdatePassword(String newPassword) {
        updateuser.child(Constant.PASSWORD).setValue(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                             requireActivity().runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     session.setData(Constant.PASSWORD,newPassword);
                                     session.setBoolean("badgeVisited",true);
                                 }
                             });
                            Toast.makeText(requireActivity(), "done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "Unable to Change password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
        binding.ProfileRefresh.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(session.getBoolean("active")) {
                    binding.activityDot.setAnimation(R.raw.active_dot);
                    binding.activityDot.loop(true);
                    binding.activityDot.setSpeed(1f);
                    binding.activityDot.playAnimation();
                    binding.tvActiveText.setText("Active");
                }else {
                    binding.activityDot.setAnimation(R.raw.offline_dot);
                    binding.tvActiveText.setText("InActive");
                    binding.activityDot.setSpeed(1f);
                    binding.activityDot.playAnimation();
                    binding.activityDot.loop(true);
                }
                binding.ProfileRefresh.setRefreshing(false);
            }
        },2000);
    }
}