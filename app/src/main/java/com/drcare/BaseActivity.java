package com.drcare;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

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

}
