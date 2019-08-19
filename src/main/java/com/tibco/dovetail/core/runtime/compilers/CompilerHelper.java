package com.tibco.dovetail.core.runtime.compilers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarLexer;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarParser;
import com.tibco.dovetail.core.runtime.flow.AttributeMapping;
import com.tibco.dovetail.core.runtime.flow.ValueMappingType;

public class CompilerHelper {
	 public static ParseTree parseExpression(String mapping) {
		InputStream stream = null;

	    try {//System.out.println("mapping=" + mapping);
	        stream = new ByteArrayInputStream(mapping.getBytes(StandardCharsets.UTF_8));
	        MapExprGrammarLexer lexer = new MapExprGrammarLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        MapExprGrammarParser parser = new MapExprGrammarParser(tokens);
	      
	        ParseTree tree = parser.expression();
	       
	        return tree;
	    }catch (Exception e){
	        throw new RuntimeException("parseExpression error: " + mapping, e);
	    } finally {
	    		if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {}
	    		}
	    }
	}
	
	 public static AttributeMapping parseObjectAttrs(String attr, Map<String, Object> mappingValue) {
	 		AttributeMapping map = new AttributeMapping(attr);
	 		List<AttributeMapping> attrmaps = new ArrayList<AttributeMapping>();
	 		map.setMappingValue(attrmaps);
	 		map.setMappingType(ValueMappingType.object);
	 		
	 		
	 		if(mappingValue.size() == 1) {
				for(String ak: mappingValue.keySet()) {
					if(ak.startsWith("@foreach")) {
						map.setMappingType(ValueMappingType.array);
						map.setFrom(CompilerHelper.parseExpression(ak.substring(9, ak.length()-1)));
						mappingValue = (Map<String, Object>) mappingValue.get(ak);
					} else if(ak.equals("mapping")) {
						mappingValue = (Map<String, Object>) mappingValue.get(ak);
						if(mappingValue.size() == 1) {
							for(String k: mappingValue.keySet()) {
								if(k.startsWith("@foreach")) { // in case the top level object is array
									map.setMappingType(ValueMappingType.array);
									map.setFrom(CompilerHelper.parseExpression(k.substring(9, k.length()-1)));
									mappingValue = (Map<String, Object>) mappingValue.get(k);
								}
							}
						}
					}
				};
	 		}
				
		    mappingValue.forEach((ak, av) -> {
		    		if(av instanceof Map) {
		    			attrmaps.add(parseObjectAttrs(ak, (Map<String, Object>)av));
		    		} else {
		    			attrmaps.add(setDirectMapping(ak, av));
		    		}
		    });
			
	 		return map;
	 }
	
	
	public static boolean isExpression(String mapping) {
 		return mapping.startsWith("=");
	}
 
	public static AttributeMapping setDirectMapping( String attr, Object mappingValue) {
 		AttributeMapping inputMapping = new AttributeMapping(attr);
 		String mapexpr = mappingValue.toString();
		if(isExpression(mapexpr)) {
			inputMapping.setMappingType(ValueMappingType.expression);
			inputMapping.setMappingValue(parseExpression(mapexpr.substring(1)));
		} else {
			inputMapping.setMappingType(ValueMappingType.literal);
			inputMapping.setMappingValue(mappingValue);
		}
		return inputMapping;
	}

}
