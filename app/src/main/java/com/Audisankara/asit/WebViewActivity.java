package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.rajat.pdfviewer.PdfRendererView;
import com.rajat.pdfviewer.PdfViewerActivity;

import java.util.ArrayList;

public class WebViewActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private Bundle bundle;
    private PdfViewerActivity pdfViewerActivity;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        arrayList = new ArrayList<>();
        bundle = getIntent().getBundleExtra("Bundle");
        arrayList = (ArrayList<String>)bundle.getSerializable("CyberSecurityAssignLinks");
        Toast.makeText(this, String.valueOf(arrayList.size()), Toast.LENGTH_SHORT).show();
        startActivity(PdfViewerActivity.Companion.launchPdfFromUrl(this,arrayList.get(0),"title","dir",false));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}