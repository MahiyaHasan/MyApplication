package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
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
    Button waterPlant_bt,start_bt,stop_bt;
    DatabaseReference reff;
    DatabaseReference sendMoistureSensorValueRef;
    DatabaseReference CurrentMoistureValue;
    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    int notificationFlag = 0;
    int notificationFlag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_moisture);
        getSupportActionBar().hide();

        moisture_tv = findViewById(R.id.moisture_et);
        waterPlant_bt = findViewById(R.id.waterPlant_bt);
        start_bt = findViewById(R.id.start_bt);
        stop_bt = findViewById(R.id.stop_bt);


        //reff = FirebaseDatabase.getInstance().getReference().child("Another").child("TestData");
        CurrentMoistureValue = FirebaseDatabase.getInstance().getReference().child("CurrentMoistureValue");
        reff = FirebaseDatabase.getInstance().getReference();
        sendMoistureSensorValueRef = FirebaseDatabase.getInstance().getReference().child("SendMoistureSensorData");
        start_bt.setOnClickListener(new View.OnClickListener() {

            ///////////////////////////###################
            @Override
            public void onClick(View v) {
                sendMoistureSensorValueRef.setValue("1");
                CurrentMoistureValue.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String moistureValue = ds.getValue().toString().trim();

                            moisture_tv.setText(moistureValue);

//                    int moistureValueInt = Integer.valueOf(moistureValue);

                            //  moisture_tv.setText(moistureValueInt);

                            NotificationCompat.Builder mbuilder = (NotificationCompat.Builder)
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_notification)
                                            .setContentTitle("Notification")
                                            //.setSound(defaultSoundUri)
                                            .setContentText("Soil moisture level is not optimum.");
                            NotificationManager notificationManager = (NotificationManager)
                                    getSystemService(NOTIFICATION_SERVICE);
                            if (moistureValue.equals("41")) {
                                ///////////############ NOTIFICATION ###############//////////////////////
                                if (notificationFlag2 == 0)
                                {
                                    notificationManager.notify(0, mbuilder.build());

                                notificationFlag2++;

                                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 1000);
                                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 6000);
                            }

                              //1  if (notificationFlag == 0) {

                                 //1   ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 1000);
                                   //1 toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 6000);
                               //1 }
                                //1 notificationFlag++;

                                ///////////############ NOTIFICATION ###############//////////////////////

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
            }
            //////////////////////###############
        });

         waterPlant_bt.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View v) {
           reff = FirebaseDatabase.getInstance().getReference();

                   reff.child("TurnPumpOn").setValue("0");


               }
          });

        stop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference();

                reff.child("SendMoistureSensorData").setValue("0");


            }
        });

    }
}
