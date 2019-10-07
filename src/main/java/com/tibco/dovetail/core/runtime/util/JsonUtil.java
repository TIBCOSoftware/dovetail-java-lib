/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.util;

import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

public class JsonUtil {
  
   static {
	    ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
		mapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
		mapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		//mapper.registerModule(new JavaTimeModule());
		
	   Configuration.setDefaults(new Configuration.Defaults() {
		
        private final JsonProvider jsonProvider = new JacksonJsonProvider(mapper);
        private final MappingProvider mappingProvider = new JacksonMappingProvider(mapper);
          
        @Override
        public JsonProvider jsonProvider() {
            return jsonProvider;
        }

        @Override
        public MappingProvider mappingProvider() {
            return mappingProvider;
        }
        
        @Override
        public Set<Option> options() {
            return EnumSet.noneOf(Option.class);
        }
    });
   }
   
   static Configuration jsonconfig = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
			.addOptions(Option.ALWAYS_RETURN_LIST);

    public static ParseContext getJsonParser (){
        return JsonPath.using(jsonconfig);
    }

}
