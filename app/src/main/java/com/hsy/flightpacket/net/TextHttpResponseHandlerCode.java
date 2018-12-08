package com.hsy.flightpacket.net;

import android.studio.os.LogCat;
import android.text.TextUtils;

import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 10:47.
 */

public class TextHttpResponseHandlerCode extends TextHttpResponseHandler {

    private ResultCallback callback;
    private int requestCode;

    public TextHttpResponseHandlerCode(ResultCallback callback) {
        this(callback, AHttpRequest.getRandomRequestCode());
    }

    public TextHttpResponseHandlerCode(ResultCallback callback, int requestCode) {
        super();
        this.callback = callback;
        this.requestCode = requestCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        LogCat.e("responseString[%s, %s]: %s", requestCode, statusCode, responseString);
        if (callback != null) {
            responseString = getErrorMessage(responseString, throwable, statusCode);
            callback.onFailure(statusCode, responseString, throwable, requestCode);
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        if (TextUtils.isEmpty(responseString)) {
            onFailure(statusCode, headers, "返回数据为空", new Exception("responseString is null"));
            return;
        }

        LogCat.i("responseString[%s, %s]: %s", requestCode, statusCode, responseString);
        if (callback != null) {
            callback.onSuccess(statusCode, responseString, requestCode);
        }
    }

    private static String getErrorMessage(String responseString, Throwable throwable, int statusCode) {
        if (statusCode == AHttpRequest.HTTP_CONNECT_ERR) {
            responseString = "网络连接失败";
        } else if (statusCode >= AHttpRequest.HTTP_CONNECT_400 && statusCode < AHttpRequest.HTTP_CONNECT_500) {
            responseString = "请求超时";
        } else if (statusCode == AHttpRequest.HTTP_TIMEOUT) {
            responseString = "请求服务器超时";
        } else if (statusCode >= AHttpRequest.HTTP_CONNECT_500 && statusCode < AHttpRequest.HTTP_CONNECT_600) {
            responseString = "请求服务器失败";
        }
        return TextUtils.isEmpty(responseString) ? "网络连接失败" : responseString;
    }

}
