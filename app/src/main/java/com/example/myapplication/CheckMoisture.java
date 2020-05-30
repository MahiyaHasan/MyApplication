package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckMoisture extends AppCompatActivity {
    TextView moisture_tv;
    Button waterPlant_bt;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_moisture);
        getSupportActionBar().hide();

        moisture_tv = findViewById(R.id.moisture_et);
        waterPlant_bt = findViewById(R.id.waterPlant_bt);

        // save_bt.setOnClickListener(new View.OnClickListener() {

        // @Override
        //   public void onClick(View v) {
        //   reff = FirebaseDatabase.getInstance().getReference().child("Another").child("TestData");
        reff = FirebaseDatabase.getInstance().getReference().child("CurrentMoistureValue");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String name = ds.getValue().toString();

                    //String name = ds.child("name").getValue().toString();
                    //String age = ds.child("age").getValue().toString();
                    //String phone = ds.child("ph").getValue().toString();

                    moisture_tv.setText(name);
                    //  b.setText(age);
                    // c.setText(phone);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //   }
        // });



        waterPlant_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference();

                reff.child("TurnPumpOn").setValue("1");




            }
        });
    }
}
