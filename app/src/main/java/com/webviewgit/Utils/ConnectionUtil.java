package com.webviewgit.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by guilherme.
 */

public class ConnectionUtil {
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm != null) && (cm.getActiveNetworkInfo() != null) && cm.getActiveNetworkInfo().isConnected();
    }
}
