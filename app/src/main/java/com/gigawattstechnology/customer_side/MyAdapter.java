package com.gigawattstechnology.customer_side;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Set;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerViewHolder> {
    Set<String> item;
    public MyAdapter(Set<String> item) {
        this.item = item;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.recyclerview_availablestock;
    }
    @NonNull
    @Override
    public MyAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.RecyclerViewHolder holder, int position) {

        holder.dec.setText("-");
        holder.inc.setText("+");
        String[] Name = item.toArray(new String[item.size()]);
        holder.avail.setText(Name[position]);
        holder.quantity.setText("0");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView avail,quantity;
        Button dec,inc;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            avail=itemView.findViewById(R.id.avail);
            quantity=itemView.findViewById(R.id.quantity);
            dec=itemView.findViewById(R.id.decrement);
            inc=itemView.findViewById(R.id.increment);
        }
    }
}
