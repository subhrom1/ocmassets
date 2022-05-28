package com.cl.ocm.core.servlets;

import com.cl.ocm.core.dto.SelectedAssets;
import com.cl.ocm.core.services.OCMAssetUploadService;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

import static com.cl.ocm.core.constants.OCMAssetConstants.*;

@Component(service = {
        Servlet.class
}, property = {
        "sling.servlet.methods=post",
        "sling.servlet.paths=/bin/uploadassets"
})

/**
 * The class <code>AssetUploadServlet</code> contains the logic to upload Assets to
 * AEM. As payload, this servlet expects the list of all asset links which it uploads
 * to AEM.
 */
public class AssetUploadServlet extends SlingAllMethodsServlet {

    //Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetUploadServlet.class);

    @Reference
    private OCMAssetUploadService ocmAssetUploadService;


    /**
     * The POST call to <code>AssetUploadServlet</code> maps to this method.
     *
     * @param request  The Servlet Request
     * @param response The Servlet Response where it denotes success/failure.
     */
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            response.setContentType(CONTENT_TYPE_JSON);

            //Reads request body and extracts links
            String reqBody = IOUtils.toString(request.getReader());
            Gson gson = new Gson();
            SelectedAssets selectedAssets = gson.fromJson(reqBody, SelectedAssets.class);

            String uploadPath = selectedAssets.getDamUploadPath();

            ocmAssetUploadService.uploadAssets(selectedAssets, uploadPath);

            //Success flow
            response.getWriter().println(SUCCESS_RESPONSE);

        } catch (Exception ex) {
            //Failure flow
            LOGGER.error("Exception in AssetUploadServlet:{}", ex.getMessage());
            response.setStatus(ERROR_STATUS_CODE);
        }
    }
}