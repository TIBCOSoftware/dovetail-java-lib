import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.tibco.dovetail.core.runtime.expression.ErrorListener;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarLexer;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarParser;
import com.tibco.dovetail.core.runtime.expression.MapExprResolver;

public class TestMapResolver {
	@Test
	public void test_array_create() {
		
		String f1 = "array.create(\"a\", \"b\", \"c\")";
		
		ParseTree tree = parseExpr(f1);
		
		 MapExprResolver visitor = new MapExprResolver(null);
		 Object value = visitor.visit(tree);
		 
		 Assert.assertEquals(Arrays.asList("a", "b", "c"), value);
		 
		 String f2 = "array.create(1, 2, 3)";
			
			ParseTree tree2 = parseExpr(f2);
			
			 MapExprResolver visito2r = new MapExprResolver(null);
			 Object value2 = visitor.visit(tree2);
			 
			 Assert.assertEquals("[1, 2, 3]", value2.toString());
		
	}
	
	@Test
	public void test_string() {
		
		String f1 = "string.concat(\"a\", string.tostring(2))";
		
		ParseTree tree = parseExpr(f1);
		
		 MapExprResolver visitor = new MapExprResolver(null);
		 Object value = visitor.visit(tree);
		 
		 Assert.assertEquals("a2", value.toString());
		
	}
	
	private ParseTree parseExpr(String s) {
		InputStream stream = null;

	    try {
	        stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
	        MapExprGrammarLexer lexer = new MapExprGrammarLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
	        lexer.removeErrorListeners();
	        lexer.addErrorListener(new ErrorListener());
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        MapExprGrammarParser parser = new MapExprGrammarParser(tokens);
	      
	        ParseTree tree = parser.expression();
	       
	        return tree;
	    }catch (Exception e){
	        throw new RuntimeException("parseExpression error: " + s, e);
	    } finally {
	    		if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {}
	    		}
	    }
	}
}
