package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckLight extends AppCompatActivity {
Switch reading_switch, light_switch;
TextView light_tv;
DatabaseReference TurnLightOn;
DatabaseReference CurrentLightIntensity;
DatabaseReference reff;
    int notificationFlag = 0;
    int notificationFlag2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_light);
        reading_switch = findViewById(R.id.reading_switch);
        light_switch = findViewById(R.id.light_switch);
        light_tv = findViewById(R.id.light_tv);
        CurrentLightIntensity = FirebaseDatabase.getInstance().getReference().child("CurrentLightIntensityValue");

        reff = FirebaseDatabase.getInstance().getReference();

        reading_switch.setOnClickListener(new View.OnClickListener() {
                                              ///////////////////////////###################
                                              @Override
                                              public void onClick(View v) {
                                                  Boolean switchState = reading_switch.isChecked();
                                                  if(switchState == true)
                                                  {
                                                  reff.child("SendLightIntensityValue").setValue("1");

                                                  CurrentLightIntensity.addValueEventListener(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                          for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                              String moistureValue = ds.getValue().toString().trim();
                                                              light_tv.setText(moistureValue);

//  int moistureValueInt = Integer.valueOf(moistureValue);
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
                                                                  if (notificationFlag2 == 0) {
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
                                              }//////////if ends

                                                  else
                                                  {
                                                      reff = FirebaseDatabase.getInstance().getReference();

                                                      reff.child("SendLightIntensityValue").setValue("0");
                                                  }
                                          }//// on click ends
            //////////////////////###############
        });

        light_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean switchState = light_switch.isChecked();
                reff = FirebaseDatabase.getInstance().getReference();

                if (switchState == true) {
                    reff.child("TurnLightOn").setValue("1");

                } else {

                    reff.child("TurnLightOn").setValue("0");
                }

            }
        });

    }

}