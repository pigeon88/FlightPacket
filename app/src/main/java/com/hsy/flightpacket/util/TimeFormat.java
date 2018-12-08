package com.hsy.flightpacket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiongweimin on 2018/7/4.
 */

public class TimeFormat {
    /**
     * 获取日期
     *
     * @param date
     * @return
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }


    public static String getStringYMDHM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getStringHM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }
}
