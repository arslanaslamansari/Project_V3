package com.example.final_year_project_1.Common;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.final_year_project_1.Helper_Classes.Favorite_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddToFavourite {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    public AddToFavourite(final String text, String uri) {
        FavoriteData u = new FavoriteData(text,uri);
        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("favorite");
        String key = reference.push().getKey();
        reference.child(userId).child(text).setValue(u);

    }
}
