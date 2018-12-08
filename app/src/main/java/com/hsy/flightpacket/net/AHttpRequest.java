package com.hsy.flightpacket.net;

import android.content.Context;
import android.studio.os.LogCat;
import android.studio.os.PreferencesUtils;
import android.studio.util.URLUtils;
import android.text.TextUtils;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.net.ConnectException;


/**
 * create by xiongweimin
 */
public class AHttpRequest {
    public static final String KEY_LATITUDE = "key_latitude";
    public static final String KEY_LONGITUDE = "key_longitude";
    //本地缓存状态码
    public static final int HTTP_OK = 200;
    public static final int HTTP_LOCAL_CACHE = 999;
    public static final int HTTP_TIMEOUT = -500;
    public static final int HTTP_CONNECT_ERR = 0;
    public static final int HTTP_CONNECT_400 = 400;
    public static final int HTTP_CONNECT_500 = 500;
    public static final int HTTP_CONNECT_600 = 600;
    private static AsyncHttpClient instance = null; // 实例话对象
    //    public static String token;
    private static final String TOKEN = "token";

    public static AsyncHttpClient getInstance() {
        synchronized (AHttpRequest.class) {
            if (instance == null) {
                AsyncHttpClient.blockRetryExceptionClass(ConnectException.class);
                instance = new AsyncHttpClient();
                instance.setConnectTimeout(60 * 1000);
                instance.setResponseTimeout(60 * 1000); // 设置链接超时，如果不设置，默认为10s
                instance.setMaxRetriesAndTimeout(0, AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                /*instance.addHeader("version", AppConfig.getPackageInfo(BaseApplication.application).versionName);
                LogCat.i("version=%s", AppConfig.getPackageInfo(BaseApplication.application).versionName);
                instance.setSSLSocketFactory(getMySSLSocketFactory());*/
            }
        }
        // instance.setSSLSocketFactory(getMySSLSocketFactory());
        if (!TextUtils.isEmpty(getToken())) {
            instance.addHeader("token", getToken());
            String latitude = PreferencesUtils.getString(KEY_LATITUDE, "");
            String longitude = PreferencesUtils.getString(KEY_LONGITUDE, "");
            if (!"".equals(latitude) && !"".equals(longitude)) {
                instance.addHeader("latitude", latitude);
                instance.addHeader("longitude", longitude);
            }
            LogCat.i("token=%s,latitude=%s,longitude=%s", getToken(), latitude, longitude);
        }
        return instance;
    }

    public static void setToken(String token) {
        PreferencesUtils.setString(TOKEN, token);
    }

    public static String getToken() {
        return PreferencesUtils.getString(TOKEN, "");
    }

    /**
     * 获取本地https 证书文件
     *
     * @return
     */
/*    private static MySSLSocketFactory getMySSLSocketFactory() {
        try {
            InputStream inStream = FlightApplication.getInstance().getAssets().open("");
            KeyStore keyStore = MySSLSocketFactory.getKeystoreOfCA(inStream);
            MySSLSocketFactory socketFactory = new MySSLSocketFactory(keyStore);
            socketFactory.setHostnameVerifier(SSLSocketFactory);
            return socketFactory;
        } catch (Throwable e) {
            e.printStackTrace();
            return SSLSocketFactory.getSocketFactory();
        }
    }*/

    public static void cancel(RequestHandle requestHandle) {
        if (requestHandle != null) {
            requestHandle.cancel(true);
        }
    }

    /**
     * 取消所有请求
     *
     * @param context
     */
    public static void cancelAllRequests(Context context) {
        if (instance != null) {
            instance.cancelRequests(context, true); //取消请求
            instance.cancelAllRequests(true);
        }
    }

    /**
     * 是否缓存
     *
     * @param statusCode
     * @return
     */
    public static boolean hasCacheCode(int statusCode) {
        return statusCode == HTTP_LOCAL_CACHE;
    }

    public static boolean hasTimeoutCode(int statusCode) {
        return statusCode == HTTP_TIMEOUT;
    }

    public static boolean hasCode(int statusCode, int httCode) {
        return statusCode == httCode;
    }

    public static int getRandomRequestCode() {
        return (int) (Math.random() * -100);
    }

    //get 需要token version 调用
    public static RequestHandle get(String url, RequestParams params, TextHttpResponseHandler responseHandler, boolean localCache) {
        return get(url, params, responseHandler, getRandomRequestCode(), localCache);
    }

    //get 需要token version 调用
    public static RequestHandle get(String url, RequestParams params, ResultCallback callback, boolean localCache) {
        int requestCode = getRandomRequestCode();
        return get(url, params, newTextHttpResponse(callback, requestCode), requestCode, localCache);
    }


    //post 正常调用
    public static RequestHandle post(String url, RequestParams params, ResultCallback callback, boolean localCache) {
        int requestCode = getRandomRequestCode();
        return post(url, params, newTextHttpResponse(callback, requestCode), requestCode, localCache);
    }

    public static RequestHandle post(String url, RequestParams params, TextHttpResponseHandler responseHandler, int requestCode, boolean localCache) {
        String encodeUrl = URLUtils.encodeURL(url);
        LogCat.i("post[%s]: %s -> %s", requestCode, encodeUrl, params);
        return getInstance().post(encodeUrl, params, new TextHttpResponseHandlerWapper(encodeUrl, params, responseHandler, localCache));
    }

    public static RequestHandle get(String url, RequestParams params, TextHttpResponseHandler responseHandler, int requestCode, boolean localCache) {
        String encodeUrl = URLUtils.encodeURL(url);
        LogCat.i("get[%s]: %s -> %s", requestCode, encodeUrl, params);
        return getInstance().get(encodeUrl, params, new TextHttpResponseHandlerWapper(encodeUrl, params, responseHandler, localCache));
    }


    /**
     * 获取数据返回json
     *
     * @param url
     * @param params
     * @param responseHandler
     * @return
     */
    public static RequestHandle get(String url, RequestParams params, TextHttpResponseHandler responseHandler) {
        return get(url, params, responseHandler, getRandomRequestCode(), false);
    }

    public static RequestHandle get(String url, RequestParams params, ResultCallback callback) {
        return get(url, params, callback, false);
    }

    public static void delete(String url, RequestParams params, JsonResultCallback callback) {
        String encodeUrl = URLUtils.encodeURL(url);
        TextHttpResponseHandlerCode responseHandler = newTextHttpResponse(callback, getRandomRequestCode());
        LogCat.i("delete[%s]: %s -> %s", responseHandler.getRequestCode(), encodeUrl, params);
        getInstance().delete(encodeUrl, params, new TextHttpResponseHandlerWapper(encodeUrl, params, responseHandler, false));
    }

    public static RequestHandle put(String url, RequestParams params, JsonResultCallback callback) {
        String encodeUrl = URLUtils.encodeURL(url);
        TextHttpResponseHandlerCode responseHandler = newTextHttpResponse(callback, getRandomRequestCode());
        LogCat.i("put[%s]: %s -> %s", responseHandler.getRequestCode(), encodeUrl, params);
        return getInstance().put(encodeUrl, params, new TextHttpResponseHandlerWapper(encodeUrl, params, responseHandler, false));
    }

    public static RequestHandle post(String url, RequestParams params, TextHttpResponseHandler responseHandler) {
        return post(url, params, responseHandler, getRandomRequestCode(), false);
    }

    public static RequestHandle post(String url, RequestParams params, ResultCallback callback) {
        return post(url, params, callback, false);
    }

    private static TextHttpResponseHandlerCode newTextHttpResponse(final ResultCallback callback, final int requestCode) {
        return new TextHttpResponseHandlerCode(callback, requestCode);
    }
}

