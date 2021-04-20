package com.miku.commom.domain;

import cn.hutool.core.collection.CollUtil;

import java.util.Map;

public class UniversalHttpResponse {

    private String viewName;
    private Map<String, Object> data = CollUtil.newHashMap();

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
