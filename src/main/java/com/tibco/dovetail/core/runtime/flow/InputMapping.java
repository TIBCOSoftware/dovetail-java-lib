package com.tibco.dovetail.core.runtime.flow;


/*****************************
 * mappingValue can be following types:
 * expression mapping - ParseTree, 
 * attribute is an object - map<String, AttributeMapping>
 * attribute is an array - map<String, Object>, keys are "from", "to", "type", and "fields"
 * otherwise, literal or assignment - Object
 */
public class InputMapping implements Mapping{
	private Object mappingValue;
    private ValueMappingType mappingType;
    
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
