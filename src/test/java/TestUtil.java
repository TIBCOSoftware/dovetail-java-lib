import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.runtime.flow.AttributeMapping;

public class TestUtil {

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
		
	}
	
	@Test
	public void testArrayMapping() {
		ObjectMapper mapper = new ObjectMapper();
		String mappingValue = "{\"to\":\"$INPUT['input']\",\"from\":\"$flow.transactionInput.payments\",\"type\":\"foreach\",\"fields\":[{\"to\":\"$INPUT.$$['data']\",\"from\":\"$.amt.quantity\",\"type\":\"primitive\"}]}";
    
		 try {
			Map<String, Object> arraymap = mapper.readValue(mappingValue.toString(), new TypeReference<Map<String, Object>>(){} );
			
			System.out.println("arraymap=" + arraymap);
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 		
	}
}