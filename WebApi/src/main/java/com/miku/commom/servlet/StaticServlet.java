package com.miku.commom.servlet;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.net.url.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = "/static/*")
public class StaticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求路径
        UrlPath urlPath = UrlPath.of(req.getRequestURI(), StandardCharsets.UTF_8);
        //目标路径
        UrlPath descPath = UrlPath.of("", StandardCharsets.UTF_8);
        List<String> segments = urlPath.getSegments();
        for (int i = segments.indexOf("static"); i < segments.size(); i++) {
            descPath.add(segments.get(i));
        }

        ClassPathResource resource = new ClassPathResource(descPath.toString());
        resource.writeTo(resp.getOutputStream());
    }
}
