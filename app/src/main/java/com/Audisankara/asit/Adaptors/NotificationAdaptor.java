package com.Audisankara.asit.Adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.NotificationModel;
import com.Audisankara.asit.Models.SearchModel;
import com.Audisankara.asit.R;
import com.Audisankara.asit.helper.RecylerViewInterface;
import com.bumptech.glide.Glide;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

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
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        NotificationModel model = notificationAdaptorArrayList.get(position);
        animation.setDuration(600);
        holder.itemView.startAnimation(animation);
        Link link = new Link("click here")
                .setTextColor(Color.parseColor("#259B24"))                  // optional, defaults to holo blue
                .setTextColorOfHighlightedLink(Color.parseColor("#0D3D0C")) // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)                                       // optional, defaults to true
                .setBold(true);
        LinkBuilder.on(holder.NotificationName).addLink(link).build();
        holder.NotificationName.setText(model.getNotificationTitle());
    }

    @Override
    public int getItemCount() {
        return notificationAdaptorArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView NotificationName;
        public ViewHolder(View itemview,RecylerViewInterface recylerViewInterface) {
            super(itemview);
            Context context = itemview.getContext();
            NotificationName = itemview.findViewById(R.id.NotificationTitle);
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
