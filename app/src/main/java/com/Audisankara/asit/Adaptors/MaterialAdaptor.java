package com.Audisankara.asit.Adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.MaterialModel;
import com.Audisankara.asit.R;
import com.Audisankara.asit.Models.SearchModel;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class MaterialAdaptor extends RecyclerView.Adapter<MaterialAdaptor.ViewHolder> {

    private ArrayList<MaterialModel> SearchModelArrayList;
    public MaterialAdaptor(ArrayList<MaterialModel> SearchModelArrayList1) {
        this.SearchModelArrayList = SearchModelArrayList1;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<MaterialModel> filterlist) {
        SearchModelArrayList = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materialmodel_lyt,parent,false);
        ViewHolder evh = new ViewHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        MaterialModel model = SearchModelArrayList.get(position);
        animation.setDuration(900);
        holder.itemView.startAnimation(animation);
        holder.Name.setText(model.getName());
        holder.Description.setText(model.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(model.getImageId()).into(holder.image);
        // holder.image.setImageResource(model.getImageId());
    }

    @Override
    public int getItemCount() {
        return SearchModelArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView Name;
        private final TextView Description;
        private final ImageView image;

        public ViewHolder(View itemview) {
            super(itemview);
            Name = itemview.findViewById(R.id.SearchViewName);
            Description = itemview.findViewById(R.id.SearcViewDescription);
            image = itemview.findViewById(R.id.SearchViewImage);
        }
    }
}
