package com.hsy.flightpacket.net;


import android.studio.os.LogCat;
import android.studio.plugins.GsonUtils;

import com.google.gson.reflect.TypeToken;
import com.hsy.flightpacket.net.entity.JsonResultVoid;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 11:35.
 */
public abstract class JsonResultCallback<T extends JsonResultVoid> extends TypeToken<T> implements ResultCallback {

    @Override
    public final void onSuccess(int statusCode, String responseString, int tag) {
        T t = GsonUtils.jsonDeserializer(responseString, this);
        if (t == null) {
            LogCat.e("jsonDeserializer[%s, %s]: is null", tag, statusCode);
            onFailure(statusCode, "解析出错", new NullPointerException("jsonDeserializer is null"), tag);
            return;
        }

        if (t.isError()) {
            LogCat.e("jsonDeserializer[%s, %s]: %s", tag, statusCode, t.getMsg());
            onFailure(statusCode, t.getMsg(), new Exception(responseString), tag);
            return;
        }

        onSuccess(statusCode, t, tag);
    }

    public abstract void onSuccess(int statusCode, T response, int tag);

    @Override
    public void onFailure(int statusCode, String responseString, Throwable throwable, int tag) {

    }
}
