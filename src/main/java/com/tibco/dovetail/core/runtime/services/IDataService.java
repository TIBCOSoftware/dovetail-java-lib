/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.services;

import java.util.List;
import java.util.Set;

import com.jayway.jsonpath.DocumentContext;

public interface IDataService <T, R>{

 //   public DocumentContext putState(String assetName, String assetKey, DocumentContext assetValue);
//    public DocumentContext getState(String assetName, String assetKey, DocumentContext keyValue);
//    public DocumentContext deleteState(String assetName, String assetKey, DocumentContext keyValue);
  //  public boolean processPayment(DocumentContext assetValue);
//    public List<DocumentContext> lookupState(String assetName, String assetKey, DocumentContext keyValue);
//    public List<DocumentContext> getHistory(String assetName, String assetKey, DocumentContext keyValue);
    
    public R getState(String assetName, String assetKey, T keyValue);
    public List<R> getStates(String assetName, String assetKey, Set<T> keyValue);
    public R putState(String assetName, String assetKey, R assetValue);
    public R deleteState(String assetName, String assetKey, T keyValue);
    public boolean processPayment(DocumentContext assetValue);
    public List<R> lookupState(String assetName, String assetKey, T keyValue);
    public List<R> getHistory(String assetName, String assetKey, T keyValue);
    public List<DocumentContext> queryState(Object query);
}
