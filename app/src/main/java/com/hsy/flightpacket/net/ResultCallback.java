package com.hsy.flightpacket.net;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 10:47.
 */
public interface ResultCallback {

    void onSuccess(int statusCode, String responseString, int tag);

    void onFailure(int statusCode, String responseString, Throwable throwable, int tag);
}
