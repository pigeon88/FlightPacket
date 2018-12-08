package com.hsy.flightpacket.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hsy.flightpacket.application.FlightApplication;

import java.io.File;
import java.util.UUID;

/**
 * Created by xiongweimin on 2017/3/16.
 */

public class AppConfig {

    public static final String IS_FIRST = "is_first" + AppConfig.getPackageInfo(FlightApplication.getInstance()).versionName;

    public static final String SHARE_PRE_NAME = "axwl_share_pre";

    public static File createDefaultCacheDir(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), "ahttp-cache");
        if (!cache.exists()) {
            cache.mkdirs();
        }

        return cache;
    }

    /**
     * 获得MobileCode
     *
     * @return
     */
    public static String getIEMI() {
        String result = getDeviceId();
        if (TextUtils.isEmpty(result)) {
            result = getBuildSerial();
        }
        return result;
    }

    /**
     * 获取Device Id。(Android手机可以获取到，其他手持设备不行，如平板)
     *
     * @return DeviceId or null
     */
    private static String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) FlightApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            try {
                UUID deviceUuid = new UUID(deviceId.hashCode(), deviceId.hashCode() << 32 | deviceId.hashCode());
                return deviceUuid.toString().replace("-", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取Serial号
     * 失败时则通过硬件信息生成的伪设备码
     *
     * @return Build.SERIAL or Pseudo DeviceId
     */
    public static String getBuildSerial() {
        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 位
        String uuid = "";
        try {
            //API>=9 使用serial号
            uuid = new UUID(m_szDevIDShort.hashCode(), Build.SERIAL.hashCode()).toString();
        } catch (Exception exception) {
            //使用硬件信息构建出来的15位号码
            uuid = new UUID(m_szDevIDShort.hashCode(), "unknown".hashCode()).toString();
        }
        return uuid.replace("-", "");
    }

    /**
     * 获取包信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            String packageName = context.getPackageName();
            info = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
