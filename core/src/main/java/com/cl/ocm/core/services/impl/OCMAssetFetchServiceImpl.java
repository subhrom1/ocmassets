package com.cl.ocm.core.services.impl;

import com.cl.ocm.core.dto.OCMAsset;
import com.cl.ocm.core.services.OCMAssetFetchService;
import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An Implementation of <code>OCMAssetFetchService</code> which hard-codes the asset links for testing purpose,
 * once the actual connectivity with OCM REST APIs are available, this implementation will be replaced with the
 * actual data.
 */
@Component(service = OCMAssetFetchService.class)
@Designate(ocd = OCMAssetFetchServiceImpl.Config.class)
public class OCMAssetFetchServiceImpl implements OCMAssetFetchService {

    private String ocmUrl;

    /**
     * Mock implementation for fetching of OCM assets
     * @return list of Assets fetched from OCM.
     */
    @Override
    public List<OCMAsset> fetchOCMAssetLinks() {

        List<OCMAsset> ocmAssetsList = new ArrayList<>();

        ocmAssetsList.add(new OCMAsset("Sea", "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Mountain", "https://cdn.pixabay.com/photo/2013/04/04/12/34/mountains-100367_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Birds", "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Berries", "https://cdn.pixabay.com/photo/2010/12/13/10/05/berries-2277_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Beach", "https://cdn.pixabay.com/photo/2014/12/16/22/25/woman-570883_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Autumn", "https://cdn.pixabay.com/photo/2013/11/28/10/03/river-219972_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Flowers", "https://cdn.pixabay.com/photo/2014/04/14/20/11/pink-324175_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Sunlight", "https://cdn.pixabay.com/photo/2014/02/27/16/10/tree-276014_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Bloom", "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Field", "https://cdn.pixabay.com/photo/2014/01/22/19/44/flower-field-250016_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Woods", "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072821_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Drive", "https://cdn.pixabay.com/photo/2013/11/28/10/36/road-220058_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Birds", "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Path", "https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Clouds", "https://cdn.pixabay.com/photo/2013/10/02/23/03/mountains-190055_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Summit", "https://cdn.pixabay.com/photo/2014/10/07/13/48/mountain-477832_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Snow Mountain", "https://cdn.pixabay.com/photo/2015/04/23/22/01/mountains-736886_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Help", "https://cdn.pixabay.com/photo/2016/11/08/05/20/sunset-1807524_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Sunrise", "https://cdn.pixabay.com/photo/2015/01/07/15/51/woman-591576_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Dew", "https://cdn.pixabay.com/photo/2014/09/14/18/04/dandelion-445228_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Rose", "https://cdn.pixabay.com/photo/2013/08/22/19/18/flowers-174817_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Red Flower", "https://cdn.pixabay.com/photo/2013/04/03/21/25/flower-100263_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Blue Rose", "https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Bunch", "https://cdn.pixabay.com/photo/2014/06/22/05/49/red-374318_1280.jpg"));

        return ocmAssetsList;
    }

    /**
     * Mock implementation for fetching of OCM assets in paginated format.
     * @return list of Assets fetched from OCM.
     */
    @Override
    public List<OCMAsset> fetchOCMAssetLinks(int offset, int limit)  {

        if (offset >0 && offset < 800) {
            try {
                Thread.sleep(2000);
            }catch (InterruptedException ex) {
                //do nothing, as this is just a test code
            }
        }

        if (offset == 40) {
            List<OCMAsset> ocmAssetsList = new ArrayList<>();
            ocmAssetsList.add(new OCMAsset("Big Mountain", "https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Arctic Fox", "https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Aurora", "https://cdn.pixabay.com/photo/2016/02/09/19/57/aurora-1190254_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Polar Light", "https://cdn.pixabay.com/photo/2016/02/07/19/48/aurora-1185464_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Fun", "https://cdn.pixabay.com/photo/2017/02/16/19/47/bokeh-2072271_1280.jpg"));
            return ocmAssetsList;

        }

        else if (offset == 80) {

            List<OCMAsset> ocmAssetsList = new ArrayList<>();
            ocmAssetsList.add(new OCMAsset("Candles", "https://cdn.pixabay.com/photo/2016/12/22/22/07/hands-1926414_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Book", "https://cdn.pixabay.com/photo/2016/03/27/19/32/book-1283865_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Library", "https://cdn.pixabay.com/photo/2016/02/16/21/07/books-1204029_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Eye Glasses", "https://cdn.pixabay.com/photo/2015/11/19/21/10/glasses-1052010_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("School Book", "https://cdn.pixabay.com/photo/2016/06/01/06/26/open-book-1428428_1280.jpg"));
            return ocmAssetsList;
        }
        else if (offset > 800) {
            return Collections.emptyList();
        }
        return fetchOCMAssetLinks();
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