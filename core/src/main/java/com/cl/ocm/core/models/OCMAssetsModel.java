package com.cl.ocm.core.models;

import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class OCMAssetsModel {

    private List<OCMAsset> ocmAssets;

    @Inject
    private OCMAssetFetchService ocmAssetFetchService;

    @PostConstruct
    protected void init() {
        ocmAssets = ocmAssetFetchService.fetchOCMAssetLinks();
    }

    public List<OCMAsset> getOcmAssets() {
        return ocmAssets;
    }

    public void setOcmAssets(List<OCMAsset> assetLinks) {
        this.ocmAssets = ocmAssets;
    }
}
