package com.drcare;

import android.annotation.SuppressLint;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

@SuppressLint("Registered")
public class DrApplication extends Application
{
    public DrApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }


}
