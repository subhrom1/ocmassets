package com.cl.ocm.core.dto;

public class OCMAsset {

    public OCMAsset(String assetTitle, String assetLink, boolean isSelected) {
        this.assetTitle = assetTitle;
        this.assetLink = assetLink;
        this.isSelected = isSelected;
    }

    private String assetTitle;

    private String assetLink;

    private boolean isSelected;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
