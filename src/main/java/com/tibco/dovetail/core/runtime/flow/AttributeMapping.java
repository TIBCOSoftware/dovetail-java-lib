package com.tibco.dovetail.core.runtime.flow;

public class AttributeMapping extends InputMapping{

	/*****************************
	 * mappingValue can be following types:
	 * expression mapping - ParseTree, 
	 * attribute is an array - map<String, Object>, keys are "from", "to", "type", and "fields"
	 * otherwise, literal or assignment - Object
	 */

    private String name;


    public AttributeMapping(String name){
        this.name = name;
    }

    public String getName() {
    		return name;
    }

}
