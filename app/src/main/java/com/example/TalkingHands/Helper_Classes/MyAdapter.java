package com.example.TalkingHands.Helper_Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TalkingHands.Database.HistoryData;
import com.example.TalkingHands.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<HistoryData>Data;

    private Context context;
    public MyAdapter(List<HistoryData> listData, Context context) {
        this.Data = listData;
       // this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryData ld=Data.get(position);
        holder.textView.setText(ld.getHist());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

/*    private HistoryData[] listdata;

    // RecyclerView recyclerView;
    public MyAdapter(HistoryData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.history_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HistoryData myListData = listdata[position];
        holder.textView.setText(listdata[position].getHist());
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.history_textview);
        }
    }
}
