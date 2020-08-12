package com.example.final_year_project_1.Common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHistory {
    FirebaseDatabase database;
    DatabaseReference reference;

    public AddHistory(String text) {
        HistoryData u = new HistoryData(text);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("history");
        String userId = reference.push().getKey();
        reference.child(userId).setValue(u);

    }
}
