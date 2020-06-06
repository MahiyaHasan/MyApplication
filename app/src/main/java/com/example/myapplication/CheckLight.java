package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yangp.ypwaveview.YPWaveView;

import static android.os.Build.VERSION_CODES.N;



public class CheckLight extends AppCompatActivity {
    YPWaveView ypWaveView;
    private NotificationManagerCompat notificationManager;
    DatabaseReference reff;
    DatabaseReference Moisture;
    SwitchCompat aSwitch;
    private NotificationHelper notificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_light);
        ypWaveView=findViewById(R.id.YPwaveView);
        aSwitch=findViewById(R.id.turnFlaslightsOn_bt);
        ypWaveView.setMax(1024);
        //ypWaveView.setProgress(50);
        ypWaveView.startAnimation();
        Moisture= FirebaseDatabase.getInstance().getReference().child("Moisture");
        notificationManager = NotificationManagerCompat.from(this);
        notificationHelper=new NotificationHelper(this);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        aSwitch.setChecked(sharedPreferences.getBoolean("value",true));
        reff = FirebaseDatabase.getInstance().getReference();



        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked())
                {

                    reff.child("TurnLightOn").setValue("1");


                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    aSwitch.setChecked(true);
                    sendOnChannel1("Lighting System","FlashLight is On");


                }
                else
                {
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    aSwitch.setChecked(false);
                    Toast.makeText(CheckLight.this,"FlashLight is Off",Toast.LENGTH_SHORT).show();
                    reff.child("TurnLightOn").setValue("0");

                }
            }
        });


        // ypWaveView.setAnimationSpeed();
        Moisture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String moistureValue = ds.getValue().toString().trim();
                    int value=Integer.parseInt(moistureValue);
                    ypWaveView.setProgress(value);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void sendOnChannel1(String title, String message)
    {
        NotificationCompat.Builder nb=notificationHelper.getChannel1Notification(title,message);
        notificationHelper.getManager().notify(1,nb.build());
    }
}


