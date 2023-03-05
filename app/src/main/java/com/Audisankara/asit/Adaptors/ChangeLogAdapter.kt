package com.Audisankara.asit.Adaptors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Audisankara.asit.Models.NotificationModel
import com.Audisankara.asit.R

class ChangeLogAdapter(
    private var notificationAdaptorArrayList: ArrayList<NotificationModel>,
) : RecyclerView.Adapter<ChangeLogAdapter.ViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterlist: ArrayList<NotificationModel>) {
        notificationAdaptorArrayList = filterlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notificationmodel_lyt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        val model = notificationAdaptorArrayList[position]
        animation.duration = 600
        holder.itemView.startAnimation(animation)
        holder.NotificationName.text = model.title
        holder.NotificationDes.text = model.description
        holder.NotificationDate.text = model.date
        holder.NotificationMonth.text = model.month
    }

    override fun getItemCount(): Int {
        return notificationAdaptorArrayList.size
    }

    class ViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        val NotificationName: TextView
        val NotificationDes: TextView
        val NotificationDate: TextView
        val NotificationMonth: TextView

        init {
            NotificationName = itemview.findViewById(R.id.NotificationTitle)
            NotificationDes = itemview.findViewById(R.id.NotificationDescription)
            NotificationDate = itemview.findViewById(R.id.TvDate)
            NotificationMonth = itemview.findViewById(R.id.TvMonth)
        }
    }
}