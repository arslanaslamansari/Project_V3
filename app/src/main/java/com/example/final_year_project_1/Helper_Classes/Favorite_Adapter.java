package com.example.final_year_project_1.Helper_Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_year_project_1.Common.FavoriteData;
import com.example.final_year_project_1.Common.HistoryData;
import com.example.final_year_project_1.R;

import java.util.List;

public class Favorite_Adapter extends RecyclerView.Adapter<Favorite_Adapter.FavViewHolder>{

    private List<FavoriteData> FavData;

    private Context context;
    public Favorite_Adapter(List<FavoriteData> listData, Context context) {
        this.FavData = listData;
        // this.context=context;
    }

    @NonNull
    @Override
    public Favorite_Adapter.FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false);
        return new Favorite_Adapter.FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Favorite_Adapter.FavViewHolder holder, int position) {
        FavoriteData ld=FavData.get(position);
        holder.textView.setText(ld.getfav());
    }

    @Override
    public int getItemCount() {
        return FavData.size();
    }


    public class FavViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public FavViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.Favorite_textview);
        }
    }
}
