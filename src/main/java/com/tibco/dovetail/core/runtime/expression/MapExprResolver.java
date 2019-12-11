/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.expression;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.internal.JsonContext;
import com.tibco.dovetail.core.runtime.engine.Scope;
import com.tibco.dovetail.core.runtime.util.CompareUtil;
import com.tibco.dovetail.core.runtime.util.JsonUtil;

import net.minidev.json.JSONArray;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExprResolver extends MapExprGrammarBaseVisitor{
	static final Pattern ARRAY_SUFFIX = Pattern.compile("\\[[0-9]+\\]");
    private Scope scope;
    public MapExprResolver(Scope scope){
        this.scope = scope;
    }
    @Override
    public Boolean visitCondExp(MapExprGrammarParser.CondExpContext ctx) {
        List<MapExprGrammarParser.AtomexprContext> contexts = ctx.atomexpr();
        if(contexts.size() == 1){
            return Boolean.valueOf(visit(contexts.get(0)).toString());
        } else {
            Object left = visit(contexts.get(0));
            Object right = visit(contexts.get(1));
            String op = ctx.EQUAL() == null?
                    (ctx.GE()==null?
                            (ctx.GT() == null?
                                    (ctx.LE()==null?
                                            (ctx.LT() == null?
                                                    (ctx.NOTEQUAL().getText())
                                                    : ctx.LT().getText())
                                            :ctx.LE().getText())
                                    : ctx.GT().getText())
                            : ctx.GE().getText())
                    : ctx.EQUAL().getText();

            return CompareUtil.compare(left, right, op);
        }
    }

    @Override
    public Object visitParenthesisCond(MapExprGrammarParser.ParenthesisCondContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitAndOrCond(MapExprGrammarParser.AndOrCondContext ctx) {
        Boolean left = (Boolean)visit(ctx.condition(0));
        Boolean right = (Boolean)visit(ctx.condition(1));

        if(ctx.AND() != null)
            return left && right;
        else
            return left || right;
    }

    @Override
    public Object visitParenthesisExp(MapExprGrammarParser.ParenthesisExpContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitFunctionExp(MapExprGrammarParser.FunctionExpContext ctx){
    		String function = "";
        try {
            function = (String) visitFuncName(ctx.funcName());
            String[] fn = function.split("\\.");
            
            if (fn.length != 2) {
            	//not a function
            		return function;
            }
            
            Class clazz = Class.forName("com.tibco.dovetail.function." + fn[0]);
            Method[] methods = clazz.getDeclaredMethods();

            for(Method method : methods){
                if(method.getName().equals(fn[1])){
                    List<MapExprGrammarParser.AtomexprContext> nodes = ctx.atomexpr();
                    List<Object> args = (List<Object>) nodes.stream().map(it -> visit(it))
                                                    .flatMap(it -> {
                                                        if (it instanceof JSONArray)
                                                            return ((JSONArray) it).stream();
                                                         else if(it instanceof List)
                                                        	 return ((List<Object>)it).stream();
                                                         else
                                                            return Stream.of(it);
                                                    })
                                                    .collect(Collectors.toList());
                    if(method.isVarArgs())
                    		return method.invoke(null, (Object)args.toArray());
                    else if(method.getParameterCount() > 1)
                    		return method.invoke(null, args.toArray());
                    else if (method.getParameterCount() == 1)
                    		return method.invoke(null, args.get(0));
                    else
                    		return method.invoke(null);
                }
            }

            throw new RuntimeException("function " + function + " is not defined");
        }catch (Exception e){
            throw new RuntimeException("error while processing function " + function, e);
        }
    }

    @Override
    public Object visitStringAtomExp(MapExprGrammarParser.StringAtomExpContext ctx) {
        String s = ctx.STRING().getText();
        return s.substring(1, s.length()-1);
    }

    @Override
    public Object visitMulDivModExp(MapExprGrammarParser.MulDivModExpContext ctx) {
        Object left = visit(ctx.expression(0));
        Object right = visit(ctx.expression(1));
        if(ctx.ASTERISK() != null){
            if((left instanceof Integer || left instanceof Long) && (right instanceof Integer || right instanceof Long) )
            		return (Long.valueOf(left.toString()) * Long.valueOf(right.toString()));
            else {
            		BigDecimal bdl = new BigDecimal(left.toString());
            		BigDecimal bdr = new BigDecimal(right.toString());
            		return bdl.multiply(bdr).toPlainString();
            }
            
        }
        else if(ctx.SLASH() != null){
	        	BigDecimal bdl = new BigDecimal(left.toString());
	    		BigDecimal bdr = new BigDecimal(right.toString());
	    		return bdl.divide(bdr).toPlainString();
        }
        else if(ctx.MOD() != null){
        		if((left instanceof Integer || left instanceof Long) && (right instanceof Integer || right instanceof Long) )
        			return (Long.valueOf(left.toString()) % Long.valueOf(right.toString()));
        		else {
        			BigDecimal bdl = new BigDecimal(left.toString());
            		BigDecimal bdr = new BigDecimal(right.toString());
            		return bdl.remainder(bdr).toPlainString();
        		}
        }
        else
            return null;
    }

    @Override
    public Object visitNumericAtomExp(MapExprGrammarParser.NumericAtomExpContext ctx) {
        String value = ctx.NUMBER().getText();
        if(value.contains("."))
            return Double.valueOf(value);
        else {
            Long l = Long.valueOf(value);
            if (l <= Integer.MAX_VALUE && l >= Integer.MIN_VALUE)
                return Integer.valueOf(value);
            else return l;
        }
    }

    @Override
    public Object visitAddSubExp(MapExprGrammarParser.AddSubExpContext ctx) {
        Object left = visit(ctx.expression(0));
        Object right = visit(ctx.expression(1));
        if(ctx.PLUS() != null){
            if(left instanceof Integer && right instanceof Integer)
                return ((Integer)left + (Integer) right);
            if(left instanceof Double || right instanceof Double)
                return ((Double)left + (Double) right);
            else
                return (Long.valueOf(left.toString()) + Long.valueOf(right.toString()));
        }
        else if(ctx.MINUS() != null){
            if(left instanceof Integer && right instanceof Integer)
                return ((Integer)left - (Integer) right);
            if(left instanceof Double || right instanceof Double)
                return ((Double)left - (Double) right);
            else
                return (Long.valueOf(left.toString()) - Long.valueOf(right.toString()));
        }
        else
            return null;
    }

    @Override
    public Object visitFuncName(MapExprGrammarParser.FuncNameContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitActivity(MapExprGrammarParser.ActivityContext ctx) {
        List<TerminalNode> names = ctx.NAME();

        String var = names.get(1).getText().trim();
        int element = -1;
        if(var.endsWith("]")) {
	    		int idx = var.indexOf("[");
	    		element = Integer.parseInt(var.substring(idx+1, var.length()-1));
	    		var = var.substring(0, idx);
        }
        Object value = scope.getVariable("$activity", names.get(0).getText() + "." + var);
        if(element >= 0)
        		value = readElementValue(value, element);
        
        if(names.size() > 2){
            String path = names.subList(2, names.size()).stream().map(it-> it.getText()).collect(Collectors.joining("."));
            value = readValue(value, path);
        }

    		return value;
    }

    private Object readElementValue(Object v, int idx) {
    		
    		List values;
    		if(v instanceof List)
    			values = (List)v;
    		else if(v instanceof JsonContext)
    			values = (List)((JsonContext)v).json();
    		else
    			throw new RuntimeException("value is not a list: " + v.toString());
    		
    		return values.get(idx);
    }
    private Object readValue(Object v, String path){
    		DocumentContext doc = null;
    	    try {
	        if(v instanceof DocumentContext)
	        		doc = (DocumentContext) v;
	        else if(v instanceof LinkedHashMap)
	        		doc = JsonUtil.getJsonParser().parse((LinkedHashMap)v);
	        
	        if(doc.json() instanceof JSONArray || doc.json() instanceof List)
	            path = "$.." + path;
	        else
	            path = "$." + path;
	        List<Object> list= doc.read(path);
	        Object value = null;
	        if(list.size() == 1)
	            value = list.get(0);
	        else
	            value = list;
	
	        return value;
    	    }catch (Exception e) {
    	    		throw new RuntimeException("error while readValue of " + path + " from " + doc.jsonString(), e);
    	    }
    }

    @Override
    public Object visitFlow(MapExprGrammarParser.FlowContext ctx) {
        List<TerminalNode> names = ctx.NAME();

        String var = names.get(0).getText().trim();
        try {
	        int element = -1;
	        
	        if(var.endsWith("]")) {
	        		int idx = var.indexOf("[");
	        		element = Integer.parseInt(var.substring(idx+1, var.length()-1));
	        		var = var.substring(0, idx);
	        }
	        Object value = scope.getVariable(Scope.SCOPE_FLOW, var);
	        
	        if(names.size() > 1){
	            String path = names.subList(1, names.size()).stream().map(it-> it.getText()).collect(Collectors.joining("."));
	            value = readValue(value, path);
	        }
	 
	        if(element >= 0 && value instanceof List)
	        		return ((List)value).get(element);
	        	else
	        		return value;
        }catch(Exception e) {
        		throw new RuntimeException("error in visitFlow, var=" + var, e);
        }
    }

    @Override 
    public Object visitCurrent(MapExprGrammarParser.CurrentContext ctx) { 
    	  	List<TerminalNode> names = ctx.NAME();
    	  	String path = null; 
    	  	Object from  = null;
    	  	
    	  	if(scope.isRootScope()) {
    	  		from = scope.getVariable(Scope.SCOPE_CURRENT, names.get(0).getText());
    	  		path = names.subList(1, names.size()).stream().map(n -> n.getText()).collect(Collectors.joining("."));
    	  	}
    	  	else {
    	  		from = scope.getVariable(Scope.SCOPE_CURRENT, "from");
    	  		path = names.stream().map(n -> n.getText()).collect(Collectors.joining("."));
    	  	}
    	  	
    	  	if(from == null || path == null || path.isEmpty()) 
    	  		return from;
    	  
    	  	return readValue(from, path);
    	  	
    	}
    
    @Override public Integer visitIteratorKey(MapExprGrammarParser.IteratorKeyContext ctx) { 
    		return (int) scope.getVariable("$iteration", "key"); 
    	}
	
	@Override public Object visitIteratorValue(MapExprGrammarParser.IteratorValueContext ctx) { 
		Object value = scope.getVariable("$iteration", "value");
		if (value != null ) {
			List<TerminalNode> names = ctx.NAME();
    	  		String path = names.stream().map(n -> n.getText()).collect(Collectors.joining("."));
    	  		return readValue(value, path);
		} else 
			return null;
	}
	
	@Override public Object visitProperty(MapExprGrammarParser.PropertyContext ctx) {
		String p = ctx.NAME().stream().map(it -> it.getText()).collect(Collectors.joining("."));
		return scope.getVariable(Scope.SCOPE_PROPERTY, p); 
	}
	
}