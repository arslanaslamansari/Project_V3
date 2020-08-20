package com.example.final_year_project_1.Common;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_year_project_1.Helper_Classes.Favorite_Adapter;
import com.example.final_year_project_1.Helper_Classes.MyAdapter;
import com.example.final_year_project_1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView fav_recyclerView;
    private List<FavoriteData> Favlist;
    String userId;
    FirebaseAuth firebaseAuth;

    //ImageView btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
        fav_recyclerView = findViewById(R.id.Fav_recycleview);
        //btn=findViewById(R.id.deletefavorite)
        Favlist = new ArrayList<FavoriteData>();


        fav_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("favorite");
        //String userId = reference.push().getKey();

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Favlist.clear();
                for (DataSnapshot ds : snapshot.child(userId).getChildren()) {
                    String Favorite = ds.child("fav").getValue().toString();
                    String uri = ds.child("uri").getValue().toString();
                    FavoriteData favoriteData = new FavoriteData(Favorite, uri);
                    Favlist.add(favoriteData);
                }
                Favorite_Adapter favadapter = new Favorite_Adapter(Favlist, Favorite.this);
                fav_recyclerView.setAdapter(favadapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Favorite.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void deletefav(final String name) {

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                                         for (DataSnapshot ds: dataSnapshot.child(userId).getChildren()) {
                                                            // appleSnapshot.getRef().removeValue();

                                                         }
                                                     }

                                                     @Override
                                                     public void onCancelled(DatabaseError databaseError) {
                                                         //  Log.e(TAG, "onCancelled", databaseError.toException());
                                                     }
                                                 });
//        Toast.makeText(Favorite.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
    }*/

    public void favoritebackbtn(View view) {

        finish();
    }
}
