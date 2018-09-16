package com.mooncakestudio.corridor.snapevnt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.mooncakestudio.corridor.snapevnt.NotificationsActivity;
import com.mooncakestudio.corridor.snapevnt.R;

public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent bringBack = new Intent(context, NotificationsActivity.class);
        bringBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 123, bringBack, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("SnapEvnt");
        builder.setContentTitle("HACKER TROUP");
        builder.setContentText("You have a notification.");
        builder.setAutoCancel(true);

        nm.notify(100, builder.build());
    }
}