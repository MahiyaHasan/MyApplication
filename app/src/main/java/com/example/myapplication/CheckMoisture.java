package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class CheckMoisture extends AppCompatActivity {
    TextView moisture_tv;
    TextView text_circular;
    SwitchCompat reading_switch,pump_switch;

    DatabaseReference reff;
    DatabaseReference sendMoistureSensorValueRef;
    DatabaseReference CurrentMoistureValue;
    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    int notificationFlag = 0;
    int notificationFlag2 = 0;
    CircularProgressBar progressBar;
    private NotificationHelper notificationHelper;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_moisture);
        getSupportActionBar().hide();

        moisture_tv = findViewById(R.id.moisture_et);
        text_circular=findViewById(R.id.textview_inside_circular);
        reading_switch = findViewById(R.id.reading_switch_moisture);
        pump_switch = findViewById(R.id.pump_switch);


        progressBar=findViewById(R.id.circularProgressBar);
        progressBar.setProgressMax(2000);
        // progressBar.setProgress(30);
        notificationManager = NotificationManagerCompat.from(this);
        notificationHelper=new NotificationHelper(this);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        pump_switch.setChecked(sharedPreferences.getBoolean("value",true));



        //reff = FirebaseDatabase.getInstance().getReference().child("Another").child("TestData");
        CurrentMoistureValue = FirebaseDatabase.getInstance().getReference().child("CurrentMoistureValue");
        reff = FirebaseDatabase.getInstance().getReference();
        sendMoistureSensorValueRef = FirebaseDatabase.getInstance().getReference().child("SendMoistureSensorData");

        reading_switch.setOnClickListener(new View.OnClickListener() {

            ///////////////////////////###################
            @Override
            public void onClick(View v) {
                Boolean switchState = reading_switch.isChecked();
                if (switchState == true) {
                    sendMoistureSensorValueRef.setValue("1");
                    CurrentMoistureValue.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String moistureValue = ds.getValue().toString().trim();

                                text_circular.setText(moistureValue+"%");
                                float progress_value=Float.parseFloat(moistureValue);
                                progressBar.setProgress(progress_value);


                                if (progress_value<=41)
                                {
                                    sendOnChannel2("Watering Sytem","Soil moisture level is not optimum.");

                                }



                                // else
                                //  {
                                // notificationManager.cancel(0);
                                //
                                //notificationFlag = 0;
                                //notificationFlag2 = 0;
                                //}


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }// end of in checked switch
                else {
                    reff = FirebaseDatabase.getInstance().getReference();

                    reff.child("SendMoistureSensorData").setValue("0");
                }
            }
            //////////////////////###############
        });




        pump_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean switchState = pump_switch.isChecked();
                reff = FirebaseDatabase.getInstance().getReference();

                if (switchState == true) {
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    pump_switch.setChecked(true);
                    reff.child("TurnPumpOn").setValue("1");
                    sendOnChannel2("Watering Sytem","Water Pump is ON");


                } else {

                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    pump_switch.setChecked(false);
                    reff.child("TurnPumpOn").setValue("0");
                    Toast.makeText(CheckMoisture.this,"Water Pump is Off",Toast.LENGTH_SHORT).show();

                }

            }
        });




    }
    public void sendOnChannel2(String title, String message)
    {
        NotificationCompat.Builder nb=notificationHelper.getChannel2Notification(title,message);
        notificationHelper.getManager().notify(2,nb.build());
    }
}

