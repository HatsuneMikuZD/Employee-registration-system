package com.miku.commom.domain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;

import java.util.Map;

public class UniversalHttpRequest {

    private Map<String, String[]> parameter = CollUtil.newHashMap();
    private String ip;
    private String requestId = IdUtil.fastSimpleUUID();

    public Map<String, String[]> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String[]> parameter) {
        this.parameter = parameter;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
