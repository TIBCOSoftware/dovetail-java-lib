/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import org.antlr.v4.runtime.tree.ParseTree;

import com.tibco.dovetail.core.model.composer.HLCResource;

public class AttributeMapping{

	/*****************************
	 * mappingValue can be following types:
	 * expression mapping - ParseTree, 
	 * attribute is an object/array - List<AttributeMapping>
	 * otherwise, literal or assignment - Object
	 */

    private String name; 
    private String dataType;
	private HLCResource metadata = null;
	private Object mappingValue;
    private ValueMappingType mappingType;
    private ParseTree from;

    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public ParseTree getFrom() {
		return from;
	}

	public void setFrom(ParseTree from) {
		this.from = from;
	}

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
