package com.example.sergi.fluball;


import android.content.*;
import android.net.wifi.*;
import android.util.Log;

import java.lang.reflect.*;

/**
 * Created by sarias on 17/01/18.
 */

public class ApManager {

    //check whether wifi hotspot on or off
    public static boolean isApOn(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        try {
            Method method = wifimanager.getClass().getDeclaredMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifimanager);
        }
        catch (Throwable ignored) {}
        return false;
    }

    // toggle wifi hotspot on or off
    public static boolean configApState(String newName,String newPass,Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        Method getConfigMethod = wifimanager.getClass().getMethod("getWifiApConfiguration");
        WifiConfiguration wificonfiguration = (WifiConfiguration) getConfigMethod.invoke(wifimanager);;


        try {
            // if WiFi is on, turn it off
            if(isApOn(context)) {
                wifimanager.setWifiEnabled(false);
            }

            wificonfiguration.SSID = newName;
            wificonfiguration.preSharedKey = newPass;

            Method setConfigMethod = wifimanager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            setConfigMethod.invoke(wifimanager, wificonfiguration);

            Method method = wifimanager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method.invoke(wifimanager, wificonfiguration, !isApOn(context));

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setHotspotName(String newName,String newPass, Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
            Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
            WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);

            wifiConfig.SSID = newName;
            wifiConfig.preSharedKey = newPass;
            wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

            Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            setConfigMethod.invoke(wifiManager, wifiConfig);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} // end of class
