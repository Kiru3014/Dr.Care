package com.ecmo.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkDetector {
    private Context _context;

    public NetworkDetector(Context context) {
        _context = context;
    }


    public boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
