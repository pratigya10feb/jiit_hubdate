package com.example.pratigya.hamblaster;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;



/**
 * This service is started when an Alarm has been raised
 *
 * We pop a notification into the status bar for the user to click on
 * When the user clicks the notification a new activity is opened
 *
 * @author paul.blundell
 */
public class NotifyService extends Service {

    /**
     * Class for clients to access
     */
    Context context=NotifyService.this;
    Notification myNotication;
    Notification notification;
    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinder = new ServiceBinder();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {
        // This is the 'title' of the notification
        CharSequence title = "Alarm!!";
        // This is the icon to use on the notification
        int icon = R.drawable.ic_add_alarm_black_24dp;
        // This is the scrolling text of the notification
        CharSequence text = "Your notification time is upon us.";
        // What time to show on the notification
        long time = System.currentTimeMillis();
           @SuppressWarnings("deprecated")
       // Notification notification = new Notification(icon, text, time);
                 /*  Notification notification = new Notification.Builder(context)
                   .setContentText(text)
                   .setSmallIcon(icon)
                   .setContentTitle("WhatsApp Notification");
                   .setWhen(time)
                   .build();*/

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, AddAdminActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyService.this);

        notification = builder.setContentIntent(contentIntent)
                .setSmallIcon(icon).setTicker(title).setWhen(time)
                .setAutoCancel(true).setContentTitle(title)
                .setContentText(text).build();
       mNM.notify(NOTIFICATION, notification); builder.setAutoCancel(false);
       // builder.setTicker("this is ticker text");
       // builder.setContentTitle("WhatsApp Notification");
     //   builder.setContentText("You have a new message");
     //   builder.setSmallIcon(R.drawable.ic_add_alarm_black_24dp);
     //   builder.setContentIntent(contentIntent);
       // builder.setOngoing(true);
      //  builder.setSubText("This is subtext...");   //API level 16

       // builder.build();

     //   myNotication = builder.getNotification();
     //   mNM.notify(11, myNotication);
        // Set the info for the views that show in the notification panel.
       // notification.setLatestEventInfo(this, title, text, contentIntent);

        // Clear the notification when it is pressed
       notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification to the system.
     //   mNM.notify(NOTIFICATION, notification);

        // Stop the service when we are finished
        stopSelf();
    }
}