package com.cl.ocm.core.services.impl;

import com.cl.ocm.core.constants.OCMAssetConstants;
import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.dto.SelectedAssets;
import com.cl.ocm.core.services.OCMAssetUploadService;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = OCMAssetUploadService.class)

/**
 * The class <code>OCMAssetUploadServiceImpl</code> uploads assets selected
 * for upload from OCM-AEM Console to DAM, by creating sling Jobs for each
 * upload.
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
    private JobManager jobManager;

    /**
     * Uploads selected assets to AEM by making a call to AssetManager
     * interface.
     *
     * @param selectedAssets List of assets selected for upload
     * @param uploadPath     Path in DAM where selected assets are to be uploaded
     */
    @Override
    public void uploadAssets(SelectedAssets selectedAssets, String uploadPath) throws Exception {

        //Uploads asset corresponding to each link to AEM
        for (OCMAsset selectedAsset : selectedAssets.getSelectedAssets()) {

            Map<String, Object> jobProperties = new HashMap<>();
            jobProperties.put(OCMAssetConstants.UPLOAD_JOB_CONSTANT_ASSET_TITLE, selectedAsset.getAssetTitle());
            jobProperties.put(OCMAssetConstants.UPLOAD_JOB_CONSTANT_ASSET_LINK, selectedAsset.getAssetLink());
            jobProperties.put(OCMAssetConstants.UPLOAD_JOB_CONSTANT_UPLOAD_PATH, uploadPath);

            //Adds job to upload each asset through jobManager
            jobManager.addJob(OCMAssetConstants.UPLOAD_JOB_TOPIC_NAME, jobProperties);

            LOGGER.debug("Job added:{}", jobProperties);
        }

    }
}
