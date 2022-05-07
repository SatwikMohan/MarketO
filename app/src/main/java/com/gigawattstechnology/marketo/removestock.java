package com.gigawattstechnology.marketo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import adapter.RemoveAdapter;

public class removestock extends AppCompatActivity {
DatabaseReference databaseReference;
/*long l;
Button removethestock;
FloatingActionButton removemore;
Context context;
EditText stockitem;
LinearLayout list2;
ArrayList<String> itemhead=new ArrayList<>();
ArrayList<String> itemname=new ArrayList<>();
long maxentry=0;*/
RecyclerView recyclerView;
MyAdapter myAdapter;
ArrayList<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removestock);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerview);

        databaseReference=FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(",","").replace(" ","").replace("/","")).child("Stock");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Item item=dataSnapshot.getValue(Item.class);
                    list.add(item);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(",","").replace(" ","").replace("/","")).child("Stock");
        databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    itemhead.add(postSnapshot.getKey());
                    DatabaseReference v=(DatabaseReference) FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(",","").replace(" ","").replace("/","")).child("Stock").child(postSnapshot.getKey());
                    v.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            itemname.add(snapshot.getValue(String.class));
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
        });*/

       /* Set<String> ih=new HashSet<>(itemhead);
        Set<String> in=new HashSet<>(itemname);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(removestock.this));
        RemoveAdapter removeAdapter = new RemoveAdapter(ih,in);
        recyclerView.setAdapter(removeAdapter);*/
    }

}