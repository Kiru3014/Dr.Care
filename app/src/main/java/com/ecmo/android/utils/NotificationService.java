package com.ecmo.android.utils;

/**
 * Created by fairoze khazi on 05/04/2018.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.ecmo.android.R;
import com.ecmo.android.activity.LoginActivity;
import com.google.android.gms.gcm.GcmListenerService;


public class NotificationService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
//        Log.i("PushwooshTest", "Gcm router service received message: " + (data != null ? data.toString() : "<null>") + " from: " + from);
//        // Base GCM listener service removes this extra before calling onMessageReceived
//        // Need to set it again to pass intent to another service
//        data.putString("from", from);
//        if (data.containsKey("mp_message")) {
//            String mp_message = data.getString("mp_message");
//            defaultPushMessageFromMixPanel(mp_message);
//            Log.i("PushwooshTest message",mp_message);
//            return;
//        } // project id of GCM
//        else {
//            Log.i("PushwooshTest", "Gcm router service INSIDE ifcondition ");
//            dispatchMessage(data);
//        }

    }

    private void dispatchMessage(Bundle data) {

        String title  = data.getString("title");
//        String desc  = data.getString("");

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("order_notification",1);
        PendingIntent pendingIntentYes = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = null;

        mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle(title)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setContentText(title);

        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntentYes);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }


    public void defaultPushMessageFromMixPanel(String title){

        Intent intent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntentYes = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = null;

        mBuilder =  new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setDefaults(Notification.DEFAULT_SOUND);



        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntentYes);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }
//
//    private void dispatchMessage(Bundle data, int type, String title, String desc) {
//
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.putExtra("order_notification",1);
//        PendingIntent pendingIntentYes = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder mBuilder = null;
//            mBuilder =
//                    new NotificationCompat.Builder(this)
//                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
//                            .setSmallIcon(R.drawable.icon)
//                            .setContentTitle(title)
//                            .setDefaults(Notification.DEFAULT_SOUND)
//                            .setContentText(desc);
//
//        mBuilder.setAutoCancel(true);
//        mBuilder.setContentIntent(pendingIntentYes);
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(0, mBuilder.build());
//    }


}