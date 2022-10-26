package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Audisankara.asit.helper.Constant;
import com.Audisankara.asit.helper.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignInUserActivity extends AppCompatActivity implements View.OnClickListener{

    Activity activity ;
    CardView SignIn ;
    EditText Email ;
    EditText Password ,RollNumber;
    TextView Signup;
    FirebaseAuth mauth;
    int tapcount = 0;
    FirebaseDatabase mdb;
    DatabaseReference databaseReference;
    private String rollnumber,email,password;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_user);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.CustomBlack));
        }

        session = new Session(this);
        SignIn = findViewById(R.id.cvSignin);
         Email = findViewById(R.id.etEmail);
         Password = findViewById(R.id.etPassword);
         Signup = findViewById(R.id.tvSignup);
         RollNumber = findViewById(R.id.etRollNumber);
        activity = SignInUserActivity.this;
        SignIn.setOnClickListener(this);
        Signup.setOnClickListener(this);
        mauth = FirebaseAuth.getInstance();
        mdb = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = mdb.getReference();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignup:
                startActivity(new Intent(activity,SignUpActivity.class));
                finish();
            case R.id.cvSignin:
                if(Email.getText().toString().isEmpty()) {
                    Email.setError("College Id");
                    Email.requestFocus();
                }else if(Password.getText().toString().isEmpty()) {
                    Password.setError("password");
                    Password.requestFocus();
                }else if(RollNumber.getText().toString().isEmpty() ) {
                    RollNumber.setError("Enter RollNumber");
                    RollNumber.requestFocus();
                }else if(!(Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches())) {
                    Email.setError("Invalid format");
                    Email.requestFocus();
                }else if(Email.getText().toString().isEmpty()) {
                    RollNumber.setError("Enter mail id");
                }else if(RollNumber.getText().toString().length()<10){
                    RollNumber.setError("Invalid Id");
                }else if(!RollNumber.getText().toString().startsWith("202H") && !RollNumber.getText().toString().startsWith("212H")) {
                    Toast.makeText(activity, "Enter proper RollNumber", Toast.LENGTH_SHORT).show();
                }else {
                    email = Email.getText().toString();
                    password = Password.getText().toString();
                    rollnumber = RollNumber.getText().toString();
                   loginuser(email,password,rollnumber);
                }
                if(tapcount == 1) {
                    SignIn.setClickable(false);
                }
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(activity, "wait", Toast.LENGTH_SHORT).show();
    }

    private void loginuser(String email,String password,String rollnumber) {
        mauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            retrivedata(rollnumber.toUpperCase(),email);
                        }else{
                            Toast.makeText(activity, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void retrivedata(String rollnumber,String email) {
        databaseReference = mdb.getReference().child("cse").child(rollnumber);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<String> userlist = new ArrayList<String>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    userlist.add(String.valueOf(snapshot1.getValue()));
                    System.out.println(userlist.size());
                }
                AssignDataToSession(userlist,email);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity, "unable to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AssignDataToSession(ArrayList<String> arrayList,String email){
        if(arrayList.isEmpty()) {
            Toast.makeText(activity, "This RollNumber not Registered", Toast.LENGTH_LONG).show();
        }else{
            session.setData(Constant.EMAIL,arrayList.get(0));
            session.setData(Constant.NAME,arrayList.get(1));
            session.setData(Constant.PASSWORD,arrayList.get(2));
            session.setData(Constant.PHONENUMBER,arrayList.get(3));
            session.setData(Constant.ROLLNUMBER,arrayList.get(4));


            if(!(arrayList.isEmpty())) {
                Intent i = new Intent(SignInUserActivity.this,OtpActivity.class);
                i.putExtra(Constant.PHONENUMBER,session.getData(Constant.PHONENUMBER));
                startActivity(i);
                finish();
            }else{
                Toast.makeText(activity, "look like this is not your roll Number", Toast.LENGTH_LONG).show();
            }
        }
    }

}