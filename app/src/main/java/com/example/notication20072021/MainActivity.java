package com.example.notication20072021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
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
                NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this, "CHANNEL_ID");
                notification.setContentTitle("App A");
                notification.setContentText("Bạn có một voucher khuyến mãi được sử dụng trong ngày hôm nay");
                notification.setSmallIcon(R.mipmap.ic_launcher);
                notification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.hinhpicture)));
                notification.setShowWhen(true);
                notification.setPriority(Notification.PRIORITY_HIGH);
                notification.setVibrate(new long[]{500, 500, 500, 500});

                Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.yeulacuoi);
                notification.setSound(soundUri);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID","VERSION_0",NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.enableVibration(true);
                    Uri soundUriChannel = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.yeulacuoi);
                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build();
                    notificationChannel.setSound(soundUriChannel,audioAttributes);

                    manager.createNotificationChannel(notificationChannel);
                }

                manager.notify(1, notification.build());


            }
        });
    }

}