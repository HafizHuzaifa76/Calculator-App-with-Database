package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<CalculationRecord> calculationRecordArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<CalculationRecord> calculationRecordArrayList) {
        this.context = context;
        this.calculationRecordArrayList = calculationRecordArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        CalculationRecord calculationRecord = calculationRecordArrayList.get(position);
        holder.recordTextView.setText(calculationRecord.getCalculateRecord() +" = " +calculationRecord.getAnswer());
    }

    @Override
    public int getItemCount() {
        return calculationRecordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recordTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recordTextView = itemView.findViewById(R.id.recordTextView);
        }
    }
}
