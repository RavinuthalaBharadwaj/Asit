package com.Audisankara.asit;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.slider.Slider;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.lidong.pdf.PDFView;
import com.lidong.pdf.listener.OnDrawListener;
import com.lidong.pdf.listener.OnLoadCompleteListener;
import com.lidong.pdf.listener.OnPageChangeListener;


import java.io.File;
import java.util.List;

public class PdfViewActivity extends AppCompatActivity implements OnPageChangeListener, OnDrawListener, OnLoadCompleteListener {

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference,AssignRef;
    public static File rootPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://audisankara-institute.appspot.com");
        AssignRef = storageReference.child("Assignments").child(getIntent().getStringExtra("PdfName"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Dexter.withActivity(this)
                    .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                            if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                CreateFolderAndShowPdf();
                            }else{
                                if(multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                                    CreateFolderAndShowPdf();
                                }
                            }
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                        @Override
                        public void onError(DexterError dexterError) {
                            Toast.makeText(PdfViewActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }).onSameThread().check();
        }else{
            Dexter.withActivity(this).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                            if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                CreateFolderAndShowPdf();
                            }else{
                                if(multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                                    CreateFolderAndShowPdf();
                                }
                            }
                        }
                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                        @Override
                        public void onError(DexterError dexterError) {
                            Toast.makeText(PdfViewActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }).onSameThread().check();
        }
        }

    private void CreateFolderAndShowPdf() {
        Dialog dialog = new Dialog(PdfViewActivity.this);
        dialog.setContentView(R.layout.downloadingdialouge);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            rootPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + "Asit");
        }else{
            rootPath = new File(Environment.getExternalStorageDirectory(), "Asit");
        }
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }
        final File localFile = new File(rootPath,getIntent().getStringExtra("PdfName"));
        if(localFile.exists()) {
        }else {
            AssignRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(PdfViewActivity.this, "Good to Go", Toast.LENGTH_SHORT).show();
                    PDFView view = findViewById(R.id.pdfView);
                    view.fileFromLocalStorage(PdfViewActivity.this,
                            PdfViewActivity.this,
                            PdfViewActivity.this,
                            localFile.toURI().getPath(),
                            "");
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PdfViewActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }

    @Override
    public void loadComplete(int nbPages) {
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(this, String.valueOf(pageCount), Toast.LENGTH_SHORT).show();
    }
}