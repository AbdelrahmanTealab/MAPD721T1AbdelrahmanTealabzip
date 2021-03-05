package com.example.mapd721_t1_abdelrahmantealabzip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationHandling {

    private static final String TAG = "NotificationHandling";
    private final Context context;
    private final NotificationManager notificationMgr;

    public NotificationHandling(Context context, NotificationManager notificationManager) {
        this.context = context;
        this.notificationMgr = notificationManager;
    }

    public void displayNotification(String notifTitle, String notifText) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        try {
            Notification notif = new Notification.Builder(context)
                    .setSmallIcon(android.R.drawable.ic_dialog_email)
                    .setContentTitle(notifTitle)
                    .setContentText(notifText)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .build();

            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationMgr.notify(0, notif);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
