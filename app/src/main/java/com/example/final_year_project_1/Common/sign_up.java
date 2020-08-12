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

                if (validateFullName() | validateUsername() | validateEmail() | validatePassword()) {

                    final String email = user_email.getEditText().getText().toString().trim();
                    String password = user_password.getEditText().getText().toString().trim();
                    final String fullName = full_name.getEditText().getText().toString();
                    final String username = user_name.getEditText().getText().toString();


                    progressBar.setVisibility(View.VISIBLE);

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

                            } else {
                                Toast.makeText(sign_up.this, "Error ! ", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
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
        String val = user_name.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            user_name.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            user_name.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            user_name.setError("No White spaces are allowed!");
            return false;
        } else {
            user_name.setError(null);
            user_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = user_email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            user_email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            user_email.setError("Invalid Email!");
            return false;
        } else {
            user_email.setError(null);
            user_email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = user_password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                "$";

        if (val.isEmpty()) {
            user_password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            user_password.setError("Password should contain 4 characters!");
            return false;
        } else {
            user_password.setError(null);
            user_password.setErrorEnabled(false);
            return true;
        }
    }

    /*private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkspaces)) {
            phoneNumber.setError("No White spaces are allowed!");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }*/

    public void callLoginFromSignUp(View view) {
        Intent intent = new Intent(this, sign_in.class);
        startActivity(intent);

    }


}