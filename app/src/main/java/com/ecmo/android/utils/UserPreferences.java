package com.ecmo.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by K.Gowda on 26/3/2017.
 */

public class UserPreferences
{
    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_FIRST_NAME = "name";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_EMAIL_ID = "email";
    private static final String KEY_IS_LOGGED_IN = "isLogin";
    private static final String KEY_PASSWORD = "password";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;



    // Constructor
    @SuppressLint("CommitPrefEdits")
    public UserPreferences(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        editor = pref.edit();
    }

    public String getFirstName() {
        return pref.getString(KEY_FIRST_NAME, "");
    }

    public void setFirstName(String firstName) {
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.commit();
    }

    public String getEmailId() {
        return pref.getString(KEY_USER_EMAIL_ID, "");
    }

    public void setEmailId(String emailId) {
        editor.putString(KEY_USER_EMAIL_ID, emailId);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, "");
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean isLogin) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLogin);
        editor.commit();
    }


    public void setPassword(String isArMode)
    {
        editor.putString(KEY_PASSWORD, isArMode);
        editor.commit();
    }


    public String getPassword()
    {
        return pref.getString(KEY_PASSWORD, "");
    }



    public void clearSession() {
        editor.clear();
        editor.commit();
    }


}
