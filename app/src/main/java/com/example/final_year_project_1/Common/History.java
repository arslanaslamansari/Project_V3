package com.example.final_year_project_1.Common;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_year_project_1.Admin.user_Profile;
import com.example.final_year_project_1.Helper_Classes.MyAdapter;
import com.example.final_year_project_1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    private List<HistoryData> list;
    FirebaseUser userid;
    FirebaseAuth fauth;
    String userId;
    FirebaseAuth firebaseAuth;

    //FirebaseDatabase firebaseDatabase;
    /*tetorial
    https://www.youtube.com/watch?v=up681IwI_6k&list=LLGmmLWGg7bKooAtg5SOGOGg&index=4&t=298s*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        recyclerView = findViewById(R.id.recycleview);
        list = new ArrayList<HistoryData>();

        //LinearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //userid=auth.getCurrentUser()
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("history");
        final String key = reference.push().getKey();

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        /*HistoryData[] hd = new HistoryData[]{new HistoryData("email"), new HistoryData("arskan"), new HistoryData("usman")};
        MyAdapter adapter = new MyAdapter(hd);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.child(userId).getChildren()) {
                    String history = ds.child("hist").getValue().toString();
                    HistoryData historyData = new HistoryData(history);
                    list.add(historyData);
                }
                MyAdapter myAdapter = new MyAdapter(list, History.this);
                recyclerView.setAdapter(myAdapter);
            }
/*noteRef.get()

            addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess (DocumentSnapshot documentSnapshot){
                    if (documentSnapshot.exists()) {
                        //String title = documentSnapshot.getString(KEY_TITLE);
                        // String description = documentSnapshot.getString(KEY_DESCRIPTION);
                        //Map<String, Object> note = documentSnapshot.getData();
                        //textViewData.setText("Title: " + title + "\n" + "Description: " + description);
                        fullName.setText(documentSnapshot.getString("fName"));
                        email.setText(documentSnapshot.getString("email"));
                    } else {
                        Toast.makeText(user_Profile.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            })
                    .

            addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure (@NonNull Exception e){
                    Toast.makeText(user_Profile.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });*/
            //https://www.javatpoint.com/android-recyclerview-list-example
          /*@Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                   // HistoryData historyData= dataSnapshot1.getValue(HistoryData.class);
                   // list.add(historyData);
                    Toast.makeText(History.this, "Done", Toast.LENGTH_SHORT).show();

                }
                MyAdapter adapter = new MyAdapter(list,History.this);
                recyclerView.setAdapter(adapter);
            }*/

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(History.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*    public void Add_History(String text) {
     *//*HistoryData user = new HistoryData("Ravi Tamada");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Search_History");
        String userId = reference.push().getKey();
        reference.setValue(text);*//*

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Search_History");
        reference.push().setValue(text);
    }*/
}
