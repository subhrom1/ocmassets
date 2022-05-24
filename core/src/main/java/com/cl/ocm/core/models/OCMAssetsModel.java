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

import static com.cl.ocm.core.constants.OCMAssetConstants.LIMIT_PARAM;
import static com.cl.ocm.core.constants.OCMAssetConstants.OFFSET_PARAM;

/**
 * The Model Class which is attached to OCM Assets Fetch HTL to show
 * the fetched asset data on page.
 */
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class OCMAssetsModel {

    private List<OCMAsset> ocmAssets;
    private int offset;
    private int limit;

    @Self
    //Encapsulates the request made for fetching data
    private HttpServletRequest request;

    @Inject
    //Service to fetch OCM Assets
    private OCMAssetFetchService ocmAssetFetchService;

    @PostConstruct
    /**
     * The <code>init</code> method is run when the Model initializes
     */
    protected void init() {

        //fetches offset and limit if they are not null
        Object offsetObj = request.getParameter(OFFSET_PARAM);
        Object limitObj = request.getParameter(LIMIT_PARAM);

        if (offsetObj != null) {
            offset = Integer.valueOf(offsetObj.toString());
        }

        if (limitObj != null) {
            limit = Integer.valueOf(limitObj.toString());
        }
        ocmAssets = ocmAssetFetchService.fetchOCMAssetLinks(offset, limit);
    }

    /**
     * The list of assets fetched from OCM
     *
     * @return the asset list
     */
    public List<OCMAsset> getOcmAssets() {
        return ocmAssets;
    }
}
