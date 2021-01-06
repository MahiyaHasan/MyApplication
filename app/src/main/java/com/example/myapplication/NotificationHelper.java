package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID="channel1ID";
    public static final String channel1name="Channel 1";
    public static final String channel2ID="channel2ID";
    public static final String channel2name="Channel 2";
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            createChannels();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels()
    {
        NotificationChannel channel1=new NotificationChannel(channel1ID,channel1name, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);

        NotificationChannel channel2=new NotificationChannel(channel2ID,channel2name, NotificationManager.IMPORTANCE_HIGH);
        channel2.enableLights(true);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel2);
    }
    public NotificationManager getManager() {
        if(mManager==null)
        {
            mManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannel1Notification(String title, String message)
    {
        return new NotificationCompat.Builder(getApplicationContext(),channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notification);
    }
    public NotificationCompat.Builder getChannel2Notification(String title, String message)
    {
        return new NotificationCompat.Builder(getApplicationContext(),channel2ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notification);
    }

}
