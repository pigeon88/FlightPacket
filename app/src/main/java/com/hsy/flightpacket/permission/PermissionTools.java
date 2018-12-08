package com.hsy.flightpacket.permission;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Desc:  权限判断
 * Author: zhongsen
 * CreateTime: 2016/11/28 13:59.
 */

public class PermissionTools {
    /**
     * @hide No operation specified.
     */
    public static final int OP_NONE = -1;
    /**
     * @hide Access to coarse location information.
     */
    public static final int OP_COARSE_LOCATION = 0;
    /**
     * @hide Access to fine location information.
     */
    public static final int OP_FINE_LOCATION = 1;
    /**
     * @hide Causing GPS to run.
     */
    public static final int OP_GPS = 2;
    /**
     * @hide
     */
    public static final int OP_VIBRATE = 3;
    /**
     * @hide
     */
    public static final int OP_READ_CONTACTS = 4;
    /**
     * @hide
     */
    public static final int OP_WRITE_CONTACTS = 5;
    /**
     * @hide
     */
    public static final int OP_READ_CALL_LOG = 6;
    /**
     * @hide
     */
    public static final int OP_WRITE_CALL_LOG = 7;
    /**
     * @hide
     */
    public static final int OP_READ_CALENDAR = 8;
    /**
     * @hide
     */
    public static final int OP_WRITE_CALENDAR = 9;
    /**
     * @hide
     */
    public static final int OP_WIFI_SCAN = 10;
    /**
     * @hide
     */
    public static final int OP_POST_NOTIFICATION = 11;
    /**
     * @hide
     */
    public static final int OP_NEIGHBORING_CELLS = 12;
    /**
     * @hide
     */
    public static final int OP_CALL_PHONE = 13;
    /**
     * @hide
     */
    public static final int OP_READ_SMS = 14;
    /**
     * @hide
     */
    public static final int OP_WRITE_SMS = 15;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_SMS = 16;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_MMS = 18;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    /**
     * @hide
     */
    public static final int OP_SEND_SMS = 20;
    /**
     * @hide
     */
    public static final int OP_READ_ICC_SMS = 21;
    /**
     * @hide
     */
    public static final int OP_WRITE_ICC_SMS = 22;
    /**
     * @hide
     */
    public static final int OP_WRITE_SETTINGS = 23;
    /**
     * @hide
     */
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    /**
     * @hide
     */
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    /**
     * @hide
     */
    public static final int OP_CAMERA = 26;
    /**
     * @hide
     */
    public static final int OP_RECORD_AUDIO = 27;
    /**
     * @hide
     */
    public static final int OP_PLAY_AUDIO = 28;
    /**
     * @hide
     */
    public static final int OP_READ_CLIPBOARD = 29;
    /**
     * @hide
     */
    public static final int OP_WRITE_CLIPBOARD = 30;
    /**
     * @hide
     */
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    /**
     * @hide
     */
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    /**
     * @hide
     */
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    /**
     * @hide
     */
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    /**
     * @hide
     */
    public static final int OP_AUDIO_RING_VOLUME = 35;
    /**
     * @hide
     */
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    /**
     * @hide
     */
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    /**
     * @hide
     */
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    /**
     * @hide
     */
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    /**
     * @hide
     */
    public static final int OP_WAKE_LOCK = 40;
    /**
     * @hide Continually monitoring location data.
     */
    public static final int OP_MONITOR_LOCATION = 41;
    /**
     * @hide Continually monitoring location data with a relatively high power request.
     */
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    /**
     * @hide Retrieve current usage stats via
     */
    public static final int OP_GET_USAGE_STATS = 43;
    /**
     * @hide
     */
    public static final int OP_MUTE_MICROPHONE = 44;
    /**
     * @hide
     */
    public static final int OP_TOAST_WINDOW = 45;
    /**
     * @hide Capture the device's display contents and/or audio
     */
    public static final int OP_PROJECT_MEDIA = 46;
    /**
     * @hide Activate a VPN connection without user intervention.
     */
    public static final int OP_ACTIVATE_VPN = 47;
    /**
     * @hide Access the WallpaperManagerAPI to write wallpapers.
     */
    public static final int OP_WRITE_WALLPAPER = 48;
    /**
     * @hide Received the assist structure from an app.
     */
    public static final int OP_ASSIST_STRUCTURE = 49;
    /**
     * @hide Received a screenshot from assist.
     */
    public static final int OP_ASSIST_SCREENSHOT = 50;
    /**
     * @hide Read the phone state.
     */
    public static final int OP_READ_PHONE_STATE = 51;
    /**
     * @hide Add voicemail messages to the voicemail content provider.
     */
    public static final int OP_ADD_VOICEMAIL = 52;
    /**
     * @hide Access APIs for SIP calling over VOIP or WiFi.
     */
    public static final int OP_USE_SIP = 53;
    /**
     * @hide Intercept outgoing calls.
     */
    public static final int OP_PROCESS_OUTGOING_CALLS = 54;
    /**
     * @hide User the fingerprint API.
     */
    public static final int OP_USE_FINGERPRINT = 55;
    /**
     * @hide Access to body sensors such as heart rate, etc.
     */
    public static final int OP_BODY_SENSORS = 56;
    /**
     * @hide Read previously received cell broadcast messages.
     */
    public static final int OP_READ_CELL_BROADCASTS = 57;
    /**
     * @hide Inject mock location into the system.
     */
    public static final int OP_MOCK_LOCATION = 58;
    /**
     * @hide Read external storage.
     */
    public static final int OP_READ_EXTERNAL_STORAGE = 59;
    /**
     * @hide Write external storage.
     */
    public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    /**
     * @hide Turned on the screen.
     */
    public static final int OP_TURN_SCREEN_ON = 61;
    /**
     * @hide
     */
    public static final int _NUM_OP = 62;

    /**
     * 判断权限是否启用
     *
     * @param context
     * @param op      权限代码号
     * @return true:启用  false:禁用或忽略
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean checkPermission(Context context, int op) {
        try {
            String packageName = context.getApplicationContext().getPackageName();
            AppOpsManager aom = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class<?>[] types = new Class[]{int.class, int.class, String.class};
            if (aom == null) {
                return false;
            }
            Method method = aom.getClass().getDeclaredMethod("checkOp", types);
            if (method == null) {
                return false;
            }
            int m = (Integer) (method.invoke(aom, op, Binder.getCallingUid(), packageName));
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int[] getPidUid(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningProcess = am.getRunningAppProcesses();
        int[] pu = new int[]{-1, -1};
        //int i = 1;
        for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess) {
            if (amProcess.processName.equals(context.getPackageName())) {
                pu[0] = amProcess.pid;
                pu[1] = amProcess.uid;
                break;
            }
        }
        return pu;
    }
}
