package com.cl.ocm.core.dto;

/**
 * This POJO encapsulates the OCM Asset fetched from the OCM Channel
 * for AEM. It contains list of all properties fetched for the OCM
 * asset, e.g. asset title, url, etc.
 */
public class OCMAsset {

    //Title of the asset
    private String assetTitle;

    //Asset Link/URL
    private String assetLink;

    //Asset Type (Document, Image, etc)
    private String assetType;

    //Asset thumbnail url
    private String thumbnailUrl;

    public OCMAsset(String assetTitle, String assetLink) {
        this.assetTitle = assetTitle;
        this.assetLink = assetLink;
    }

    public OCMAsset(String assetTitle, String assetType,  String assetLink,String thumbnailUrl) {
        this.assetTitle = assetTitle;
        this.assetType = assetType;
        this.assetLink = assetLink;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAssetTitle() {
        return assetTitle;
    }

    public void setAssetTitle(String assetTitle) {
        this.assetTitle = assetTitle;
    }

    public String getAssetLink() {
        return assetLink;
    }

    public void setAssetLink(String assetLink) {
        this.assetLink = assetLink;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
