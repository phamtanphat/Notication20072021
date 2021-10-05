package com.example.notication20072021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button mBtnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mBtnNotification = findViewById(R.id.buttonNotification);

        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity2.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity2.this);
                stackBuilder.addNextIntentWithParentStack(intent);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity2.this, "CHANNEL_ID");

                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.textViewTitle,"Android App");
                remoteViews.setOnClickPendingIntent(R.id.buttonOpenApp,pendingIntent);

                notification.setSmallIcon(R.mipmap.ic_launcher);
                notification.setCustomContentView(remoteViews);
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