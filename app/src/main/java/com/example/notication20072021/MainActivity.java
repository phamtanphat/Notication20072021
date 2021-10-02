package com.example.notication20072021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotification = findViewById(R.id.buttonNotification);

        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this,"CHANNEL_ID");
                notification.setContentTitle("App A");
                notification.setContentText("Bạn có một voucher khuyến mãi được sử dụng trong ngày hôm nay");
                notification.setSmallIcon(R.mipmap.ic_launcher);
                notification.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                notification.setShowWhen(true);
                notification.setPriority(Notification.PRIORITY_HIGH);
                notification.setVibrate(new long[]{500,500,500,500});
                notification.setSound();



            }
        });
    }

}