package com.cl.ocm.core.utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;

import static com.cl.ocm.core.constants.OCMAssetConstants.OCE_ASSET_SERVICE_USERNAME;

/**
 * This Class contains utility to be used in the
 * OCM Assets application.
 */
public class OCMAssetsUtils {

    /**
     * Returns OCM Asset ResourceResolver:this ResourceResolver is used to upload assets to AEM.
     *
     * Note: following  is the service-mepping added through configuration:
     *     ocmassets.core:oceassetservice=oceassetuser
     *
     * Thus, it is assumed that the AEM instance has a system user created which mapps to
     * "oceassetservice". This system user must have read, modify,create permissions for the folder
     * to which AEM assets need to be uploaded.
     *
     * @param resourceResolverFactory ResourceResolverFactory used to fetch service resolver
     * @return ResourceResolver: the service resolver used to upload AEM assets
     * @throws LoginException if the service resolver cannot be created due to insufficient mapping.
     */
    public static ResourceResolver getOCMAssetResolver(ResourceResolverFactory resourceResolverFactory)
            throws LoginException {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE,OCE_ASSET_SERVICE_USERNAME);
        return resourceResolverFactory.getServiceResourceResolver(param);
    }
}
