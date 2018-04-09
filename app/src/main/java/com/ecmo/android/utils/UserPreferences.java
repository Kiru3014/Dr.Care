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
    private static final String KEY_DR_ID = "docId";
    private static final String KEY_DOC_TYPE = "doc_type";
    private static final String KEY_USER_EMAIL_ID = "email";
    private static final String KEY_USER_MOB = "mobileno";
    private static final String KEY_USER_HOSPITAL = "hospital";
    private static final String KEY_USER_SPECIALITY = "speciality";

    private static final String KEY_IS_LOGGED_IN = "isLogin";
    private static final String KEY_SESSION_ID = "session";
    private static final String PUSHWOSH_TOKEN = "pushwosh_token";
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
        return pref.getString(KEY_DR_ID, "");
    }

    public void setUserId(String userId) {
        editor.putString(KEY_DR_ID, userId);
        editor.commit();
    }

    public boolean isUserLogin() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean isLogin) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLogin);
        editor.commit();
    }


    public void setSession(String sessionid)
    {
        editor.putString(KEY_SESSION_ID, sessionid);
        editor.commit();
    }


    public String getSession()
    {
        return pref.getString(KEY_SESSION_ID, "");
    }



    public void clearSession() {
        editor.clear();
        editor.commit();
    }


    public void setPushwooshToken(String pushwooshToken) {
        editor.putString(PUSHWOSH_TOKEN, pushwooshToken);
        editor.commit();
    }

    public String getPushwooshToken()
    {
        return pref.getString(PUSHWOSH_TOKEN, "");
    }

    public  String getKeyUserMob() {
        return pref.getString(KEY_USER_MOB, "");
    }

    public  String getKeyUserHospital() {
        return pref.getString(KEY_USER_HOSPITAL, "");
    }

    public void setUserMob( String mobileno) {
        editor.putString(KEY_USER_MOB, mobileno);
        editor.commit();
    }

    public void setUserHospital( String hospitalnmae) {
        editor.putString(KEY_USER_HOSPITAL, hospitalnmae);
        editor.commit();

    }

    public void setUserSpeciality( String speciality) {
        editor.putString(KEY_USER_SPECIALITY, speciality);
        editor.commit();

    }

    public  String getKeyUserSpeciality() {
        return pref.getString(KEY_USER_SPECIALITY, "");
    }


    public void setDocType( String doctype) {
        editor.putString(KEY_DOC_TYPE, doctype);
        editor.commit();

    }

    public  String getDocType() {
        return pref.getString(KEY_DOC_TYPE, "");
    }
}
