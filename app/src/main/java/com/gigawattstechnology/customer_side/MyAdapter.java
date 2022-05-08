package com.gigawattstechnology.customer_side;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        holder.save.setText("save");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView avail,quantity;
        Button dec,inc,save;
        int i=0;
        int a=0;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            avail=itemView.findViewById(R.id.avail);
            quantity=itemView.findViewById(R.id.quantity);
            dec=itemView.findViewById(R.id.decrement);
            inc=itemView.findViewById(R.id.increment);
            save=itemView.findViewById(R.id.save);
            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i > 0) {
                        i--;
                        quantity.setText(""+i);
                    }
                }
            });
            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i++;
                    quantity.setText(""+i);
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(authtransfer.giveauth()).child("date");
                    String k = databaseReference.push().getKey();
                        save.setText("undo");
                        databaseReference.child(k).child("Name").setValue(avail.getText().toString());
                        databaseReference.child(k).child("quantity").setValue(quantity.getText().toString());
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            save.setText("save");
                            databaseReference.child(k).removeValue();
                        }
                    });
                }
            });
        }

    }
}
