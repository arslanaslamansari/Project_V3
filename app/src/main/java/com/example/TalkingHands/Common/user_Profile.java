package com.example.TalkingHands.Common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TalkingHands.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class user_Profile extends AppCompatActivity {

    private static final int GALLERY_INTENT_CODE = 1023;
    TextView fullName, email, phone, verifyMsg, username;
    FirebaseAuth fAuth;
    //FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseFirestore fStore;
    String userId;
    Button resendCode;
    Button resetPassword, logout_btn;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        username = findViewById(R.id.username);
        fullName = findViewById(R.id.Name);
        email = findViewById(R.id.email);

        //fullName.setText("ok");

        resendCode = findViewById(R.id.resendCode);
        //logout_btn=findViewById(R.id.logoutbutton);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Toast.makeText(getApplicationContext(), "you're loged in ", Toast.LENGTH_SHORT).show();
            userId = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();
            access_user_data();


        } else {
            Intent i = new Intent(this, sign_in.class);
            startActivity(i);
            finish();
        }





/*
        if (fAuth.getInstance().getCurrentUser() != null) {
            Toast.makeText(getApplicationContext(), "you're loged in ", Toast.LENGTH_SHORT).show();
            userId = fAuth.getCurrentUser().getUid();
            user = fAuth.getCurrentUser();
        }
        else{
            Intent i = new Intent(this, sign_in.class);
            startActivity(i);
        }
*/


//email not verified
        if (firebaseUser != null) {

            if (!user.isEmailVerified()) {
                resendCode.setVisibility(View.VISIBLE);

                resendCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("tag", "onFailure: Email not sent " + e.getMessage());
                            }
                        });
                    }
                });
            }
        }

    }

    private void access_user_data() {


        /*DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //assert documentSnapshot != null;
                assert documentSnapshot != null;
                if (documentSnapshot.exists()) {
                    // phone.setText(documentSnapshot.getString("phone"));
                    fullName.setText(documentSnapshot.getString("fName"));
                    email.setText(documentSnapshot.getString("email"));
                } else {
                    Toast.makeText(getApplicationContext(), "details don't exist ", Toast.LENGTH_SHORT).show();
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });*/

        //It is not crashing on logout.
        //https://codinginflow.com/tutorials/android/cloud-firestore/part-3-get-document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference noteRef = db.collection("users").document(userId);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            //String title = documentSnapshot.getString(KEY_TITLE);
                            // String description = documentSnapshot.getString(KEY_DESCRIPTION);
                            //Map<String, Object> note = documentSnapshot.getData();
                            //textViewData.setText("Title: " + title + "\n" + "Description: " + description);
                            fullName.setText(documentSnapshot.getString("fName"));
                            email.setText(documentSnapshot.getString("email"));
                            username.setText(documentSnapshot.getString("username"));
                        } else {
                            Toast.makeText(user_Profile.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(user_Profile.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /*    @Override
        protected void onResume() {
            super.onResume();
            fAuth.addAuthStateListener(mAuthListener);
        }*/


    public void logout(View view) {
        finish();
        FirebaseAuth.getInstance().signOut();//logout
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void profile_backbtn(View view){
        finish();
    }


}
