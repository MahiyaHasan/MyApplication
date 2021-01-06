package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email_et, password_et;
    Button login_bt,signip_bt;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findview
        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);
        login_bt = findViewById(R.id.login_bt);
        signip_bt = findViewById(R.id.signup_bt);

        fAuth=FirebaseAuth.getInstance();


        ////intent


        login_bt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String Email=email_et.getText().toString().trim();
                String password=password_et.getText().toString().trim();
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
                fAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"LOGGED IN",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Main1Activity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"ERROR OCCURED"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });
        signip_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);

                startActivity(intent);

            }

        });


    }
}
