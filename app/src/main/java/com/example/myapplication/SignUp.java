package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText fullName_et, email_et, password_et, retypePassword_et,username_et;
    Button signUp_bt;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullName_et = findViewById(R.id.fullName_et);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        retypePassword_et = findViewById(R.id.retypePassword_et);
        signUp_bt = findViewById(R.id.signUp_bt);
        email_et=findViewById(R.id.email_et);
        findViewById(R.id.signUp_bt);
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        signUp_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Email=email_et.getText().toString().trim();
                String password=password_et.getText().toString().trim();
                String username=username_et.getText().toString().trim();
                String retypepassword=retypePassword_et.getText().toString().trim();
                String fullname=fullName_et.getText().toString().trim();
                if(TextUtils.isEmpty(Email))
                {
                    email_et.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    email_et.setError("Password is required");
                    return;
                }
                if(password.length()<6)
                {
                    password_et.setError("Password must be at least 8 charecters long");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this,"REGISTRATION SUCCESSFUL",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Main1Activity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUp.this,"ERROR OCCURED"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });


    }
}
