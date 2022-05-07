package com.gigawattstechnology.marketo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class authentication extends AppCompatActivity {
TextView createaccount,forgotpassword;
EditText supermarketname,managername,emailid,password,address;
FirebaseAuth fAuth;
ImageView login;
ProgressBar progress;
String checkname=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        createaccount=findViewById(R.id.createaccount);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(authentication.this,register.class);
                startActivity(i);
            }
        });
        address=findViewById(R.id.addressin);
        forgotpassword=findViewById(R.id.forgotpassword);
        supermarketname=findViewById(R.id.supermarketname);
        managername=findViewById(R.id.managername);
        emailid=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        fAuth=FirebaseAuth.getInstance();
        progress=findViewById(R.id.progressin);

        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smn=supermarketname.getText().toString();
                String mn=managername.getText().toString();
                String email=emailid.getText().toString();
                String pword=password.getText().toString();
                String add=address.getText().toString();
                if(TextUtils.isEmpty(add)){
                    address.setError("* Address is Required");
                    return;
                }
                if(TextUtils.isEmpty(smn)){
                    supermarketname.setError("* Supermarket Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(mn)){
                    managername.setError("* Manager's Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    emailid.setError("* Email Id is Required");
                    return;
                }
                if(TextUtils.isEmpty(pword)){
                    password.setError("* Password is Required");
                    return;
                }
                authtransfer.storesupermarket(smn);
                authtransfer.storemanager(mn);
                authtransfer.storeaddress(add);
                authtransfer.storeauth(email.substring(0,email.indexOf("@")).replace("/","").replace(".",""));
                progress.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(smn).child(add.replace(".","").replace("/","").replace(" ","").replace(",","")).child("Manager");
                        databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.getValue(String.class)!=null) {
                                    checkname = snapshot.getValue(String.class);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        if (task.isSuccessful()) {
                           if(checkname.equals(mn)) {
                                Intent i = new Intent(authentication.this, workspace.class);
                                startActivity(i);
                            }else{
                               progress.setVisibility(View.INVISIBLE);
                               managername.setError("Incorrect name");
                               return;
                           }
                        }else{
                            Toast.makeText(authentication.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                forgotpassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText resetmail=new EditText(view.getContext());
                        final AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(view.getContext());
                        passwordResetDialog.setTitle("Reset Forgotten Password");
                        passwordResetDialog.setMessage("Enter your registered email "+resetmail.getText().toString().trim());
                        passwordResetDialog.setView(resetmail);
                        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String mail=resetmail.getText().toString().trim();
                                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(authentication.this,"Link sent successfully",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(authentication.this,"ERRORR!!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        passwordResetDialog.create().show();
                    }
                });
            }
        });
    }
}