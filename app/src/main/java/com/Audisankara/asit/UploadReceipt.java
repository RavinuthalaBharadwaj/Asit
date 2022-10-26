package com.Audisankara.asit;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class UploadReceipt extends Fragment {



    public UploadReceipt() {
        // Required empty public constructor
    }


    private LinearLayout CvUplaod;
    Uri Imageuri = null;
    public static int noofreceipts = 0;
    private LinearLayout lyt;
    CardView cvProceed;
    Dialog dialogone;
    public static ArrayList<Session> receiptdata;
    Session session ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_upload_receipt, container, false);

       CvUplaod = view.findViewById(R.id.lytupload);
        session = new Session(requireActivity());
       CvUplaod.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("application/pdf");
             startActivityForResult(i,1);
            }
        });

       if(MainActivity.chipNavigationBar.getVisibility() == View.VISIBLE) {
           MainActivity.chipNavigationBar.setVisibility(View.GONE);
       }
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == MainActivity.RESULT_OK) {
            Imageuri = data.getData();
            if(data!=null) {
                showDialogForFilename(Imageuri);
            }
        }
    }

    private void showDialogForFilename(Uri PdfData) {

        EditText etFiletype,OrderId;
        Uri data = PdfData;
        dialogone = new Dialog(requireActivity());
        dialogone.setContentView(R.layout.receipt_name_taker);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
        dialogone.show();

        dialogone.setCancelable(true);
        dialogone.getWindow().getAttributes().windowAnimations = R.style.animation;
        etFiletype = dialogone.findViewById(R.id.etTypeofReceipt);
        cvProceed = dialogone.findViewById(R.id.cvProceed);
        OrderId = dialogone.findViewById(R.id.etReceiptId);
        lyt = dialogone.findViewById(R.id.lyt_orderid);
        CardView cvMain = dialogone.findViewById(R.id.cvMain);
        etFiletype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    TransitionManager.beginDelayedTransition(cvMain);
                    lyt.setVisibility(View.VISIBLE);
                }
            }
        });
        OrderId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    TransitionManager.beginDelayedTransition(cvMain);
                    cvProceed.setVisibility(View.VISIBLE);
                }
            }
        });
        cvProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFiletype.getText().toString().isEmpty()) {
                    etFiletype.setError("Enter Receipt Name");
                    etFiletype.requestFocus();
                } else if (OrderId.getText().toString().length() < 10) {
                    OrderId.setError("Enter Proper Order Id");
                    OrderId.requestFocus();
                } else {
                    uploadFileToBase(etFiletype.getText().toString(), OrderId.getText().toString(), data);
                    dialogone.dismiss();
                }
            }
        });
    }

    private void uploadFileToBase(String filename,String OrderId,Uri PdfUri) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://audisankara-institute.appspot.com");
        StorageReference reference = storageReference.child(session.getData(Constant.ROLLNUMBER));
        String finalname = filename.toLowerCase().concat(" - ").concat("Order Id : ").concat(OrderId);
        StorageReference finalone = reference.child(finalname);
        UploadTask uploadTask = finalone.putFile(PdfUri);
        uploadTask
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            showSucessDialog();
                            noofreceipts = session.getInt("TotalReceipts") + 1;
                            session.setInt("TotalReceipts",noofreceipts);
                        }else{
                         FailureDialog();
                            session.setBoolean("do_user_upload_receipt",false);
                        }
                    }
                });
    }

    private void showSucessDialog() {
        Dialog dialogone = new Dialog(requireActivity());
        dialogone.setContentView(R.layout.sucess_dialog);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
        dialogone.show();
        dialogone.getWindow().getAttributes().windowAnimations = R.style.animation;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogone.dismiss();
            }
        },3000);
    }

    private void FailureDialog() {
        Dialog dialogone = new Dialog(requireActivity());
        dialogone.setContentView(R.layout.failure_dialoge);
        dialogone.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogone.setCancelable(false);
        dialogone.show();
        dialogone.getWindow().getAttributes().windowAnimations = R.style.animation;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogone.dismiss();
            }
        },3000);
    }
}

