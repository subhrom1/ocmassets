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
    DOC("doc", "application/msword"),
    DOCX("docx","application/vnd.openxmlformats-officedocument.wordprocessing"),
    PDF("pdf","application/pdf"),
    PPT("ppt","application/vnd.ms-powerpoint"),
    PPTX("pptx","application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    PNG("png", "image/png"),
    MP4( "mp4", "video/mp4"),
    MOV("mov", "video/quicktime"),
    GP("3gp", "video/3gpp"),
    AVI("avi", "video/x-msvideo"),
    FLV("flv", "video/x-flv"),
    MPEG("mpeg", "video/mpeg"),
    MPG("mpg", "video/mpeg"),
    WMV("wmv", "video/x-ms-wmv"),
    TIFF("tiff","image/tiff"),
    XLS("xls","application/vnd.ms-excel"),
    XLSX("xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    VOB("vob", "image/png");





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
