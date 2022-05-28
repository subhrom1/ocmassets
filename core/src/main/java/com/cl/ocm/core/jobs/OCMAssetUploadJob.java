package com.cl.ocm.core.jobs;

import com.cl.ocm.core.constants.OCMAssetMimeType;
import com.cl.ocm.core.utils.OCMAssetsUtils;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.cl.ocm.core.constants.OCMAssetConstants.*;

@Component(service = JobConsumer.class,
        immediate = true,
        property = {
                JobConsumer.PROPERTY_TOPICS + EQUALS + UPLOAD_JOB_TOPIC_NAME
        })
/**
 * The Class <code>OCMAssetUploadJob</code> is the upload topic
 * consumer which does the actual heavy-lifting task of uploading
 * each OCM asset to AEM DAM.
 *
 * Since this job is executing async by the sling job execution pipeline.
 * the AEM UI is free for other tasks. This would otherwise have caused
 * bottlenecks when very large files get uploaded.
 */
public class OCMAssetUploadJob implements JobConsumer {

    //Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(OCMAssetUploadJob.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private HttpURLConnection httpCon;

    @Override
    /**
     * The process method is guaranteed to be executed once, to upload asset to AEM.
     * However, its execution time may not be immediate after asset upload.
     */
    public JobResult process(Job job) {

        try {
            String assetTitle = job.getProperty(UPLOAD_JOB_CONSTANT_ASSET_TITLE, String.class);
            String assetLink = job.getProperty(UPLOAD_JOB_CONSTANT_ASSET_LINK, String.class);
            String uploadPath = job.getProperty(UPLOAD_JOB_CONSTANT_UPLOAD_PATH, String.class);

            if (StringUtils.isAnyEmpty(assetTitle, assetLink, uploadPath)) {
                LOGGER.error("Cannot fetch assetTitle or AssetLink for the asset..");
                return JobResult.FAILED;
            }

            uploadAssets(assetTitle, assetLink, uploadPath);
            return JobResult.OK;

        } catch (Exception e) {
            LOGGER.error("Extension is upload job. Message:{}", e.getLocalizedMessage());
            return JobResult.FAILED;
        }
    }

    /*
     * Uploads the asset to AEM after determining its mime-type.
     */
    private void uploadAssets(String assetTitle, String assetLink, String uploadPath) throws Exception {

        //Fetches service resolver and adopts it to AssetManager to uplaad AEM asset
        ResourceResolver resourceResolver = OCMAssetsUtils.getOCMAssetResolver(resourceResolverFactory);
        AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);

        if (assetManager == null) {
            //Returns if assetManager cannot be fetched
            LOGGER.error("Cannot adapt resourceResolver to AssetManager..");
            throw new Exception("assetManager is null..");
        }

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

        //Adds metadata (asset title) to created asset
        Resource assetMetadata = resourceResolver.getResource(createdAsset.adaptTo(Resource.class),
                JcrConstants.JCR_CONTENT + SLASH + ASSET_METADATA_FOLDER_NAME);
        ModifiableValueMap valueMap = assetMetadata.adaptTo(ModifiableValueMap.class);
        valueMap.put(ASSET_METADATA_PROP_TITLE, assetTitle);
        resourceResolver.commit();
    }
}
