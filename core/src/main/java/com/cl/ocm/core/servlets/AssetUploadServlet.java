package com.cl.ocm.core.servlets;

import com.cl.ocm.core.dto.SelectedAssets;
import com.day.cq.dam.api.AssetManager;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component(service = {
        Servlet.class
}, property = {
        "sling.servlet.methods=post",
        "sling.servlet.paths=/bin/uploadassets"
})
@Designate(ocd = AssetUploadServlet.Config.class)
public class AssetUploadServlet extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetUploadServlet.class);


    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        AssetManager assetManager = request.getResourceResolver().adaptTo(AssetManager.class);

        if (assetManager == null) {
            LOGGER.error("Cannot adapt resourceResolver to AssetManager..");
            return;
        }

        String reqBody = IOUtils.toString(request.getReader());
        Gson gson = new Gson();
        SelectedAssets selectedAssets = gson.fromJson(reqBody, SelectedAssets.class);

        for (String assetLink : selectedAssets.getLinks()) {
            String assetName = StringUtils.substringAfterLast(assetLink, "/");
            HttpURLConnection httpcon = (HttpURLConnection) new URL(assetLink).openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
            assetManager.createAsset("/content/dam/ocm/" + assetName, httpcon.getInputStream(), "image/jpeg", true);
        }
        response.getWriter().println("{\"Status\":\"Done!\"}");
    }


    /**
     * The Configuration for OCM Asset Upload Servet
     */
    @ObjectClassDefinition(name = "Oracle Content Management - Asset Upload Configuration",
            description = "Configuration for uploading assets from Oracle OCM")
    public @interface Config {

        @AttributeDefinition(name = "DAM Upload Folder", description = "Folder in DAM (AEM) where OCM Assets are uploaded")
        String uploadPath() default "/content/dam/ocm/";

    }

}