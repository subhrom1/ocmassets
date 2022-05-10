package com.cl.ocm.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = {
        Servlet.class
}, property = {
        "sling.servlet.methods=post",
        "sling.servlet.paths=/bin/uploadassets"
})
public class AssetUploadServlet extends SlingAllMethodsServlet {

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().println("{\"Status\":\"Done!\"}");
    }

}