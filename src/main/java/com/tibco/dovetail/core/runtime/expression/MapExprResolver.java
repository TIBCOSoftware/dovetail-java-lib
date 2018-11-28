/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.expression;

import com.jayway.jsonpath.DocumentContext;
import com.tibco.dovetail.core.runtime.engine.Scope;
import com.tibco.dovetail.core.runtime.function.string;
import com.tibco.dovetail.core.runtime.util.CompareUtil;
import com.tibco.dovetail.core.runtime.util.JsonUtil;

import net.minidev.json.JSONArray;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
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
        try {
            String function = (String) visitFuncName(ctx.funcName());
            String[] fn = function.split("\\.");
            
            if (fn.length != 2) {
            	//not a function
            		return function;
            }
            
            Class clazz = Class.forName("com.tibco.dovetail.core.runtime.function." + fn[0]);
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
                    else
                    		return method.invoke(null, args.toArray());
                }
            }

            throw new RuntimeException("function " + function + " is not defined");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object visitStringAtomExp(MapExprGrammarParser.StringAtomExpContext ctx) {
        return ctx.STRING().getText();
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

        Object value = scope.getVariable("$activity", names.get(0).getText() + "." + names.get(1).getText());

        if(names.size() > 2){
            String path = names.subList(2, names.size()).stream().map(it-> it.getText()).collect(Collectors.joining("."));
            value = readValue(value, path);
        }

        return value;
    }

    private Object readValue(Object v, String path){
        DocumentContext doc = null;
        if(v instanceof DocumentContext)
        		doc = (DocumentContext) v;
        else if(v instanceof LinkedHashMap)
        		doc = JsonUtil.getJsonParser().parse((LinkedHashMap)v);
        
        if(doc.json() instanceof JSONArray)
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
    }

    @Override
    public Object visitFlow(MapExprGrammarParser.FlowContext ctx) {
        List<TerminalNode> names = ctx.NAME();

        String var = names.get(0).getText().trim();
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

        if(element >= 0 && value instanceof JSONArray)
        		return ((JSONArray)value).get(element);
        	else
        		return value;
    }

    @Override 
    public Object visitCurrent(MapExprGrammarParser.CurrentContext ctx) { 
    	  	List<TerminalNode> names = ctx.NAME();
    	  	String path = names.stream().map(n -> n.getText()).collect(Collectors.joining("."));
    	  	Object from = scope.getVariable(Scope.SCOPE_LOCAL, "from");
    	  	if(from != null)
    	  		return readValue(from, path);
    	  	else
    	  		return null;
    	}
    
}


