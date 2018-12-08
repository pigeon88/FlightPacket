package com.hsy.flightpacket.net.entity;

import java.util.List;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/16 18:00.
 */
public class JsonResultRow<T> extends JsonResultVoid {

    private List<T> data;
    private int row;
    @Deprecated
    public int PraiseRate;

    public List<T> getData() {
        return data;
    }

    public boolean isNextPage(int pageIndex) {
        return pageIndex < row;
    }
}
