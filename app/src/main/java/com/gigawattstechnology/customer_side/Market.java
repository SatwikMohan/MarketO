package com.gigawattstechnology.customer_side;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Market extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference databaseReference;
ArrayList<String> listmarket=new ArrayList<>();
ArrayList<String> listaddress=new ArrayList<>();
TextView stat;
Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        /*recyclerView=findViewById(R.id.recycler);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Supermarkets");
        recyclerView.setHasFixedSize(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    listmarket.add(postSnapshot.getKey());
                    databaseReference.child(postSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            listaddress.add(snapshot.getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Set<String> lm=new HashSet<>(listmarket);
        Set<String> la=new HashSet<>(listaddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(lm,la);
        recyclerView.setAdapter(recyclerAdapter);*/
        stat=findViewById(R.id.stat);
        start=findViewById(R.id.start);
        databaseReference=FirebaseDatabase.getInstance().getReference("Big Bazar").child("Delhigateagra").child("Status");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stat.setText("Big Bazar is "+snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Market.this,stock.class);
                startActivity(i);
            }
        });
    }
}