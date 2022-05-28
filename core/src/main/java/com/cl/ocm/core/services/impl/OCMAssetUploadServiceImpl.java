package com.cl.ocm.core.services.impl;

import com.cl.ocm.core.constants.OCMAssetMimeType;
import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.dto.SelectedAssets;
import com.cl.ocm.core.services.OCMAssetUploadService;
import com.cl.ocm.core.utils.OCMAssetsUtils;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.cl.ocm.core.constants.OCMAssetConstants.*;

@Component(service = OCMAssetUploadService.class)

/**
 * The class <code>OCMAssetUploadServiceImpl</code> uploads assets selected
 * for upload from OCM-AEM Console to DAM.
 *
 * The path where assets are uploaded is specified by a configuration for this
 * service: DAM Upload Folder. All OCM Specific assets are uploaded to the
 * DAM Upload Folder as specified by the user. Once assets are uploaded,
 * they become AEM assets and can be used by AEM authors for another through any
 * component which supports AEM assets.
 *
 */
public class OCMAssetUploadServiceImpl implements OCMAssetUploadService {

    //Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(OCMAssetUploadServiceImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private HttpURLConnection httpCon;

    /**
     * Uploads selected assets to AEM by making a call to AssetManager
     * interface.
     *
     * @param selectedAssets List of assets selected for upload
     * @param uploadPath     Path in DAM where selected assets are uploaded
     */
    @Override
    public void uploadAssets(SelectedAssets selectedAssets, String uploadPath) throws Exception {

        //Fetches service resolver and adopts it to AssetManager to uplaad AEM asset
        ResourceResolver resourceResolver = OCMAssetsUtils.getOCMAssetResolver(resourceResolverFactory);
        AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);

        if (assetManager == null) {
            //Returns if assetManager cannot be fetched
            LOGGER.error("Cannot adapt resourceResolver to AssetManager..");
            return;
        }

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

            if (ASSET_EXTENSION_3GP.equalsIgnoreCase(assetExtension)) {
                assetExtension = MIMETYPE_MAPPER_3GP;
            }

            OCMAssetMimeType ASSET_MIME_TYPE;
            try {
                ASSET_MIME_TYPE = OCMAssetMimeType.valueOf(assetExtension.toUpperCase());
            } catch (IllegalArgumentException ie) {
                LOGGER.error("Extension type of asset: {} currently not supported for AEM upload.", assetName);
                throw new Exception(ie);
            }
            httpCon = (HttpURLConnection) new URL(assetLink).openConnection();
            httpCon.addRequestProperty(USER_AGENT_PARAM, USER_AGENT_PARAM_VALUE);
            Asset createdAsset = assetManager.createAsset(uploadPath + SLASH + assetName,
                    httpCon.getInputStream(), ASSET_MIME_TYPE.assetType, true);

            Resource assetMetadata = resourceResolver.getResource(createdAsset.adaptTo(Resource.class),
                    JcrConstants.JCR_CONTENT + SLASH + ASSET_METADATA_FOLDER_NAME);
            ModifiableValueMap valueMap = assetMetadata.adaptTo(ModifiableValueMap.class);
            valueMap.put(ASSET_METADATA_PROP_TITLE, assetTitle);
            resourceResolver.commit();
        }

    }
}
