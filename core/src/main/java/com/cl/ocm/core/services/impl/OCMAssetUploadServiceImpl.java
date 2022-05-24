package com.cl.ocm.core.services.impl;

import com.cl.ocm.core.dto.SelectedAssets;
import com.cl.ocm.core.services.OCMAssetUploadService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import static com.cl.ocm.core.constants.OCMAssetConstants.OCM_DEFAULT_PATH;

@Component(service = OCMAssetUploadService.class)
@Designate(ocd = OCMAssetUploadServiceImpl.Config.class)

/**
 * The class <code>OCMAssetUploadServiceImpl</code> uploads assets selected
 * for upload from OCM-AEM Console to DAM.
 *
 * The path where assets are uploaded is specified by a configuration for this
 * service: DAM Upload Folder. All OCM Specific assets are uploaded to the
 * DAM Upload Folder as specified by this configuration. Once assets are uploaded,
 * they become AEM assets and can be used by AEM authors for another through any
 * component which supports AEM assets.
 *
 */
public class OCMAssetUploadServiceImpl implements OCMAssetUploadService {


    /**
     * Uploads selected assets to AEM by making a call to AssetManager
     * interface.
     *
     * @param selectedAssets List of assets selected for upload
     */
    @Override
    public void uploadAssets(SelectedAssets selectedAssets) {

    }

    /**
     * The Configuration for OCM Asset Upload to AEM
     */
    @ObjectClassDefinition(name = "OCM Asset Upload Service Configuration",
            description = "Configuration for Uploading OCM Assets to AEM")
    public @interface Config {

        @AttributeDefinition(name = "DAM Upload Folder",
                description = "Folder in DAM (AEM) where OCM Assets are uploaded")
        String uploadPath() default OCM_DEFAULT_PATH;

    }
}
