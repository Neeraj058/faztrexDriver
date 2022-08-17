package com.courier365cloud.faztrex.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static boolean isConnected(Context context) {

        boolean isConnectionAvailable = false;

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wifiConnection = null;
            NetworkInfo dataConnection = null;

            if (connectivityManager != null) {
                wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            }

            if (connectivityManager != null) {
                dataConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            }

            // check for Wi-Fi connection
            if (wifiConnection != null) {

                isConnectionAvailable = (wifiConnection.isAvailable() && wifiConnection.isConnected());

                // check for data connection
                if (!isConnectionAvailable) {

                    if (dataConnection != null) {

                        isConnectionAvailable = dataConnection.isAvailable() && dataConnection.isConnected();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }

        return isConnectionAvailable;
    }
}
