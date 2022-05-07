package com.cl.ocm.core.services.impl;

import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.util.ArrayList;
import java.util.List;

@Component(service = OCMAssetFetchService.class)
@Designate(ocd = OCMAssetFetchServiceImpl.Config.class)
public class OCMAssetFetchServiceImpl implements OCMAssetFetchService {

    private String ocmUrl;

    @Override
    public List<String> fetchOCMAssetLinks() {

        List<String> ocmAssetsList = new ArrayList<>();

        ocmAssetsList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg");
        ocmAssetsList.add("https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989_1280.jpg");
        ocmAssetsList.add("https://cdn.pixabay.com/photo/2013/04/04/12/34/mountains-100367_1280.jpg");
        ocmAssetsList.add("https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg");
        ocmAssetsList.add("https://cdn.pixabay.com/photo/2010/12/13/10/05/berries-2277_1280.jpg");

        return ocmAssetsList;
    }

    @Activate
    protected void activate(final Config config) {
        this.ocmUrl = config.ocmUrl();
    }

    /**
     * The Configuration for OCM Asset Link Fetcher
     */
    @ObjectClassDefinition(name = "OCM Asset Fetch Service Configuration", description = "Configuration for Fetching Assets from Oracle OCM")
    public @interface Config {

        @AttributeDefinition(name = "ocmUrl", description = "URL for connecting to OCM channel")
        String ocmUrl() default StringUtils.EMPTY;

    }
}
