package com.ecmo.android;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecmo.android.activity.HelpActivity;
import com.ecmo.android.activity.LoginActivity;
import com.ecmo.android.activity.WelcomeActivity;
import com.ecmo.android.model.response.Hospitalitem;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity
{
    public Dialog mAlertDialog;
    UserPreferences userPreferences;
    static List<Hospitalitem> hospitallist;
    static List<Hospitalitem> specialitylist;

    public static List<Hospitalitem> getHospitallist() {
        return hospitallist;
    }

    public static void setHospitallist(List<Hospitalitem> hospitallist) {
        BaseActivity.hospitallist = hospitallist;
    }

    public static List<Hospitalitem> getSpecialitylist() {
        return specialitylist;
    }

    public static void setSpecialitylist(List<Hospitalitem> specialitylist) {
        BaseActivity.specialitylist = specialitylist;
    }

    public String gethospitalvalue(String hospital){
        List<Hospitalitem> hosplist= getHospitallist();
        for (int i = 0; i < hosplist.size(); i++) {
            if(hospital.equals(hosplist.get(i).getText()))
                return hosplist.get(i).getValue();
        }
        return "0";
    }

    public String gethospitalname(String hospitalvalue){
        List<Hospitalitem> hosplist= getHospitallist();
        for (int i = 0; i < hosplist.size(); i++) {
            if(hospitalvalue.equals(hosplist.get(i).getValue()))
                return hosplist.get(i).getValue();
        }
        return "Select";
    }

    public String getSpecilityvalue(String speciality){
        List<Hospitalitem> hosplist= getSpecialitylist();
        for (int i = 0; i < hosplist.size(); i++) {
            if(speciality.equals(hosplist.get(i).getText()))
                return hosplist.get(i).getValue();
        }
        return "0";
    }


    public String getSpecility(String specialityvalue){
        List<Hospitalitem> hosplist= getSpecialitylist();
        for (int i = 0; i < hosplist.size(); i++) {
            if(specialityvalue.equals(hosplist.get(i).getValue()))
                return hosplist.get(i).getValue();
        }
        return "Select";
    }


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


    public int getspinnerIndexvalue(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }


    public void commonToast(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView textView = (TextView) layout.findViewById(R.id.custom_toast_message);
        textView.setTypeface(Helper.getSharedHelper().getLightFont());
        textView.setText(msg);
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 170);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 2000);
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
        if(mAlertDialog!=null)
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


    public int getAgefromCivilId( String civilid)
    {
        int cidyy= Integer.parseInt(civilid.substring(1,3));
        Calendar c = Calendar.getInstance();
//        int seconds = c.get(Calendar.SECOND);
//        int hour = c.get(Calendar.HOUR_OF_DAY); // IF YOU USE HOUR IT WILL GIVE 12 HOUR USE HOUR_OF_DAY TO GET 24 HOUR FORMAT
//        int minutes = c.get(Calendar.MINUTE);
//        int date = c.get(Calendar.DATE);
//        int month = c.get(Calendar.MONTH) + 1; // in java month starts from 0 not from 1 so for december 11+1 = 12
        int year = c.get(Calendar.YEAR)%100;

        if(cidyy<=year)
            return year-cidyy;

        year=year+100;
        return year-cidyy;

    }



    public void showConfirmDialog(Context c) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setTitle("YOU WANT TO LOGOUT");

        alertDialogBuilder.setPositiveButton("NO",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });

        alertDialogBuilder.setNegativeButton("YES",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userPreferences.clearSession();
                        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }



    public ArrayAdapter<String> getSpeciality(Context context) {
        List<String> list = new ArrayList<String>();
        List<Hospitalitem> hosplist= getSpecialitylist();
        if(hosplist!=null) {
            for (int i = 0; i < hosplist.size(); i++) {
                list.add(hosplist.get(i).getText());
            }
        }


        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list);
    }


    public ArrayAdapter<String> getAutosuggestion(Context context) {
        List<String> list = new ArrayList<String>();
        List<Hospitalitem> hosplist= getHospitallist();
        if(hosplist!=null) {
            for (int i = 0; i < hosplist.size(); i++) {
                list.add(hosplist.get(i).getText());
            }

        }

        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list);
    }

    public  void LogoutSession()
    {
        commonToast("Session Expired , log In again to continue.");
        userPreferences.clearSession();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
