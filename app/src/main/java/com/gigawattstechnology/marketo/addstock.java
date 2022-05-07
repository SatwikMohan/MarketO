package com.gigawattstechnology.marketo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addstock extends AppCompatActivity {
DatabaseReference databaseReference;
long l;
Button addtostock;
FloatingActionButton addmore;
Context context;
EditText stockitem;
LinearLayout list;
ArrayList<String> item=new ArrayList<>();
long maxentry=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstock);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        addmore=findViewById(R.id.addmore);
        addtostock=findViewById(R.id.addtostock);
        stockitem=findViewById(R.id.item);
        list=findViewById(R.id.list);
        context=this;
        databaseReference= FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(",","").replace(" ","").replace("/","")).child("Added Stock");
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

        addtostock.setOnClickListener(new View.OnClickListener() {
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
                for(int i=0;i<list.getChildCount();i++){
                    if(list.getChildAt(i) instanceof LinearLayoutCompat){
                        LinearLayoutCompat l=(LinearLayoutCompat) list.getChildAt(i);
                        for(int j=0;j<l.getChildCount();j++){
                            if(l.getChildAt(j) instanceof EditText){
                                EditText e=(EditText) l.getChildAt(j);
                                if(e.getId()==R.id.item){
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
        final View aa=getLayoutInflater().inflate(R.layout.additem,null,false);
        ImageView remove=aa.findViewById(R.id.remove);
        EditText moreitem=aa.findViewById(R.id.item);
        remove.setVisibility(View.VISIBLE);
        moreitem.setVisibility(View.VISIBLE);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(aa);
            }
        });
        list.addView(aa);
    }

    private void removeView(View aa) {
        list.removeView(aa);
    }
}