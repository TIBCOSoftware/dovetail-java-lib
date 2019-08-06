/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import com.tibco.dovetail.core.runtime.expression.MapExprGrammarLexer;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Link {

    private String condition;
    transient private ParseTree conditionObject;

    private LinkMapType type;
    private Node nextNode;

    public LinkMapType getType() {
        return type;
    }

    public ParseTree getConditionObject() {
        return conditionObject;
    }

    public Link(LinkMapType type, String condition){
        this.type = type;
        this.condition = condition;
        if(type == Link.LinkMapType.expression)
            conditionObject = parseConditionExpression(condition);

    }

    public void setNextNode(Node n){
        this.nextNode = n;
    }
    public String getCondition() {
        return condition;
    }
    public Node getNextNode() {
        return nextNode;
    }

    private ParseTree parseConditionExpression(String mapping) {
        try {
            InputStream stream = new ByteArrayInputStream(mapping.getBytes(StandardCharsets.UTF_8));

            MapExprGrammarLexer lexer = new MapExprGrammarLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            MapExprGrammarParser parser = new MapExprGrammarParser(tokens);
           
            stream.close();
            return parser.condition();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static enum LinkMapType {
        normal(0), expression(1), label(2), error(3);

        private int value;

        LinkMapType(int type){
            this.value = type;
        }

        public static LinkMapType fromInt(int v){
            switch (v){
                case 0:
                    return normal;
                case 1:
                    return expression;
                case 2:
                    return label;
                case 3:
                    return error;

                default:
                		return normal;

            }
        }
    }

}