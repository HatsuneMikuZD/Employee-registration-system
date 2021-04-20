package com.miku.model.index.servlet;

import cn.hutool.core.collection.CollUtil;
import com.miku.commom.domain.UniversalHttpResponse;
import com.miku.commom.servlet.BaseServlet;
import jakarta.servlet.annotation.WebServlet;

import java.util.Map;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends BaseServlet {


    @Override
    public UniversalHttpResponse doAction() {
        UniversalHttpResponse response = new UniversalHttpResponse();
        Map<String, Object> root = CollUtil.newHashMap();
        root.put("title","111-2222-44444");
        response.setData(root);
        response.setViewName("index.ftl");
        return response;
    }
}
