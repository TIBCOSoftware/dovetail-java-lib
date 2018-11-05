package com.tibco.dovetail.core.runtime.transaction;

import com.tibco.dovetail.core.model.common.SimpleAttribute;

public class TxnInputAttribute extends SimpleAttribute {
	private boolean isAssetRef;
	private boolean isArray;       
	private String assetName;     
	private String identifiers;   
	private boolean isParticipant;
	public String getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(String identifiers) {
		this.identifiers = identifiers;
	}
	public boolean isAssetRef() {
		return isAssetRef;
	}
	public void setAssetRef(boolean isAssetRef) {
		this.isAssetRef = isAssetRef;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	public boolean isParticipant() {
		return isParticipant;
	}
	public void setParticipant(boolean isParticipant) {
		this.isParticipant = isParticipant;
	} 
}
