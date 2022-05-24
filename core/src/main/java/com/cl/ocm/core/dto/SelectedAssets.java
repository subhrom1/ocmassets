package com.cl.ocm.core.dto;

import java.util.List;

/**
 * The POJO class <code>SelectedAssets</code> represents
 * an asset which is selected for upload. It contains the list of
 * selected assets.
 */
public class SelectedAssets {

    //List of Assets selected
    private List<OCMAsset> selectedAssets;

    public List<OCMAsset> getSelectedAssets() {
        return selectedAssets;
    }

    public void setSelectedAssets(List<OCMAsset> selectedAssets) {
        this.selectedAssets = selectedAssets;
    }
}
