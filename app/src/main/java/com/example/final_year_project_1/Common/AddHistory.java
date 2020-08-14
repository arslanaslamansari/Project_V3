package com.example.final_year_project_1.Common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHistory {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String userId;
    FirebaseUser user;
    public AddHistory(String text) {
        HistoryData u = new HistoryData(text);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("history");
        String key = reference.push().getKey();
        reference.child(userId).child(key).setValue(u);

    }
}
