package com.ecmo.android.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.ecmo.android.R;
import com.ecmo.android.activity.SplashScreen;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pushwoosh.PushManager;

import java.util.Date;

public class FirebaseMessagingRouterService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        PushManager.clearLocalNotifications(this);
        dispatchNonPushwooshMessage(remoteMessage.getData().get("title"));
    }

    private void dispatchNonPushwooshMessage(String title) {
        String titlenew="",id="0";
        int reqid=0;

        Intent intent = new Intent(this, SplashScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(title.toLowerCase().contains("form id")){

            if(title.length()>28) {
                titlenew = title.substring(0, 28);
            }
            id = title.replaceAll("[^0-9]", "");
            if(titlenew!=null && !titlenew.isEmpty()){
              //  title=titlenew;
            }
            if(!id.isEmpty() && !id.equalsIgnoreCase("0")){
                intent.putExtra("FORMID",id);
                try {
                    reqid = Integer.getInteger(id);
                }
                catch (Exception e){}
            }
        }


        PendingIntent pendingIntentYes = PendingIntent.getActivity(this, 1234+reqid, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("ECMO")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentText(title);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntentYes);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify((int) new Date().getTime(), mBuilder.build());
    }
}
