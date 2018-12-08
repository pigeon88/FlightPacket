package com.hsy.flightpacket.net.entity;

import java.util.List;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/16 18:01.
 */
public class JsonResultTsList<T> extends JsonResultVoid {

    private PageList<T> data;

    public boolean hasNextPage() {
        return data.dataList.size() == data.pageSize;
    }

    public boolean hasNextPage(int pageSize) {
        return data.dataList.size() == pageSize;
    }

    public int getTotalCount() {
        return data.totalCount;
    }

    public int getPageSize() {
        return data.pageSize;
    }

    public List<T> getData() {
        return data.dataList;
    }

    public List<T> getDataList() {
        return data.dataList;
    }

    public String getEtagFirst() {
        return data.msg_etag_first;
    }

    public String getEtagEnd() {
        return data.msg_etag_end;
    }

    class PageList<T> {
        private String msg_etag_first;
        private String msg_etag_end;
        private int totalCount;
        private int pageSize;
        private List<T> dataList;
    }
}
