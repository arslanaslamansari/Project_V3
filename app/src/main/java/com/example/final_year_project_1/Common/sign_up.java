package com.example.final_year_project_1.Common;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_year_project_1.Admin.MainActivity;
import com.example.final_year_project_1.Admin.user_Profile;
import com.example.final_year_project_1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class sign_up extends AppCompatActivity {


    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;
    TextInputLayout full_name, user_name, user_email, user_password;

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button signup_btn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);


        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        // next = findViewById(R.id.signup_button);
        login = findViewById(R.id.signup_login_button);
        full_name = findViewById(R.id.signup_fullname);
        user_name = findViewById(R.id.signup_username);
        user_email = findViewById(R.id.signup_email);
        user_password = findViewById(R.id.signup_password);
        signup_btn = findViewById(R.id.signup_button);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), user_Profile.class));
            finish();
        }


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFullName() && validateUsername() && validateEmail() && validatePassword()) {

                    final String email = user_email.getEditText().getText().toString().trim();
                    String password = user_password.getEditText().getText().toString().trim();
                    final String fullName = full_name.getEditText().getText().toString();
                    final String username = user_name.getEditText().getText().toString();


                    progressBar.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    // register the user in firebase

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // send verification link

                                FirebaseUser fuser = fAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(sign_up.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                    }
                                });

                                Toast.makeText(sign_up.this, "User Created.", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fName", fullName);
                                user.put("email", email);
                                user.put("username", username);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });

                                Intent i = new Intent(getApplicationContext(), user_Profile.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(sign_up.this, "Error ! ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }
                        }
                    });
                }
            }
        });


    }

    private boolean validateFullName() {
        String val = full_name.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            full_name.setError("Field can not be empty");
            return false;
        } else {
            full_name.setError(null);
            full_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = user_name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            user_name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            user_name.setError("Username too long");
            return false;
        } else if (val.length() <= 4) {
            user_name.setError("Username too short");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            user_name.setError("White Spaces are not allowed");
            return false;
        } else {
            user_name.setError(null);
            user_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = user_email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            user_email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            user_email.setError("Invalid email address");
            return false;
        } else {
            user_email.setError(null);
            user_email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = user_password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";

        if (val.isEmpty()) {
            user_password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            user_password.setError("Password is too weak");
            return false;
        } else {
            user_password.setError(null);
            user_password.setErrorEnabled(false);
            return true;
        }
    }

    public void signup_back_button(View view) {
        Intent intent = new Intent(sign_up.this, sign_in.class);
        startActivity(intent);
    }


}