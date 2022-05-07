package com.gigawattstechnology.marketo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapter.RemoveAdapter;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Item> list;

    public MyAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item= list.get(position);
        holder.delete.setImageResource(R.drawable.delete);
        holder.itemname.setText(item.getItemname());
        holder.itemhead.setText(item.getItemhead());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemhead,itemname;
        ImageView delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemhead=itemView.findViewById(R.id.itemhead);
            itemname=itemView.findViewById(R.id.itemname);
            delete=itemView.findViewById(R.id.deleteitem);
        }
    }
}
