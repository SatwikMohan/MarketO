package com.gigawattstechnology.marketo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

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

public class removestock extends AppCompatActivity {
DatabaseReference databaseReference;
long l;
Button removethestock;
FloatingActionButton removemore;
Context context;
EditText stockitem;
LinearLayout list2;
ArrayList<String> item=new ArrayList<>();
long maxentry=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removestock);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        /*recyclerView=findViewById(R.id.recyclerview);
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
        });*/
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
        removemore=findViewById(R.id.removemore);
        removethestock=findViewById(R.id.removethestock);
        list2=findViewById(R.id.list2);
        context=this;
        databaseReference= FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(",","").replace(" ","").replace("/","")).child("Removed Stock");
        removemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });
        removethestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                            maxentry=snapshot.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                for(int i=0;i<list2.getChildCount();i++){
                    if(list2.getChildAt(i) instanceof LinearLayoutCompat){
                        LinearLayoutCompat l=(LinearLayoutCompat) list2.getChildAt(i);
                        for(int j=0;j<l.getChildCount();j++){
                            if(l.getChildAt(j) instanceof EditText){
                                EditText e=(EditText) l.getChildAt(j);
                                if(e.getId()==R.id.item2){
                                    //Toast.makeText(context, e.getText().toString(), Toast.LENGTH_SHORT).show();
                                    if(e.getText().toString()!=""){
                                        item.add(e.getText().toString());
                                        // databaseReference.child("item"+maxentry+j).setValue(e.getText().toString());
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i=1;i<item.size();i++){
                    long p=maxentry+i;
                    databaseReference.child("item"+p).child("itemname").setValue(item.get(i));
                    databaseReference.child("item"+p).child("itemhead").setValue("item"+p);
                }
            }
        });

    }
    private void addView() {
        final View aa=getLayoutInflater().inflate(R.layout.removeitem,null,false);
        ImageView remove=aa.findViewById(R.id.remove2);
        EditText moreitem=aa.findViewById(R.id.item2);
        remove.setVisibility(View.VISIBLE);
        moreitem.setVisibility(View.VISIBLE);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(aa);
            }
        });
        list2.addView(aa);
    }

    private void removeView(View aa) {
        list2.removeView(aa);
    }

}