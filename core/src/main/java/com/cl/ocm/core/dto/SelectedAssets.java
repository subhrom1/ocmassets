package com.cl.ocm.core.dto;

import java.util.List;

/**
 * The POJO class <code>SelectedAssets</code> represents
 * an asset which is selected for upload. It contains the list of
 * properties for selected asset.
 */
public class SelectedAssets {

    //List of Asset Links selected
    private List<String> links;

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
