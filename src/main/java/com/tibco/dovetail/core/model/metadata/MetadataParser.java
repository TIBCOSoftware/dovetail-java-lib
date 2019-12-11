package com.tibco.dovetail.core.model.metadata;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MetadataParser {
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Schema {
		//only care about meta data which is stored in description field
		private  String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	 }
	 
	 public static ResourceDescriptor parseSingleSchema(String schema) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Schema metaSchema = mapper.readValue(schema, Schema.class);
		if(metaSchema.getDescription() != null) {
			ResourceDescriptor hlcResource = mapper.readValue(metaSchema.getDescription(), ResourceDescriptor.class );
			
			return hlcResource;
		} else {
			return null;
		}
	}
}
