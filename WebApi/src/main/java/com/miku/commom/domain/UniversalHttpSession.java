package com.miku.commom.domain;

import cn.hutool.core.collection.CollUtil;

import java.util.Map;

public class UniversalHttpSession {

    private Map<String, Object> currSession = CollUtil.newHashMap();

    public Map<String, Object> getCurrSession() {
        return currSession;
    }

    public void setCurrSession(Map<String, Object> currSession) {
        this.currSession = currSession;
    }
}
