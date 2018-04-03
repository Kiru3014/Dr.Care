package com.drcare;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.drcare.activity.HelpActivity;
import com.scanning.drcare.R;
import com.drcare.utils.Helper;
import com.drcare.utils.UserPreferences;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity
{
    public Dialog mAlertDialog;
    UserPreferences userPreferences;

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrApplication application = (DrApplication) getApplication();
        application.onCreate();
        Helper.getSharedHelper().initFonts(this);
        userPreferences = new UserPreferences(this);

    }


    public void commonToast(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView textView = (TextView) layout.findViewById(R.id.custom_toast_message);
        textView.setTypeface(Helper.getSharedHelper().getLightFont());
        textView.setText(msg);
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration((int) 1);
        toast.setGravity(Gravity.BOTTOM, 0, 170);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void commonLoaderstart() {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.loader_layout, null);
        mAlertDialog = new Dialog(this);
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.setContentView(dialogView);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();

    }

    public void commonLoaderstop() {

        mAlertDialog.cancel();
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addNotification(String msg) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("ECMO REFERRAL APPLICATION")
                        .setAutoCancel(false)
                        .setContentText(msg);

        Intent notificationIntent = new Intent(this, HelpActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Vibration
        builder.setVibrate(new long[]{500, 500});
        builder.setSound(uri);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }


}
