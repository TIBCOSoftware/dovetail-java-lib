/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tibco.dovetail.core.model.common.SimpleAttribute;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute extends SimpleAttribute {

    boolean required = true;
    boolean isRef = false;
    boolean isArray = false;
    String minValue = null;
    String maxValue = null;
    String pattern = null;
    String type = null; //java type
    boolean isAsset = false;
	boolean isParticipant = false;
    boolean isReferenceData = false;
    PartyType partyType = PartyType.Participant;
    
    public boolean isAsset() {
		return isAsset;
	}

	public void setIsAsset(boolean isAsset) {
		this.isAsset = isAsset;
	}

	public boolean isParticipant() {
		return isParticipant;
	}

	public void setIsParticipant(boolean isParticipant) {
		this.isParticipant = isParticipant;
	}

	public boolean isReferenceData() {
		return isReferenceData;
	}

	public void setIsReferenceData(boolean isReferenceData) {
		this.isReferenceData = isReferenceData;
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRef() {
        return isRef;
    }

    public void setIsRef(boolean ref) {
        isRef = ref;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean array) {
        isArray = array;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isRequired() {
        return required;
    }
    
    public void setPartyType(String partytype) {
    		if(partytype != null && !partytype.isEmpty())
    			this.partyType = PartyType.valueOf(partytype);
    }
    
    public PartyType getPartyType() {
		return this.partyType;
    }

    public static enum PartyType {
    		Participant, Observer, Notary;
    }
}
