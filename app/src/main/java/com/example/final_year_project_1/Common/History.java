package com.example.final_year_project_1.Common;

import com.example.final_year_project_1.Helper_Classes.History_Helper_Class;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class History {
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    public History(String text) {

        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference().child("Search_History");
        reference.push().setValue(text);
    }
}

