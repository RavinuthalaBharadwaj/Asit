package com.Audisankara.asit.Adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.NotificationModel;
import com.Audisankara.asit.R;
import com.Audisankara.asit.helper.RecylerViewInterface;

import java.util.ArrayList;

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.ViewHolder> {

    private final RecylerViewInterface recylerViewInterface;
    private ArrayList<NotificationModel> notificationAdaptorArrayList;
    public NotificationAdaptor(ArrayList<NotificationModel> notificationModelArrayList, RecylerViewInterface recylerViewInterface) {
        this.notificationAdaptorArrayList = notificationModelArrayList;
        this.recylerViewInterface = recylerViewInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<NotificationModel> filterlist) {
        notificationAdaptorArrayList = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notificationmodel_lyt,parent,false);
        ViewHolder evh = new ViewHolder(view,recylerViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
        NotificationModel model = notificationAdaptorArrayList.get(position);
        animation.setDuration(600);
        holder.itemView.startAnimation(animation);
        holder.NotificationName.setText(model.getTitle());
        holder.NotificationDes.setText(model.getDescription());
        holder.NotificationDate.setText(model.getDate());
        holder.NotificationMonth.setText(model.getMonth());
    }

    @Override
    public int getItemCount() {
        return notificationAdaptorArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView NotificationName,NotificationDes,NotificationDate,NotificationMonth;
        public ViewHolder(View itemview,RecylerViewInterface recylerViewInterface) {
            super(itemview);
            Context context = itemview.getContext();
            NotificationName = itemview.findViewById(R.id.NotificationTitle);
            NotificationDes = itemview.findViewById(R.id.NotificationDescription);
            NotificationDate = itemview.findViewById(R.id.TvDate);
            NotificationMonth = itemview.findViewById(R.id.TvMonth);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recylerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recylerViewInterface.onItemClick(position,context);
                        }
                    }
                }
            });
        }
    }
}
