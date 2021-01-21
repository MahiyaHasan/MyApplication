package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;


import android.widget.Button;


import android.widget.Switch;





import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;



import java.util.Timer;
import java.util.TimerTask;





public class CheckTemperature extends AppCompatActivity {






    private NotificationManagerCompat notificationManager;


    public static  String g = "temperature has increased";

    Button notify_bt;
    Button  profile_bt;
    private Thermometer thermometer;
    private float a;
    private float progress_value;
    private float temperature;
    DatabaseReference df;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_temperature);
        notificationManager = NotificationManagerCompat.from(this);


        thermometer=findViewById(R.id.thermometer);

        df=FirebaseDatabase.getInstance().getReference().child("Temperature1");

        Query lastQuery = df.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    String value =  data.getValue().toString();

                    progress_value = Float.parseFloat(value);
                    showvalue(progress_value);

                    if (progress_value > 35.00||progress_value == 35.00) {




                        shownotification();


                    }
                    if (progress_value < 30.00||progress_value==30.00) {




                        shownotification1();


                    }




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});





    }


    public void shownotification1() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24) //set icon for notification
                        .setContentTitle("Notifications Example") //set title of notification
                        .setContentText("This is a notification message")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification

        int requestID = (int) System.currentTimeMillis();
        Intent notificationIntent = new Intent(this, Notification1.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_CLEAR_TOP );
//Intent.FLAG_ACTIVITY_CLEAR_TOP
        // notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // To open only one activity on launch.


        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "Temperature has decreased less than the threshold. light will be turned on");
        PendingIntent contentIntent = PendingIntent.getActivity(
                this, requestID, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(contentIntent);

        // Add as notification

        manager.notify(requestID, builder.build());


    }
    public void shownotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24) //set icon for notification
                        .setContentTitle("Notifications Example") //set title of notification
                        .setContentText("This is a notification message")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification

        int requestID = (int) System.currentTimeMillis();
        Intent notificationIntent = new Intent(this, Notification1.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_CLEAR_TOP );
//Intent.FLAG_ACTIVITY_CLEAR_TOP
        // notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // To open only one activity on launch.


        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "Temperature has increased beyond the threshold. fan will be turned on");
        PendingIntent contentIntent = PendingIntent.getActivity(
                this, requestID, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(contentIntent);

        // Add as notification

        manager.notify(requestID, builder.build());


    }

    public void showvalue( float p)
    {

        Timer timer = new Timer();
        a=p;
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                temperature = Utils.showint(-10, a);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thermometer.setCurrentTemp(temperature);
                        getSupportActionBar().setTitle(getString(R.string.app_name) + " : " + temperature);


                    }
                });
            }
        }, 0, 3500);

    }


}





