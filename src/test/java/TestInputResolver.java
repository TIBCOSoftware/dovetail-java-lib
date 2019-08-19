/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.tibco.dovetail.core.runtime.compilers.CompilerHelper;
import com.tibco.dovetail.core.runtime.engine.ContextImpl;
import com.tibco.dovetail.core.runtime.engine.InputResolver;
import com.tibco.dovetail.core.runtime.engine.Scope;
import com.tibco.dovetail.core.runtime.flow.AttributeMapping;
import com.tibco.dovetail.core.runtime.flow.ValueMappingType;
import com.tibco.dovetail.core.runtime.util.JsonUtil;
import com.tibco.dovetail.core.runtime.util.ModelUtil;


public class TestInputResolver {

	@Test
	public void testPattern() {
		String pattern1 = "(\\$INPUT.\\$\\$)?(\\['(?<name>\\w+)'\\])+?";
		
		String in = "$INPUT['data']['amt']['curre1cy_a']['D']";
		String in1 = "$INPUT.$$['data']";
		String pattern2 = "(\\$INPUT)?(\\['(?<name>\\w+)'\\])+?";
		
		System.out.println("pattern2");
		Pattern cp = Pattern.compile(pattern2);
		Matcher matcher = cp.matcher(in1);
		System.out.println(matcher.groupCount());
		while(matcher.find()) {
			System.out.println(matcher.group("name"));
		}
		
		System.out.println(in);
		Pattern cp1 = Pattern.compile(pattern1);
		Matcher matcher1 = cp1.matcher(in);
		System.out.println(matcher1.groupCount());
		while(matcher1.find()) {
			System.out.println(matcher1.group("name"));
		}
		
		System.out.println(in1);
		Matcher matcher2 = cp1.matcher(in1);
		while(matcher2.find()) {
			System.out.println(matcher2.group("name"));
		}
		
		String path = Arrays.asList("a").subList(1, 1).stream().collect(Collectors.joining("."));
		
		System.out.println("path='" + path + "'");
	}
	
	
	@Test
	public void namespace() {
		System.out.println("com.example.iou.IOU".lastIndexOf("."));
		System.out.println(ModelUtil.getNameSpace("com.example.iou.IOU"));
	}
	
