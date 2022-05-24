package com.cl.ocm.core.services;

import com.cl.ocm.core.dto.OCMAsset;

import java.util.List;

/**
 * Class <code>OCMAssetFetchService</code> contains methods to fetch
 * OCM assets from AEM Channel.
 */
public interface OCMAssetFetchService {

    /**
     * Fetches OCM Assets. This method only fetches a limited
     * number of assets (first page if assets fetch is paginated).
     *
     * @return List of Assets to be uploaded from OCM to AEM
     */
    List<OCMAsset> fetchOCMAssetLinks();

    /**
     * Fetches OCM Assets in a paginated form depending on the offset
     * (page no) and limit(no of items to be fetched per page).
     *
     * @return List of Assets to be uploaded from OCM to AEM
     */
    List<OCMAsset> fetchOCMAssetLinks(int offset, int limit);
}
