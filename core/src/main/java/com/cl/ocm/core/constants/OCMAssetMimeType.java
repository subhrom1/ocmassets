package com.cl.ocm.core.constants;

/**
 * The enum to hold list of mimetypes (jpg, gif,svg, etc) which are
 * supported for upload to AEM.
 */
public enum OCMAssetMimeType {

    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    SVG("svg", "image/svg"),
    GIF("gif", "image/gif"),
    PNG("png", "image/png");

    //Extension as obtained from asset URL
    public final String extension;

    //The argument to be passed to asset manager as asset
    //mime type, e.g. image/jpeg
    public final String assetType;

    OCMAssetMimeType(String extension, String assetType) {
        this.extension = extension;
        this.assetType = assetType;
    }

    String getExtension() {
        return extension;
    }

    String getAssetType() {
        return assetType;
    }


}
