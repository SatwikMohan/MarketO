package com.gigawattstechnology.customer_side;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class unavailablestocktab extends Fragment {
TextView unavail;
DatabaseReference databaseReference;
RecyclerView recyclerView;
long max;
ArrayList<String> itemname=new ArrayList<>();
FloatingActionButton createlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_unavailablestocktab, container, false);
        unavail=view.findViewById(R.id.unavail);
        createlist=view.findViewById(R.id.createlist);
        recyclerView=view.findViewById(R.id.recyclerunavailable);
        databaseReference= FirebaseDatabase.getInstance().getReference("Big Bazar").child("Delhigateagra").child("Removed Stock");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                max=snapshot.getChildrenCount();
                //Toast.makeText(getContext(), ""+max, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        long i=0;
        for(i=1;i<=max;i++){
            databaseReference.child("item"+i).child("itemname").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue(String.class)!=null)
                        itemname.add(snapshot.getValue(String.class));
                   // unavail.append(snapshot.getValue(String.class)+"\n");
                    //Toast.makeText(getContext(), snapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        Set<String> in=new HashSet<>(itemname);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Unavail unavail=new Unavail(in);
        recyclerView.setAdapter(unavail);
        createlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),shopping.class);
            }
        });
        return view;
    }
}