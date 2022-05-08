package com.gigawattstechnology.customer_side;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<MarketClass> list;

    public RecyclerAdapter(Context context, ArrayList<MarketClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_market,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MarketClass marketClass=list.get(position);
        holder.marketname.setText(marketClass.getName());
        holder.marketaddress.setText(marketClass.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView marketname,marketaddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marketaddress=itemView.findViewById(R.id.marketaddress);
            marketname=itemView.findViewById(R.id.marketname);
        }
    }
}
