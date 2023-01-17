package com.nexton.creative;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseInstanceID extends FirebaseMessagingService {
    public FirebaseInstanceID() {
        super();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        showNotification(title,body);
    }

    private void showNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity2.class);
        String channel_id = "CHN_ID";
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setVibrate(new long[]{1000, 1000, 1000}) //알림시 진동 설정 : 1초 진동, 1초 쉬고, 1초 진동
                .setContentIntent(pendingIntent);
        NotificationChannel notificationChannel = new NotificationChannel(channel_id, "CHN_NAME", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(0,builder.build());
    }
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
