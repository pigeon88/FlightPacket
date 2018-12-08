package com.hsy.flightpacket.net.entity;

/**
 * Created by wenxiaowei on 2017/9/7.
 */

public class TokenInvalid {
    private int code;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private String msg;

    public TokenInvalid() {

    }

    public TokenInvalid(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
