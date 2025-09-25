package com.expense_tracker.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class IPAddressHelper {


    // Get WiFi IP address
    public static String getWifiIPAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);

            if (wifiManager != null && wifiManager.isWifiEnabled()) {
                int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
                return Formatter.formatIpAddress(ipAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
