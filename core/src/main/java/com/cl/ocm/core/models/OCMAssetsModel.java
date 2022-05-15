package com.cl.ocm.core.models;

import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class})
public class OCMAssetsModel {

    private List<OCMAsset> ocmAssets;
    private int offset;
    private int limit;

    @Self
    private HttpServletRequest request;

    @Inject
    private OCMAssetFetchService ocmAssetFetchService;

    @PostConstruct
    protected void init() {

        Object offsetObj = request.getParameter("offset");
        Object limitObj = request.getParameter("limit");

        if (offsetObj != null) {
            offset = Integer.valueOf(offsetObj.toString());
        }

        if (limitObj != null) {
            limit = Integer.valueOf(limitObj.toString());;
        }
        ocmAssets = ocmAssetFetchService.fetchOCMAssetLinks(offset,limit);
    }

    public List<OCMAsset> getOcmAssets() {
        return ocmAssets;
    }

    public void setOcmAssets(List<OCMAsset> assetLinks) {
        this.ocmAssets = ocmAssets;
    }
}
