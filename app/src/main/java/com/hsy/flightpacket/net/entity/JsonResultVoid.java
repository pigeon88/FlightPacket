package com.hsy.flightpacket.net.entity;

import android.studio.plugins.GsonUtils;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/16 18:01.
 */
public class JsonResultVoid {

    public boolean success;
    public String msg;
    public int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isError() {
        return !isSuccess();
    }

    public String getMsg() {
        return msg;
    }

    public static JsonResultVoid createErrorVoid(String response) {
        JsonResultVoid resultVoid = GsonUtils.jsonDeserializer(response, JsonResultVoid.class);
        if (resultVoid == null) {
            resultVoid = new JsonResultVoid();
            resultVoid.msg = response;
        }
        return resultVoid;
    }
}
