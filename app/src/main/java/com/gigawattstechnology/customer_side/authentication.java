package com.gigawattstechnology.customer_side;

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

public class authentication extends AppCompatActivity {
    EditText Email,Password;
    ProgressBar progressBar;
    TextView resetpassword,createaccount;
    FirebaseAuth fAuth;
    ImageView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar2);
        resetpassword=findViewById(R.id.forgotpassword);
        fAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.login);
        createaccount=findViewById(R.id.createaccount);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(authentication.this,signup.class);
                startActivity(i);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Email.setError("* Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Password.setError("* Password is required");
                    return;
                }
                if(password.length()<8)
                {
                    Password.setError("* Password should contain at least 8 characters");
                    return;
                }
                authtransfer.storeauth(email.substring(0,email.indexOf("@")).replace("/","").replace(".",""));
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(authentication.this,email.substring(0,email.indexOf("@"))+" is Successfully Logged IN",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(authentication.this,Market.class);
                            i.putExtra("profile",email.substring(0,email.indexOf("@")));
                            startActivity(i);

                        }else{
                            Toast.makeText(authentication.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });
            }
        });

        resetpassword.setOnClickListener(new View.OnClickListener() {
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
}