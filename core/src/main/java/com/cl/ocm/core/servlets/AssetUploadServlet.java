package com.cl.ocm.core.servlets;

import com.cl.ocm.core.dto.SelectedAssets;
import com.day.cq.dam.api.AssetManager;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.cl.ocm.core.constants.OCMAssetConstants.*;

@Component(service = {
        Servlet.class
}, property = {
        "sling.servlet.methods=post",
        "sling.servlet.paths=/bin/uploadassets"
})
@Designate(ocd = AssetUploadServlet.Config.class)

/**
 * The class <code>AssetUploadServlet</code> contains the logic to upload Assets to
 * AEM. As payload, this servlet expects the list of all asset links which it uploads
 * to AEM.
 */
public class AssetUploadServlet extends SlingAllMethodsServlet {

    //Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetUploadServlet.class);

    //The path in DAM under which assets need to be uploaded
    private String uploadPath;

    /**
     * The POST call to <code>AssetUploadServlet</code> maps to this method.
     *
     * @param request  The Servlet Request
     * @param response The Servlet Response where it denotes success/failure.
     */
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            response.setContentType(CONTENT_TYPE_JSON);

            //It is assumed here that the requester is an admin who has permission to write
            //assets under the configured path in DAM.
            AssetManager assetManager = request.getResourceResolver().adaptTo(AssetManager.class);

            if (assetManager == null) {
                //Returns if assetManager cannot be fetched
                LOGGER.error("Cannot adapt resourceResolver to AssetManager..");
                return;
            }

            //Reads request body and extracts links
            String reqBody = IOUtils.toString(request.getReader());
            Gson gson = new Gson();
            SelectedAssets selectedAssets = gson.fromJson(reqBody, SelectedAssets.class);

            //Uploads asset corresponding to each link to AEM
            for (String assetLink : selectedAssets.getLinks()) {
                String assetName = StringUtils.substringAfterLast(assetLink, SLASH);
                HttpURLConnection httpCon = (HttpURLConnection) new URL(assetLink).openConnection();
                httpCon.addRequestProperty(USER_AGENT_PARAM, USER_AGENT_PARAM_VALUE);
                assetManager.createAsset(this.uploadPath + SLASH + assetName, httpCon.getInputStream(), "image/jpeg", true);
            }
            //Success flow
            response.getWriter().println(SUCCESS_RESPONSE);

        } catch (Exception ex) {
            //Failure flow
            LOGGER.error("Exception in AssetUploadServlet:{}", ex.getMessage());
            response.setStatus(ERROR_STATUS_CODE);
        }
    }

    /**
     * This method is called when the component is activated
     * in OSGi container
     *
     * @param config The configuration parameters
     */
    @Activate
    protected void activate(final Config config) {
        this.uploadPath = config.uploadPath();
    }


    /**
     * The Configuration for OCM Asset Upload Servet
     */
    @ObjectClassDefinition(name = "Oracle Content Management - Asset Upload Configuration",
            description = "Configuration for uploading assets from Oracle OCM")
    public @interface Config {

        @AttributeDefinition(name = "DAM Upload Folder", description = "Folder in DAM (AEM) where OCM Assets are uploaded")
        String uploadPath() default "/content/dam/ocm";
    }
}