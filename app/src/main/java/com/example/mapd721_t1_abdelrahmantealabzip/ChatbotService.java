package com.example.mapd721_t1_abdelrahmantealabzip;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;

public class ChatbotService extends Service {

    private NotificationManager notificationMgr;
    private NotificationHandling notificationHandler;

    public ChatbotService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        notificationHandler.displayNotification("ChatBot Stopped: ", "03");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationHandler = new NotificationHandling(this, notificationMgr);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent != null) {

            Bundle data = intent.getExtras();
            Intent responseIntent = new Intent();
            Bundle responseMessage = new Bundle();

            int MessageID = data.getInt("MessageID");
            String responseText = "error: undefined message ID";

            if (MessageID == 0){
                responseText = "Hello Abdelrahman Tealab !";
                responseMessage.putString("message",responseText);
                responseIntent.putExtras(responseMessage);
                responseIntent.setAction("Message_CMD_0");
            }
            else if (MessageID == 1){
                responseText = "How are you ?";
                responseMessage.putString("message",responseText);
                responseIntent.putExtras(responseMessage);
                responseIntent.setAction("Message_CMD_1");
            }
            else if (MessageID == 2){
                responseText = "Good Bye Abdelrahman Tealab !";
                responseMessage.putString("message",responseText);
                responseIntent.putExtras(responseMessage);
                responseIntent.setAction("Message_CMD_2");
            }
            else {
                stopSelf();
            }
            notificationHandler.displayNotification("New Message: ", responseText);
            sendBroadcast(responseIntent);
        }
        return START_STICKY;
    }

}