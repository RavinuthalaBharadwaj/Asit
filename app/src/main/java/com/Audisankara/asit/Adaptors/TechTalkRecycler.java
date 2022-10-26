package com.Audisankara.asit.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.TechTalkRecentModel;
import com.Audisankara.asit.R;

import java.util.ArrayList;

public class TechTalkRecycler extends RecyclerView.Adapter<TechTalkRecycler.viewholder>{
    private ArrayList<TechTalkRecentModel> dataClassArrayList;
    private Context ctx;
    private LayoutInflater inflater;

    public TechTalkRecycler(ArrayList<TechTalkRecentModel> dataClassArrayList,Context ctx) {
        this.dataClassArrayList = dataClassArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TechTalkRecycler.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recent_tech_talk,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechTalkRecycler.viewholder holder, int position) {
        TechTalkRecentModel dataClass = dataClassArrayList.get(position);
        holder.tvSubject.setText(dataClass.getTechTalkName());

    }

    @Override
    public int getItemCount() {
        return dataClassArrayList.size();
    }
    public static class viewholder extends RecyclerView.ViewHolder {
        private TextView tvSubject;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvSubject);
        }
    }
}
