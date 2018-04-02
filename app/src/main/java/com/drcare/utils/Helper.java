package com.drcare.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by K.Gowda on 3/3/2017.
 */

public class Helper
{
    private static Helper sharedHelper = null;
    private Typeface normalFont, boldFont, lightFont, semiBoldFont, italicFont, hominis;



    public static Helper getSharedHelper() {
        if (sharedHelper == null) {
            sharedHelper = new Helper();
            sharedHelper.normalFont = null;
            sharedHelper.boldFont = null;
            sharedHelper.lightFont = null;
            sharedHelper.semiBoldFont = null;
            sharedHelper.italicFont = null;
            sharedHelper.hominis = null;

        }
        return sharedHelper;
    }


    public void initFonts(Activity activity) {
        sharedHelper.normalFont = Typeface.createFromAsset(
                activity.getAssets(), "fonts/MyriadPro-Regular.otf");
        sharedHelper.boldFont = Typeface.createFromAsset(
                activity.getAssets(), "fonts/MyriadPro-Bold.otf");
        sharedHelper.lightFont = Typeface.createFromAsset(
                activity.getAssets(), "fonts/MyriadPro-Light.otf");
        sharedHelper.semiBoldFont = Typeface.createFromAsset(
                activity.getAssets(), "fonts/MyriadPro-Semibold.otf");
        sharedHelper.italicFont = Typeface.createFromAsset(
                activity.getAssets(), "fonts/MyriadPro-It.otf");
        sharedHelper.hominis = Typeface.createFromAsset(activity.getAssets(), "fonts/Hominis.ttf");
    }


    public Typeface getNormalFont() {
        return normalFont;
    }

    public Typeface getBoldFont() {
        return boldFont;
    }

    public Typeface getLightFont() {
        return lightFont;
    }

    public Typeface getSemiBoldFont() {
        return semiBoldFont;
    }

    public Typeface getItalicFont() {
        return italicFont;
    }

    public Typeface getHominis() {
        return hominis;
    }


    //validate Email
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email) )
            return false;

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    //Valide Null Pointer
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().equals("")
                || s.trim().equalsIgnoreCase("null");
    }

}
