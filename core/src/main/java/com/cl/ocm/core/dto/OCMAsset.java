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

    public OCMAsset(String assetTitle, String assetLink) {
        this.assetTitle = assetTitle;
        this.assetLink = assetLink;
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
}
