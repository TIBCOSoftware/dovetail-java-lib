// Generated from MapExprGrammar.g4 by ANTLR 4.7.1
package com.tibco.dovetail.core.runtime.expression;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MapExprGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MapExprGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code condExp}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondExp(MapExprGrammarParser.CondExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisCond(MapExprGrammarParser.ParenthesisCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrCond(MapExprGrammarParser.AndOrCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExp(MapExprGrammarParser.FunctionExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericAtomExp(MapExprGrammarParser.NumericAtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtomExp(MapExprGrammarParser.StringAtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAtomExp(MapExprGrammarParser.VarAtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExp(MapExprGrammarParser.AtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExp(MapExprGrammarParser.MulDivModExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExp(MapExprGrammarParser.ParenthesisExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExp(MapExprGrammarParser.AddSubExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#funcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncName(MapExprGrammarParser.FuncNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MapExprGrammarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#activity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActivity(MapExprGrammarParser.ActivityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow(MapExprGrammarParser.FlowContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#iteratorKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIteratorKey(MapExprGrammarParser.IteratorKeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#iteratorValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIteratorValue(MapExprGrammarParser.IteratorValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#current}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrent(MapExprGrammarParser.CurrentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MapExprGrammarParser#containerService}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainerService(MapExprGrammarParser.ContainerServiceContext ctx);
}