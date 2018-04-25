package com.ecmo.android.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

public class SplashScreen extends BaseActivity
{

    Thread splashTread;
    UserPreferences userProfileSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        userProfileSharedPreferences = new UserPreferences(getApplicationContext());
        Helper.getSharedHelper().initFonts(this);
        NextScreen();
    }


    private void NextScreen() {
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 100;
                    }

                    if (userProfileSharedPreferences.isUserLogin() && !userProfileSharedPreferences.getSession().isEmpty())
                        {
                            if(getIntent().hasExtra("FORMID")){
                                Intent intent = new Intent(SplashScreen.this, ViewReferalForm.class);
                                intent.putExtra("formid",  getIntent().getStringExtra("FORMID"));
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                finish();
                            }
                    } else {
                        Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        splashTread.start();
    }

}
