/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.services;

import java.util.List;

import com.jayway.jsonpath.DocumentContext;

public interface IDataService {

    public DocumentContext putState(String assetName, String assetKey, DocumentContext assetValue);
    public DocumentContext getState(String assetName, String assetKey, DocumentContext keyValue);
    public DocumentContext deleteState(String assetName, String assetKey, DocumentContext keyValue);
    public List<DocumentContext> lookupState(String assetName, String assetKey, DocumentContext keyValue);
    public List<DocumentContext> getHistory(String assetName, String assetKey, DocumentContext keyValue);
    public List<DocumentContext> queryState(Object query);
}
