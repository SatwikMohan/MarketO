package com.gigawattstechnology.customer_side;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Set;

public class Unavail extends RecyclerView.Adapter<Unavail.RecyclerViewHolder>{
    Set<String> item;
    public Unavail(Set<String> item) {
        this.item = item;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.recyclerview_unavailablestock;
    }
    @NonNull
    @Override
    public Unavail.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new Unavail.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Unavail.RecyclerViewHolder holder, int position) {
        String[] Name = item.toArray(new String[item.size()]);
        holder.unavail.setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView unavail;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            unavail=itemView.findViewById(R.id.unavail);
        }
    }
}
