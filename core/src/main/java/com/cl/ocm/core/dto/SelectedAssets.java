package com.cl.ocm.core.dto;

import java.util.List;

/**
 * The POJO class <code>SelectedAssets</code> represents
 * an asset which is selected for upload. It contains the list of
 * selected assets.
 */
public class SelectedAssets {

    //Path to upload assets in DAM
    private String damUploadPath;

    //List of Assets selected
    private List<OCMAsset> selectedAssets;

    public String getDamUploadPath() {
        return damUploadPath;
    }

    public void setDamUploadPath(String damUploadPath) {
        this.damUploadPath = damUploadPath;
    }

    public List<OCMAsset> getSelectedAssets() {
        return selectedAssets;
    }

    public void setSelectedAssets(List<OCMAsset> selectedAssets) {
        this.selectedAssets = selectedAssets;
    }
}
