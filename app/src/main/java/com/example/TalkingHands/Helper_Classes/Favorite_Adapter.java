package com.example.TalkingHands.Helper_Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TalkingHands.Common.Fav_Item_videoplayer;
import com.example.TalkingHands.Database.FavoriteData;
import com.example.TalkingHands.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Favorite_Adapter extends RecyclerView.Adapter<Favorite_Adapter.FavViewHolder>{

    private List<FavoriteData> FavData;
    String uri,key,userId;



    private Context context;
    public Favorite_Adapter(List<FavoriteData> listData, Context context) {
        this.FavData = listData;
         this.context=context;
    }

    @NonNull
    @Override
    public Favorite_Adapter.FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false);
        return new Favorite_Adapter.FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Favorite_Adapter.FavViewHolder holder, final int position) {
        FavoriteData ld=FavData.get(position);
        holder.textView.setText(ld.getfav());
        uri=FavData.get(position).geturi();
        key=FavData.get(position).getfav();
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Recycle Click"+position, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, Fav_Item_videoplayer.class);
                i.putExtra("VideoUri", FavData.get(position).geturi());
                context.startActivity(i);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Recycle Click"+position, Toast.LENGTH_SHORT).show();
                FirebaseDatabase database;
                DatabaseReference reference;
                FirebaseAuth firebaseAuth;

                firebaseAuth = FirebaseAuth.getInstance();
                userId = firebaseAuth.getCurrentUser().getUid();


                database = FirebaseDatabase.getInstance();
                reference = database.getReference().child("favorite");
                reference.child(userId).child(FavData.get(position).getfav()).removeValue();
                FavData.remove(position);
                notifyDataSetChanged();

                //FirebaseStorage storage = FirebaseStorage.getInstance();
        }
        });

        /*holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "long Click" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return FavData.size();
    }


    public class FavViewHolder extends RecyclerView.ViewHolder{
        private TextView textView,textView1;
        ImageView deletebtn;
        String uri;

        public FavViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.Favorite_textview);
            deletebtn=(ImageView)itemView.findViewById(R.id.deletefavorite);
        }
    }
}
