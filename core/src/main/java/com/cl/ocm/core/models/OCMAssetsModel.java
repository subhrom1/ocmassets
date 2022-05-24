package com.cl.ocm.core.models;

import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

/**
 * The Model Class which is attached to OCM Assets Fetch HTL to show
 * the fetched asset data on page.
 */
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class OCMAssetsModel {

    //Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(OCMAssetsModel.class);

    private List<OCMAsset> ocmAssets;
    private int offset;
    private int limit;

    @Self
    //Encapsulates the request made for fetching data
    private SlingHttpServletRequest request;

    @Inject
    //Service to fetch OCM Assets
    private OCMAssetFetchService ocmAssetFetchService;

    @PostConstruct
    /**
     * The <code>init</code> method is run when the Model initializes
     */
    protected void init() {

        //fetches offset and limit if they are not null
        String[] selectors = request.getRequestPathInfo().getSelectors();

        if (selectors != null && selectors.length >= 2) {
            try {
                offset = Integer.valueOf(selectors[0]);
                limit = Integer.valueOf(selectors[1]);
            } catch (NumberFormatException ne) {
                LOGGER.error("Exception in parsing non-numeric values for " +
                        "request selectors in OCMAssetModel.Will fetch data with offset=0, limit=0");
            }
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
