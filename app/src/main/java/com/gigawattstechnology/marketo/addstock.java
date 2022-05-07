package com.gigawattstechnology.marketo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class addstock extends AppCompatActivity {
DatabaseReference databaseReference;
long l;
Button addtostock;
FloatingActionButton addmore;
Context context;
EditText stockitem;
ConstraintLayout list;
ArrayList<String> item=new ArrayList<>();
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
        databaseReference= FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress()).child("Added Stock");
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

    }
    private void addView() {
        final View aa=getLayoutInflater().inflate(R.layout.additem,null,false);
        ImageView remove=aa.findViewById(R.id.remove);
        remove.setVisibility(View.VISIBLE);
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