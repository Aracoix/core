//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ihomefnt.commonlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import java.util.List;

public class AbWifiUtil {
    public AbWifiUtil() {
    }

    public static void setWifiEnabled(Context context, boolean enabled) {
        WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        if (enabled) {
            wifiManager.setWifiEnabled(true);
        } else {
            wifiManager.setWifiEnabled(false);
        }

    }

    public static boolean isConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().getState() == State.CONNECTED || telephonyManager.getNetworkType() == 3;
    }

    public static boolean isWifiConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == 1;
    }

    public static List<ScanResult> getScanResults(Context context) {
        WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        List<ScanResult> list = null;
        boolean f = wifiManager.startScan();
        if (!f) {
            getScanResults(context);
        } else {
            list = wifiManager.getScanResults();
        }

        return list;
    }

    public static ScanResult getScanResultsByBSSID(Context context, String bssid) {
        WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        ScanResult scanResult = null;
        boolean f = wifiManager.startScan();
        if (!f) {
            getScanResultsByBSSID(context, bssid);
        }

        List<ScanResult> list = wifiManager.getScanResults();
        if (list != null) {
            for(int i = 0; i < list.size(); ++i) {
                scanResult = (ScanResult)list.get(i);
                if (scanResult.BSSID.equals(bssid)) {
                    break;
                }
            }
        }

        return scanResult;
    }

    public static WifiInfo getConnectionInfo(Context context) {
        WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo;
    }
}
