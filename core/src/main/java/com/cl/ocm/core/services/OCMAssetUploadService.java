package com.cl.ocm.core.services;

import com.cl.ocm.core.dto.SelectedAssets;

/**
 * Class <code>OCMAssetUploadService</code> contains methods to upload
 * OCM assets to AEM.
 */
public interface OCMAssetUploadService {

    /**
     * The method to upload OCM assets to AEM
     * @param selectedAssets Asset selected for upload to AEM
     * @param uploadPath Path in DAM where selected assets will be uploaded
     */
    void uploadAssets(SelectedAssets selectedAssets,String uploadPath) throws Exception;

}
