package com.miku.commom.servlet;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.miku.commom.domain.UniversalHttpRequest;
import com.miku.commom.domain.UniversalHttpResponse;
import com.miku.commom.domain.UniversalHttpSession;
import com.miku.commom.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {

    private static final Log log = LogFactory.get();
    private UniversalHttpRequest universalHttpRequest;
    private UniversalHttpSession universalHttpSession;
    private User currUser;


    public BaseServlet() {
        setUniversalHttpRequest(new UniversalHttpRequest());
        setUniversalHttpSession(new UniversalHttpSession());
    }

    public UniversalHttpRequest getUniversalHttpRequest() {
        return universalHttpRequest;
    }

    private void setUniversalHttpRequest(UniversalHttpRequest universalHttpRequest) {
        this.universalHttpRequest = universalHttpRequest;
    }

    public UniversalHttpSession getUniversalHttpSession() {
        return universalHttpSession;
    }

    private void setUniversalHttpSession(UniversalHttpSession universalHttpSession) {
        this.universalHttpSession = universalHttpSession;
    }

    public User getCurrUser() {
        return currUser;
    }

    private void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        universalHttpRequest.setParameter(req.getParameterMap());
        log.info("UniversalHttpRequest => {}", JSONUtil.toJsonStr(universalHttpRequest));

        /**
         * request.getSession(true)：若存在会话则返回该会话，否则新建一个会话。
         * request.getSession(false)：若存在会话则返回该会话，否则返回NULL。
         */
        HttpSession currSession = req.getSession(false);
        if (ObjectUtil.isNotNull(currSession)) {
            Enumeration<String> sessionAttributeNames = currSession.getAttributeNames();
            while (sessionAttributeNames.hasMoreElements()) {
                String keys = sessionAttributeNames.nextElement();
                universalHttpSession.getCurrSession().put(keys, currSession.getAttribute(keys));
            }

            User currUser = (User) currSession.getAttribute("currUser");
            setCurrUser(currUser);
        }
        log.info("UniversalHttpSession => {}", JSONUtil.toJsonStr(universalHttpSession));
        log.info("CurrUser => {}", JSONUtil.toJsonStr(currUser));

        UniversalHttpResponse universalHttpResponse = doAction();
        log.info("UniversalHttpResponse => {}", JSONUtil.toJsonStr(universalHttpResponse));
        if (ObjectUtil.isNotNull(universalHttpResponse)) {
            String path = req.getContextPath();
            String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
            universalHttpResponse.getData().put("basePath", basePath);

            for (Map.Entry<String, Object> entry : universalHttpResponse.getData().entrySet()) {
                req.setAttribute(entry.getKey(), entry.getValue());
            }

            if (StrUtil.isBlank(universalHttpResponse.getViewName())) {
                throw new ServletException("viewName 不能为空白字符");
            }
            req.getRequestDispatcher(universalHttpResponse.getViewName()).forward(req, resp);
        } else {
            throw new NullPointerException("UniversalHttpResponse 不能为空");
        }
    }

    public abstract UniversalHttpResponse doAction();

}
