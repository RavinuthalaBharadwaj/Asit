package com.Audisankara.asit.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Models.ReceiptModel;
import com.Audisankara.asit.R;

import java.util.ArrayList;

public class ReceiptAdaptor extends RecyclerView.Adapter<ReceiptAdaptor.viewHolder> {

    private ArrayList<ReceiptModel> ReceiptModelArrayList;

    public ReceiptAdaptor(ArrayList<ReceiptModel> ReceiptModelArrayList1){
        this.ReceiptModelArrayList = ReceiptModelArrayList1;
    }
    @NonNull
    @Override
    public ReceiptAdaptor.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_model_lyt,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdaptor.viewHolder holder, int position) {
        ReceiptModel model = ReceiptModelArrayList.get(position);;
    }

    @Override
    public int getItemCount() {
        return ReceiptModelArrayList.size();
    }

    protected static class viewHolder extends RecyclerView.ViewHolder {

        private TextView OrderId;
        private TextView ReceiptName;
        private TextView Time;
        private TextView Status;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            OrderId = itemView.findViewById(R.id.OrderIdInReceipt);
            ReceiptName = itemView.findViewById(R.id.ReceiptName);
            Time = itemView.findViewById(R.id.ReceiptDate);
            Status = itemView.findViewById(R.id.ReceiptStatus);
        }
    }
}
