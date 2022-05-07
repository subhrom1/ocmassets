package com.cl.ocm.core.models;

import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class OCMAssetsModel {

    private List<String> assetLinks;

    @Inject
    private OCMAssetFetchService ocmAssetFetchService;

    @PostConstruct
    protected void init() {
        assetLinks = ocmAssetFetchService.fetchOCMAssetLinks();
    }

    public List<String> getAssetLinks() {
        return assetLinks;
    }

    public void setAssetLinks(List<String> assetLinks) {
        this.assetLinks = assetLinks;
    }
}