	@Test
	public void testObjectParser() {
		Assert.assertNotNull(parseMapping());
	}
	
	
	private AttributeMapping parseMapping(){
		String json="{\n" + 
				"                        \"ref\": \"github.com/project-flogo/flow\",\n" + 
				"                        \"settings\": {\n" + 
				"                            \"flowURI\": \"res://flow:settleIOU\"\n" + 
				"                        },\n" + 
				"                        \"input\": {\n" + 
				"                            \"transactionInput\": {\n" + 
				"                                \"mapping\": {\n" + 
				"                                    \"iou\": \"=$.transactionInput.iou\",\n" + 
				"                                    \"funds\": {\n" + 
				"                                        \"@foreach($.transactionInput.funds)\": {\n" + 
				"                                            \"assetId\": \"=$.assetId\",\n" + 
				"                                            \"amt\": \"=$.amt\",\n" + 
				"                                            \"issuer\": \"=$.issuer\",\n" + 
				"                                            \"issuerRef\": \"=$.issuerRef\",\n" + 
				"                                            \"owner\": \"=$.owner\"\n" + 
				"                                        }\n" + 
				"                                    },\n" + 
				"                                    \"sendChangeTo\": \"=$.transactionInput.sendPaymentTo\",\n" + 
				"                                    \"sendPaymentTo\": \"=$.transactionInput.sendChangeTo\",\n" + 
				"                                    \"transactionId\": \"=$.transactionInput.transactionId\",\n" + 
				"                                    \"timestamp\": \"=$.transactionInput.timestamp\",\n" + 
				"                                    \"payAmt\": {\n" + 
				"                                        \"currency\": \"=$.transactionInput.payAmt.currency\",\n" + 
				"                                        \"quantity\": 0\n" + 
				"                                    }\n" + 
				"                                }\n" + 
				"                            }\n" + 
				"                        }}";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> data = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
			Map<String, Object> input = (Map<String, Object>)data.get("input");
			
			AttributeMapping mapping = CompilerHelper.parseObjectAttrs("transactionInput", (Map<String, Object>)input.get("transactionInput"));
			Assert.assertEquals(mapping.getMappingType(), ValueMappingType.object);
			Assert.assertTrue(mapping.getMappingValue() instanceof List);
			List<AttributeMapping> value = (List<AttributeMapping>)mapping.getMappingValue();
			Assert.assertEquals(value.size(), 7);
			
			for(AttributeMapping m : value) {
				if(m.getName() == "funds")
					Assert.assertEquals(m.getMappingType(),  ValueMappingType.array);
				else if (m.getName() == "payAmt")
					Assert.assertEquals(m.getMappingType(),  ValueMappingType.object);
				else if (m.getName() == "sendChangeTo")
					Assert.assertEquals(m.getMappingType(),  ValueMappingType.expression);
			}
		    return mapping; 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}
	@Test
	public void testInputResolver() {
		ContextImpl context = new ContextImpl();
		Scope scope = new Scope();
		
		String txnIn = "{\n" + 
				"  \"iou\":{\n" + 
				"    \"owner\": \"partyOwner\",\n" + 
				"    \"issuer\": \"partyIssuer\",\n" + 
				"    \"amt\":{\n" + 
				"      \"currency\": \"USD\",\n" + 
				"      \"quantity\": 10000\n" + 
				"    }\n" + 
				"  },\n" + 
				"  \"funds\":[\n" + 
				"    {\n" + 
				"    \"assetId\": \"cash1\",\n" + 
				"    \"amt\":{\n" + 
				"      \"currency\": \"USD\",\n" + 
				"      \"quantity\": 50000\n" + 
				"    },\n" + 
				"    \"issuer\": \"bank\",\n" + 
				"    \"issuerRef\": \"123\",\n" + 
				"    \"owner\": \"partyIssuer\"\n" + 
				"  },\n" + 
				"  {\n" + 
				"    \"assetId\": \"cash2\",\n" + 
				"    \"amt\":{\n" + 
				"      \"currency\": \"USD\",\n" + 
				"      \"quantity\": 50000\n" + 
				"    },\n" + 
				"    \"issuer\": \"bank\",\n" + 
				"    \"issuerRef\": \"321\",\n" + 
				"    \"owner\": \"partyIssuer\"\n" + 
				"  }\n" + 
				"    ],\n" + 
				"  \"sendPaymentTo\": \"partyOwner\",\n" + 
				"  \"sendChangeTo\": \"partyIssuer\",\n" + 
				"   \"payAmt\":{\n" + 
				"      \"currency\": \"USD\",\n" + 
				"      \"quantity\": 10000\n" + 
				"    },\n" + 
				"    \"transactionId\": \"txn1\",\n" + 
				"    \"timestamp\":\"time\"\n" + 
				"}";
		
		scope.addVariable(Scope.SCOPE_CURRENT, "transactionInput", JsonUtil.getJsonParser().parse(txnIn));
		
		AttributeMapping mapping = parseMapping();
		InputResolver.resolveInput(context, scope, "transactionInput", mapping);
		
		Object value = context.getInput("transactionInput");
		Assert.assertTrue(value instanceof DocumentContext);
		DocumentContext doc = (DocumentContext)value;
		System.out.println(doc.jsonString());
		
		Assert.assertEquals(doc.jsonString(), "{\"iou\":{\"owner\":\"partyOwner\",\"issuer\":\"partyIssuer\",\"amt\":{\"currency\":\"USD\",\"quantity\":10000}},\"funds\":[{\"assetId\":\"cash1\",\"amt\":{\"currency\":\"USD\",\"quantity\":50000},\"issuer\":\"bank\",\"issuerRef\":\"123\",\"owner\":\"partyIssuer\"},{\"assetId\":\"cash2\",\"amt\":{\"currency\":\"USD\",\"quantity\":50000},\"issuer\":\"bank\",\"issuerRef\":\"321\",\"owner\":\"partyIssuer\"}],\"sendChangeTo\":\"partyOwner\",\"sendPaymentTo\":\"partyIssuer\",\"transactionId\":\"txn1\",\"timestamp\":\"time\",\"payAmt\":{\"currency\":\"USD\",\"quantity\":0}}");
	}
}