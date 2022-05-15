package com.cl.ocm.core.services;

import com.cl.ocm.core.dto.OCMAsset;

import java.util.List;

public interface OCMAssetFetchService {

    public List<OCMAsset> fetchOCMAssetLinks();

    public List<OCMAsset> fetchOCMAssetLinks(int offset, int limit);
}
