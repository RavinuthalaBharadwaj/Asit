package com.Audisankara.asit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText Name,RollNumber,PhoneNumber,Email,Password;
    private CardView cvSignup;
    private LottieAnimationView namelottie,rollnumberlottie,phonenumberlottie,emaillottie,passwordlottie,lottieCheckBox;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Firebasedb firebasedb;
    FirebaseAuth mAuth;
    Boolean isChecked = false;
    int tapcount = 0;
    private TextView SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.CustomFontColor));
        }
        namelottie = findViewById(R.id.namelottie);
        rollnumberlottie = findViewById(R.id.Rollnumberlottie);
        phonenumberlottie = findViewById(R.id.phonenumberlottie);
        emaillottie = findViewById(R.id.lottiemail);
        passwordlottie = findViewById(R.id.lottiepassword);
        Name = findViewById(R.id.etName);
        RollNumber = findViewById(R.id.etRollNumber);
        PhoneNumber = findViewById(R.id.etPhoneNumber);
        Password = findViewById(R.id.etPassword);
        cvSignup = findViewById(R.id.cvSignup);
        Email = findViewById(R.id.etEmail);
        SignIn = findViewById(R.id.tvSignin);
        mAuth = FirebaseAuth.getInstance();
        //firebase things
        firebaseDatabase = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference().child("cse");
        firebasedb = new Firebasedb();

        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            namelottie.setSpeed(1);
                            namelottie.addAnimatorListener(new Animator.AnimatorListener() {
                                                               @Override
                                                               public void onAnimationStart(Animator animation) {

                                                               }

                                                               @Override
                                                               public void onAnimationEnd(Animator animation) {
                                                                   animation.pause();
                                                               }

                                                               @Override
                                                               public void onAnimationCancel(Animator animation) {

                                                               }

                                                               @Override
                                                               public void onAnimationRepeat(Animator animation) {
                                                               }
                                                           });
                                    namelottie.playAnimation();
                        }
                    },4500);
                }
                if(s.toString().isEmpty()) {
                    namelottie.setSpeed(-1);
                    namelottie.playAnimation();
                    namelottie.addAnimatorListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animation.pause();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
        });
        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(s.toString().length() > 10) {
                            passwordlottie.setSpeed(1);
                            passwordlottie.playAnimation();
                            passwordlottie.addAnimatorListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    animation.pause();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {
                                    animation.pause();
                                }
                            });
                        }
                    }
                },1500);
            }
        });
        RollNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(s.toString().startsWith("202H1") || s.toString().startsWith("212H") && s.toString().length() == 10){
                            rollnumberlottie.setSpeed(1);
                            rollnumberlottie.playAnimation();
                            rollnumberlottie.addAnimatorListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
animation.pause();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                        }
                    }
                },1500);
            }
        });
        PhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(s.toString().length() >=10) {
                            phonenumberlottie.setSpeed(1);
                            phonenumberlottie.playAnimation();
                        }
                    }
                },1500);
            }
        });
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!s.toString().isEmpty()){
                            emaillottie.playAnimation();
                            emaillottie.setSpeed(1);
                            emaillottie.addAnimatorListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
animation.pause();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                        }
                    }
                },1500);
            }
        });
        cvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapcount = 1;
                String name = Name.getText().toString();
                String phone = PhoneNumber.getText().toString();
                String Rollnumber = RollNumber.getText().toString().toUpperCase();
                String password = Password.getText().toString();
                String email = Email.getText().toString();
                if (Name.getText().toString().equals("")) {
                    Name.setError("Name");
                    Name.requestFocus();
                } else if (RollNumber.getText().toString().equals("")) {
                    RollNumber.setError("Roll Number");
                    RollNumber.requestFocus();
                } else if (RollNumber.getText().toString().length() < 10) {
                    RollNumber.setError("Enter college id");
                    RollNumber.requestFocus();
                } else if (PhoneNumber.getText().toString().length() < 10) {
                    PhoneNumber.setError("Enter Number");
                    PhoneNumber.requestFocus();
                } else if (Password.getText().toString().equals("")) {
                    Password.setError("password");
                    Password.requestFocus();
                } else if (Email.getText().toString().isEmpty()) {
                    Email.setError("Email");
                    Email.requestFocus();
                } else if (!(Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches())) {
                    Email.setError("Invalid format");
                    Email.requestFocus();
                } else if (Password.getText().toString().length() <= 10) {
                    Password.setError("too small password");
                } else if (!Rollnumber.startsWith("202H") && !Rollnumber.startsWith("212H")) {
                    Toast.makeText(SignUpActivity.this, "Enter proper rollNumber", Toast.LENGTH_SHORT).show();
                } else if(Name.getText().toString().contains(".")) {
                    Name.setError("Remove FullStop in name");
                }else
                {
                    CreateAccount(name, Rollnumber, email, phone, password);
                }
                if(tapcount == 1) {
                    cvSignup.setClickable(false);
                }
            }
        });
        cvSignup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignInUserActivity.class));
                return true;
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignInUserActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "wait", Toast.LENGTH_SHORT).show();
    }

    private void CreateAccount(String name , String RollNumber,String Email,String PhoneNumber,String Password) {
        Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.customdialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        //setting data to firebase class
        firebasedb.setName(name);
        firebasedb.setRollNumber(RollNumber);
        firebasedb.setPhoneNumber(PhoneNumber);
        firebasedb.setEmail(Email);
        firebasedb.setPassword(Password);
        databaseReference.child(RollNumber).setValue(firebasedb).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            createLoginAccount(Email,Password);
                        }
                    },5000);

                }else{
                    Toast.makeText(SignUpActivity.this, "unable to enter data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        GetDataFromDb(name);
    }
    private void createLoginAccount(String email,String password) {
        Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.account_created);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent i = new Intent(SignUpActivity.this,SignInUserActivity.class);
                        if(task.isSuccessful()) {
                            dialog.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(i);
                                    finish();
                                }
                            },2400);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

            private void GetDataFromDb(String rollnumber) {


                databaseReference = FirebaseDatabase.getInstance("https://audisankara-institute-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                DatabaseReference forPhonenumber = databaseReference.child("cse").child(rollnumber).child("phoneNumber");

                forPhonenumber.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String PhoneNumber = snapshot.getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            }