/*package com.example.final_year_project_1.Admin;

import android.content.Context;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_year_project_1.Common.History;
import com.example.final_year_project_1.Helper_Classes.History_Helper_Class;
import com.example.final_year_project_1.R;

import java.util.List;

public class user_adapter extends RecyclerView.Adapter<user_adapter.MyViewHolder> {

    private Context mcontext;
    private List<History_Helper_Class> mdatalist;

    public user_adapter(Context mcontext, List<History_Helper_Class> mdatalist) {
        this.mcontext = mcontext;
        this.mdatalist = mdatalist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview= LayoutInflater.from(mcontext).inflate(R.layout.history_items,parent,false);
        return new MyViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        History_Helper_Class history_helper_class=mdatalist(position);
        holder.textView.setText(history_helper_class.getText());
    }

    @Override
    public int getItemCount() {
        return mdatalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.history_textview);
        }
    }
}

 */
