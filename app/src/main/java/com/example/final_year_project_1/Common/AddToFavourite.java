package com.example.final_year_project_1.Common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToFavourite {
    FirebaseDatabase database;
    DatabaseReference reference;

    public AddToFavourite(String text) {
        FavoriteData u = new FavoriteData(text);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("favorite");
        String favId = reference.push().getKey();
        reference.child(favId).setValue(u);

        /*FavoriteData u = new FavoriteData(text);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Favorite");
        String favId = reference.push().getKey();
        reference.child(favId).setValue(u);*/
    }
}
