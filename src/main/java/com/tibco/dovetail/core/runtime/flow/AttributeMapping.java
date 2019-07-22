/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import com.tibco.dovetail.core.model.composer.HLCResource;

public class AttributeMapping{

	/*****************************
	 * mappingValue can be following types:
	 * expression mapping - ParseTree, 
	 * attribute is an object - map<String, AttributeMapping>
	 * attribute is an array - map<String, Object>, keys are "from", "to", "type", and "fields"
	 * otherwise, literal or assignment - Object
	 */

    private String name;
    private HLCResource metadata = null;
	private Object mappingValue;
    private ValueMappingType mappingType;


    public AttributeMapping(String name){
        this.name = name;
    }

    public String getName() {
    		return name;
    }
    
    public void setComplextObjectMetadata(HLCResource metadata) {
    		this.metadata = metadata;
    }
    
    public HLCResource getComplextObjectMetadata() {
    		return this.metadata;
    }
    
    public void setMappingType(ValueMappingType type) {
		this.mappingType = type;
    }
    
    public void setMappingValue(Object mappingValue) {
    		this.mappingValue = mappingValue;  
    }

    public Object getMappingValue(){

        return this.mappingValue;
    }
    public ValueMappingType getMappingType() {

        return mappingType;
    }
}
