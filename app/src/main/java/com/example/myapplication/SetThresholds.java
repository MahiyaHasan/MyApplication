package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetThresholds extends AppCompatActivity {


    EditText Temperature_tv, light_tv;
    EditText moisture1_et;
    Button setThresholds_bt;
    Button setThresholds_bt2;
    int m,l,t;
    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_thresholds);
        databaseReference= FirebaseDatabase.getInstance().getReference("Plant");
        Temperature_tv = findViewById(R.id.Temperature_tv);
        light_tv = findViewById(R.id.light_tv);
        moisture1_et = findViewById(R.id.moisture1_et);
        setThresholds_bt = findViewById(R.id.setThresholds_bt);
        setThresholds_bt2 = findViewById(R.id.setThresholds_bt2);

        setThresholds_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 saveData();
            }

        });
        setThresholds_bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);

            }

        });
    }
    public void saveData()
    {
        t=Integer.parseInt(Temperature_tv.getText().toString());
        m=Integer.parseInt(moisture1_et.getText().toString());
        l=Integer.parseInt(light_tv.getText().toString());
        String key=databaseReference.push().getKey();
        Plant p=new Plant(t,l,m);
        databaseReference.child("Plant").setValue(p);
        Toast.makeText(getApplicationContext(),"threshold data is added",Toast.LENGTH_LONG).show();
        Temperature_tv.setText("");
        moisture1_et.setText("");
        light_tv.setText("");



    }
}
