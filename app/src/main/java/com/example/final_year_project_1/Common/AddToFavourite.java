package com.example.final_year_project_1.Common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToFavourite {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String userId;

    public AddToFavourite(String text) {

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        FavoriteData u = new FavoriteData(text);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("favorite");
        String key = reference.push().getKey();
        reference.child(userId).child(key).setValue(u);

        /*FavoriteData u = new FavoriteData(text);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Favorite");
        String favId = reference.push().getKey();
        reference.child(favId).setValue(u);*/
    }
}
