package com.example.pe_thithuu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pe_thithuu.R;
import com.example.pe_thithuu.model.Booking;

import java.util.List;

public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    Context context;
    List<Booking> list;

    public RecycleViewAdapter(Context context, List<Booking> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking food=list.get(position);
        holder.name.setText(food.getCustomerName());
        holder.id.setText(Integer.toString(position+ 1));
        holder.date.setText(food.getTime()+"");
//        double totalValue = food.getPrice() * food.getQuantity();
//        holder.value.setText(Double.toString(totalValue));
        holder.value.setText(Double.toString(food.getValue()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, date, value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.id);
            name= itemView.findViewById(R.id.CustorName);
            date= itemView.findViewById(R.id.date);
            value= itemView.findViewById(R.id.value);
        }
    }
}
