package com.example.TalkingHands.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TalkingHands.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in extends AppCompatActivity {
    // EditText user_email,user_password;
    Button signin_Btn, signup_btn, forgot_password;
    TextView mCreateBtn, forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextInputLayout user_email, user_password;
    public static final String TAG = "TAG";

    public static sign_in obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        user_email = findViewById(R.id.signin_email);
        user_password = findViewById(R.id.sign_in_password);

        signin_Btn = findViewById(R.id.signin_button);
        signup_btn = findViewById(R.id.signup_button_on_login);

        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        forgot_password = findViewById(R.id.forget_password);

        obj = this;


        signin_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String email = user_email.getEditText().getText().toString().trim();
                String password = user_password.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    user_email.setError("Email is Required.");
                } else if (TextUtils.isEmpty(password)) {
                    user_password.setError("Password is Required.");
                } else {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(sign_in.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), user_Profile.class);
                                //progressBar.setVisibility(View.GONE);

                                startActivity(i);
                                finish();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                // Toast.makeText(sign_in.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                Toast.makeText(sign_in.this, "try again", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sign_up.class));
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());


                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Enter Your Email");
                //passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(sign_in.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(sign_in.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });


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

    public void back_btn_login(View view) {
        finish();
    }
}