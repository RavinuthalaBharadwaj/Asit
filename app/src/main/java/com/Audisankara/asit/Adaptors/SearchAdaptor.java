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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.MaterialModel;
import com.Audisankara.asit.R;
import com.Audisankara.asit.Models.SearchModel;
import com.Audisankara.asit.helper.RecylerViewInterface;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.ViewHolder> {

    private final RecylerViewInterface recylerViewInterface;
    private ArrayList<SearchModel> SearchModelArrayList;
    public SearchAdaptor(ArrayList<SearchModel> SearchModelArrayList1,RecylerViewInterface recylerViewInterface) {
        this.SearchModelArrayList = SearchModelArrayList1;
        this.recylerViewInterface = recylerViewInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<SearchModel> filterlist) {
        SearchModelArrayList = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchmodel_lyt,parent,false);
        ViewHolder evh = new ViewHolder(view,recylerViewInterface);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        SearchModel model = SearchModelArrayList.get(position);
        animation.setDuration(600);
        holder.itemView.startAnimation(animation);
        holder.Name.setText(model.getName());
        holder.Description.setText(model.getDescription());
        holder.CvCategory.setCardBackgroundColor(model.getCategoryCardColor());
        holder.TvCategory.setText(model.getCategory());

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
        private final CardView CvCategory;
        private final TextView TvCategory;
        public ViewHolder(View itemview,RecylerViewInterface recylerViewInterface) {
            super(itemview);
            Context context = itemview.getContext();
            Name = itemview.findViewById(R.id.SearchViewName);
            Description = itemview.findViewById(R.id.SearcViewDescription);
            image = itemview.findViewById(R.id.SearchViewImage);
            CvCategory = itemview.findViewById(R.id.CvCategory);
            TvCategory = itemview.findViewById(R.id.TvCategory);
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
