package com.hsy.flightpacket.util;

import android.widget.TextView;

/**
 * Created by xiongweimin on 2017/3/22.
 */

public class TextViewUtils {
    /**
     * 设置 textview 左侧资源图片
     *
     * @param tv
     * @param iconResID
     */
    public static void setDrawableLeft(TextView tv, int iconResID) {
        tv.setCompoundDrawablesWithIntrinsicBounds(iconResID, 0, 0, 0);
    }

    /**
     * 设置 textview 右侧资源图片
     *
     * @param tv
     * @param iconResID
     */
    public static void setDrawableRight(TextView tv, int iconResID) {
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResID, 0);
    }

    /**
     * 设置 textview 上侧资源图片
     *
     * @param tv
     * @param iconResID
     */
    public static void setDrawableTop(TextView tv, int iconResID) {
        tv.setCompoundDrawablesWithIntrinsicBounds(0, iconResID, 0, 0);
    }

    /**
     * 设置 textview 下侧资源图片
     *
     * @param tv
     * @param iconResID
     */
    public static void setDrawableBottom(TextView tv, int iconResID) {
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, iconResID);
    }

}
