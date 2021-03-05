package com.example.mapd721_t1_abdelrahmantealabzip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static Integer pressCounter = 0;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.chatArea);
        Button btnSendMessage = (Button) findViewById(R.id.sendMessage);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Message_CMD_0");
        intentFilter.addAction("Message_CMD_1");
        intentFilter.addAction("Message_CMD_2");
        registerReceiver(mServiceResponseReceiver, intentFilter);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        if (pressCounter>3)
        {
            pressCounter = 0;
        }
        Bundle data = new Bundle();
        data.putInt("MessageID", pressCounter);
        pressCounter += 1;
        Intent intent = new Intent(this, ChatbotService.class);
        intent.putExtras(data);
        startService(intent);
    }

    private final BroadcastReceiver mServiceResponseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle data = intent.getExtras();
            String valueString = data.getString("message");
            Log.d("BroadcastReceiver", "Receiver detected new message: " + action);
            txtView.append(action+" : "+valueString+"\n");
        }
    };
}