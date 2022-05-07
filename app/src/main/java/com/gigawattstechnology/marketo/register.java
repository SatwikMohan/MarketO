package com.gigawattstechnology.marketo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText supermarketname,managername,emailid,setpassword,confirmpassword,address;
    FirebaseAuth fAuth;
    ImageView register;
    ProgressBar progress;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        supermarketname=findViewById(R.id.supermarketnameup);
        managername=findViewById(R.id.managernameup);
        emailid=findViewById(R.id.emailidup);
        setpassword=findViewById(R.id.setpassword);
        address=findViewById(R.id.address);
        confirmpassword=findViewById(R.id.confirmpassword);
        fAuth=FirebaseAuth.getInstance();
        progress=findViewById(R.id.progressup);
        fAuth=FirebaseAuth.getInstance();
        register=findViewById(R.id.register);
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),authentication.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smn=supermarketname.getText().toString().trim();
                String mn=managername.getText().toString().trim();
                String email=emailid.getText().toString().trim();
                String password=setpassword.getText().toString().trim();
                String cpassword=confirmpassword.getText().toString().trim();
                String add=address.getText().toString().trim();
                if(!password.equals(cpassword))
                {
                    setpassword.setError("* Password and Confirm Password are not same");
                    confirmpassword.setError("* Password and Confirm Password are not same");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    emailid.setError("* Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    setpassword.setError("* Password is required");
                    return;
                }
                if(password.length()<8)
                {
                    setpassword.setError("* Password should contain at least 8 characters");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            databaseReference= FirebaseDatabase.getInstance().getReference(smn).child(add.replace(".","").replace("/","").replace(" ","").replace(",","")).child("Manager");
                            databaseReference.setValue(mn);
                            Toast.makeText(register.this," Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),authentication.class));

                        }else{
                            Toast.makeText(register.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}