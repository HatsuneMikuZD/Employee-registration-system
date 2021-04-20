package com.miku.commom.servlet;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.net.url.UrlPath;
import cn.hutool.log.StaticLog;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(urlPatterns = "*.ftl")
public class FreemarkerViewServlet extends HttpServlet {

    private Configuration configuration;

    @Override
    public void init() throws ServletException {
        StaticLog.info("Configuration 初始化");
        configuration = new Configuration(Configuration.VERSION_2_3_21);
        ClassPathResource templates = new ClassPathResource("templates");
        try {
            configuration.setDirectoryForTemplateLoading(templates.getFile());
            configuration.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        UrlPath urlPath = UrlPath.of(requestURI, StandardCharsets.UTF_8);
        String templateName = CollUtil.getLast(urlPath.getSegments());
        Template template = configuration.getTemplate(templateName);


        Map<String, Object> root = CollUtil.newHashMap();
        Enumeration<String> attributeNames = req.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            root.put(attributeName, req.getAttribute(attributeName));
        }

        StaticLog.info("\nrequestURI=>{}\n templateName=>{}\n root={}\n", requestURI, templateName, root);

        try {
            template.process(root, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
