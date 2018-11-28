/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;


public interface Mapping {

	  public void setMappingValue(Object mappingValue) ;
	  public void setMappingType(ValueMappingType mappingType) ;
	  public Object getMappingValue();
	  public ValueMappingType getMappingType() ;

	public static enum ValueMappingType {
        assign(1), literal(2), expression(3), object(4), array(5);

        private int value;
        ValueMappingType(int value){
            this.value = value;
        }

        public static ValueMappingType fromInt(int v){
            switch (v){
                case 1:
                    return assign;
                case 2:
                    return literal;
                case 3:
                    return expression;
                case 4:
                    return object;
                case 5:
                    return array;

                    default:
                        return literal;
            }
        }
    }
}
