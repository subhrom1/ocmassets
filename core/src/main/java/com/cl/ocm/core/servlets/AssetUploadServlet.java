package com.cl.ocm.core.servlets;

import com.cl.ocm.core.constants.OCMAssetMimeType;
import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.dto.SelectedAssets;
import com.cl.ocm.core.utils.OCMAssetsUtils;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
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

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    /**
     * The POST call to <code>AssetUploadServlet</code> maps to this method.
     *
     * @param request  The Servlet Request
     * @param response The Servlet Response where it denotes success/failure.
     */
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            response.setContentType(CONTENT_TYPE_JSON);

            //Fetches service resolver and adopts it to AssetManager to uplaad AEM asset
            ResourceResolver resourceResolver = OCMAssetsUtils.getOCMAssetResolver(resourceResolverFactory);
            AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);

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
            for (OCMAsset selectedAsset : selectedAssets.getSelectedAssets()) {

                String assetLink = selectedAsset.getAssetLink();
                String assetTitle = selectedAsset.getAssetTitle();
                String assetName = StringUtils.substringAfterLast(assetLink, SLASH);
                String assetExtension = StringUtils.substringAfterLast(assetLink, DOT);

                if (StringUtils.isEmpty(assetExtension)) {
                    LOGGER.info("Asset Extension is null..using jpeg as default extension..");
                    assetExtension = OCMAssetMimeType.JPEG.extension;
                }

                OCMAssetMimeType ASSET_MIME_TYPE;
                try {
                    ASSET_MIME_TYPE = OCMAssetMimeType.valueOf(assetExtension.toUpperCase());
                } catch (IllegalArgumentException ie) {
                    LOGGER.error("Extension type of asset: {} currently not supported for AEM upload.", assetName);
                    throw new Exception(ie);
                }
                HttpURLConnection httpCon = (HttpURLConnection) new URL(assetLink).openConnection();
                httpCon.addRequestProperty(USER_AGENT_PARAM, USER_AGENT_PARAM_VALUE);
                Asset createdAsset = assetManager.createAsset(this.uploadPath + SLASH + assetName,
                        httpCon.getInputStream(), ASSET_MIME_TYPE.assetType, true);

                Resource assetMetadata = resourceResolver.getResource(createdAsset.adaptTo(Resource.class),
                        JcrConstants.JCR_CONTENT + SLASH + ASSET_METADATA_FOLDER_NAME);
                ModifiableValueMap valueMap = assetMetadata.adaptTo(ModifiableValueMap.class);
                valueMap.put(ASSET_METADATA_PROP_TITLE, assetTitle);
                resourceResolver.commit();
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