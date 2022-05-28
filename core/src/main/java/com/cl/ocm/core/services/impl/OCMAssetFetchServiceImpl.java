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

        ocmAssetsList.add(new OCMAsset("Sample SVG","IMAGE" ,"https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg","https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg"));
        ocmAssetsList.add(new OCMAsset("Sample TIFF","IMAGE" ,"https://filesamples.com/samples/image/tiff/sample_640%C3%97426.tiff","https://filesamples.com/samples/image/tiff/sample_640%C3%97426.tiff"));
        ocmAssetsList.add(new OCMAsset("Sample BMP","IMAGE" ,"https://eeweb.engineering.nyu.edu/~yao/EL5123/image/lena_gray.bmp","https://eeweb.engineering.nyu.edu/~yao/EL5123/image/lena_gray.bmp"));

        ocmAssetsList.add(new OCMAsset("Sample Large File","VIDEO" ,"http://212.183.159.230/1GB.zip","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample MP4","VIDEO" ,"http://mirrors.standaloneinstaller.com/video-sample/star_trails.mp4","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample MP4 2", "VIDEO","http://mirrors.standaloneinstaller.com/video-sample/metaxas-keller-Bell.mp4","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample PDF","VIDEO","http://www.africau.edu/images/default/sample.pdf","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample MOV","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/Panasonic_HDC_TM_700_P_50i.mov","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample 3GP","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/metaxas-keller-Bell.3gp","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample AVI","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/TRA3106.avi","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample FLV","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/Panasonic_HDC_TM_700_P_50i.flv","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample MPEG","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/lion-sample.mpeg","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample MPG","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/grb_2.mpg","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample WMV","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/P6090053.wmv","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample VOB","VIDEO","http://mirrors.standaloneinstaller.com/video-sample/TRA3106.vob","/content/dam/ocmassets/Video-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample DOCX","DOCUMENT","https://filesamples.com/samples/document/docx/sample4.docx","/content/dam/ocmassets/Document-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample DOC","DOCUMENT","https://filesamples.com/samples/document/doc/sample2.doc","/content/dam/ocmassets/Document-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample PPTX","DOCUMENT","https://scholar.harvard.edu/files/torman_personal/files/samplepptx.pptx","/content/dam/ocmassets/Document-Placeholder.jpeg"));
        ocmAssetsList.add(new OCMAsset("Sample PPT","DOCUMENT","https://acdbio.com/sites/default/files/sample.ppt","/content/dam/ocmassets/Document-Placeholder.jpeg"));

        ocmAssetsList.add(new OCMAsset("Sea","IMAGE", "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989_1280.jpg", "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Mountain","IMAGE", "https://cdn.pixabay.com/photo/2013/04/04/12/34/mountains-100367_1280.jpg", "https://cdn.pixabay.com/photo/2013/04/04/12/34/mountains-100367_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Birds","IMAGE","https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg","https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Berries", "IMAGE","https://cdn.pixabay.com/photo/2010/12/13/10/05/berries-2277_1280.jpg","https://cdn.pixabay.com/photo/2010/12/13/10/05/berries-2277_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Beach", "IMAGE","https://cdn.pixabay.com/photo/2014/12/16/22/25/woman-570883_1280.jpg","https://cdn.pixabay.com/photo/2014/12/16/22/25/woman-570883_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Autumn", "IMAGE","https://cdn.pixabay.com/photo/2013/11/28/10/03/river-219972_1280.jpg","https://cdn.pixabay.com/photo/2013/11/28/10/03/river-219972_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Flowers","IMAGE", "https://cdn.pixabay.com/photo/2014/04/14/20/11/pink-324175_1280.jpg", "https://cdn.pixabay.com/photo/2014/04/14/20/11/pink-324175_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Sunlight","IMAGE", "https://cdn.pixabay.com/photo/2014/02/27/16/10/tree-276014_1280.jpg", "https://cdn.pixabay.com/photo/2014/02/27/16/10/tree-276014_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Bloom", "IMAGE","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_1280.jpg","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Field", "IMAGE","https://cdn.pixabay.com/photo/2014/01/22/19/44/flower-field-250016_1280.jpg","https://cdn.pixabay.com/photo/2014/01/22/19/44/flower-field-250016_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Woods","IMAGE", "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072821_1280.jpg", "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072821_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Drive","IMAGE", "https://cdn.pixabay.com/photo/2013/11/28/10/36/road-220058_1280.jpg", "https://cdn.pixabay.com/photo/2013/11/28/10/36/road-220058_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Birds","IMAGE", "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg", "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Path", "IMAGE","https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297_1280.jpg","https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Clouds","IMAGE", "https://cdn.pixabay.com/photo/2013/10/02/23/03/mountains-190055_1280.jpg", "https://cdn.pixabay.com/photo/2013/10/02/23/03/mountains-190055_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Summit","IMAGE", "https://cdn.pixabay.com/photo/2014/10/07/13/48/mountain-477832_1280.jpg", "https://cdn.pixabay.com/photo/2014/10/07/13/48/mountain-477832_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Snow Mountain","IMAGE", "https://cdn.pixabay.com/photo/2015/04/23/22/01/mountains-736886_1280.jpg", "https://cdn.pixabay.com/photo/2015/04/23/22/01/mountains-736886_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Help", "IMAGE","https://cdn.pixabay.com/photo/2016/11/08/05/20/sunset-1807524_1280.jpg","https://cdn.pixabay.com/photo/2016/11/08/05/20/sunset-1807524_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Sunrise","IMAGE", "https://cdn.pixabay.com/photo/2015/01/07/15/51/woman-591576_1280.jpg", "https://cdn.pixabay.com/photo/2015/01/07/15/51/woman-591576_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Dew", "IMAGE","https://cdn.pixabay.com/photo/2014/09/14/18/04/dandelion-445228_1280.jpg","https://cdn.pixabay.com/photo/2014/09/14/18/04/dandelion-445228_1280.jpg"));

        ocmAssetsList.add(new OCMAsset("Rose","IMAGE", "https://cdn.pixabay.com/photo/2013/08/22/19/18/flowers-174817_1280.jpg","https://cdn.pixabay.com/photo/2013/08/22/19/18/flowers-174817_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Red Flower", "IMAGE","https://cdn.pixabay.com/photo/2013/04/03/21/25/flower-100263_1280.jpg","https://cdn.pixabay.com/photo/2013/04/03/21/25/flower-100263_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Blue Rose", "IMAGE","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_1280.jpg","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_1280.jpg"));
        ocmAssetsList.add(new OCMAsset("Bunch","IMAGE", "https://cdn.pixabay.com/photo/2014/06/22/05/49/red-374318_1280.jpg", "https://cdn.pixabay.com/photo/2014/06/22/05/49/red-374318_1280.jpg"));

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
            ocmAssetsList.add(new OCMAsset("Big Mountain", "IMAGE","https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg","https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Arctic Fox", "IMAGE","https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445_1280.jpg","https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Aurora", "IMAGE","https://cdn.pixabay.com/photo/2016/02/09/19/57/aurora-1190254_1280.jpg","https://cdn.pixabay.com/photo/2016/02/09/19/57/aurora-1190254_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Polar Light","IMAGE", "https://cdn.pixabay.com/photo/2016/02/07/19/48/aurora-1185464_1280.jpg", "https://cdn.pixabay.com/photo/2016/02/07/19/48/aurora-1185464_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Fun", "IMAGE","https://cdn.pixabay.com/photo/2017/02/16/19/47/bokeh-2072271_1280.jpg","https://cdn.pixabay.com/photo/2017/02/16/19/47/bokeh-2072271_1280.jpg"));
            return ocmAssetsList;

        }

        else if (offset == 80) {

            List<OCMAsset> ocmAssetsList = new ArrayList<>();
            ocmAssetsList.add(new OCMAsset("Candles","IMAGE", "https://cdn.pixabay.com/photo/2016/12/22/22/07/hands-1926414_1280.jpg", "https://cdn.pixabay.com/photo/2016/12/22/22/07/hands-1926414_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Book","IMAGE", "https://cdn.pixabay.com/photo/2016/03/27/19/32/book-1283865_1280.jpg", "https://cdn.pixabay.com/photo/2016/03/27/19/32/book-1283865_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Library","IMAGE", "https://cdn.pixabay.com/photo/2016/02/16/21/07/books-1204029_1280.jpg", "https://cdn.pixabay.com/photo/2016/02/16/21/07/books-1204029_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("Eye Glasses","IMAGE", "https://cdn.pixabay.com/photo/2015/11/19/21/10/glasses-1052010_1280.jpg", "https://cdn.pixabay.com/photo/2015/11/19/21/10/glasses-1052010_1280.jpg"));
            ocmAssetsList.add(new OCMAsset("School Book", "IMAGE","https://cdn.pixabay.com/photo/2016/06/01/06/26/open-book-1428428_1280.jpg","https://cdn.pixabay.com/photo/2016/06/01/06/26/open-book-1428428_1280.jpg"));
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