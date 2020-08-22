package com.example.TalkingHands.Common;

import com.example.TalkingHands.Database.FavoriteData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
