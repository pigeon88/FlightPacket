package com.hsy.flightpacket.net.entity;

import java.util.List;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 10:46.
 */
public class JsonResultList<T> extends JsonResultVoid {

    private PageList<T> data;

    public boolean isNextPage() {
        return data.currentPage < data.totalPage;
    }

    public int getTotalPage() {
        return data.totalPage;
    }

    public int getTotalCount() {
        return data.totalCount;
    }

    public int getPageSize() {
        return data.pageSize;
    }

    public int getCurrentPage() {
        return data.currentPage;
    }

    public List<T> getData() {
        return data.dataList;
    }

    public List<T> getDataList() {
        return data.dataList;
    }

    public static <T> JsonResultList<T> createJsonResult(List<T> data, boolean success) {
        JsonResultList<T> jsonResult = new JsonResultList<>();
        jsonResult.success = success;
        PageList<T> pageList = new PageList<>();
        pageList.dataList = data;
        jsonResult.data = pageList;
        return jsonResult;
    }

    static class PageList<T> {
        private int totalPage;
        private int totalCount;
        private int pageSize;
        private int currentPage;
        private List<T> dataList;
    }
}
