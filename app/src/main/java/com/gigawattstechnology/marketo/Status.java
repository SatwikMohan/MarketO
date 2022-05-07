package com.gigawattstechnology.marketo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Status extends AppCompatActivity {
ImageView open,close;
DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        open=findViewById(R.id.open);
        close=findViewById(R.id.close);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(".","").replace("/","").replace(" ","").replace(",","")).child("Status");
                database.setValue("Open");
                Intent i=new Intent(Status.this,workspace.class);
                startActivity(i);
                Toast.makeText(Status.this, "Store Set as Open", Toast.LENGTH_LONG).show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance().getReference(authtransfer.givesupermarket()).child(authtransfer.giveaddress().replace(".","").replace("/","").replace(" ","").replace(",","")).child("Status");
                database.setValue("Close");
                Intent i=new Intent(Status.this,workspace.class);
                startActivity(i);
                Toast.makeText(Status.this, "Store Set as Close", Toast.LENGTH_LONG).show();
            }
        });
    }
}