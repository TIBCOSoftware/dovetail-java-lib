// Generated from MapExprGrammar.g4 by ANTLR 4.7.1
package com.tibco.dovetail.core.runtime.expression;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MapExprGrammarParser}.
 */
public interface MapExprGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code condExp}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondExp(MapExprGrammarParser.CondExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condExp}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondExp(MapExprGrammarParser.CondExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisCond(MapExprGrammarParser.ParenthesisCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisCond(MapExprGrammarParser.ParenthesisCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOrCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterAndOrCond(MapExprGrammarParser.AndOrCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOrCond}
	 * labeled alternative in {@link MapExprGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitAndOrCond(MapExprGrammarParser.AndOrCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExp(MapExprGrammarParser.FunctionExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExp(MapExprGrammarParser.FunctionExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void enterNumericAtomExp(MapExprGrammarParser.NumericAtomExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void exitNumericAtomExp(MapExprGrammarParser.NumericAtomExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void enterStringAtomExp(MapExprGrammarParser.StringAtomExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void exitStringAtomExp(MapExprGrammarParser.StringAtomExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void enterVarAtomExp(MapExprGrammarParser.VarAtomExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAtomExp}
	 * labeled alternative in {@link MapExprGrammarParser#atomexpr}.
	 * @param ctx the parse tree
	 */
	void exitVarAtomExp(MapExprGrammarParser.VarAtomExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(MapExprGrammarParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(MapExprGrammarParser.AtomExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExp(MapExprGrammarParser.MulDivModExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExp(MapExprGrammarParser.MulDivModExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExp(MapExprGrammarParser.ParenthesisExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExp(MapExprGrammarParser.ParenthesisExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExp(MapExprGrammarParser.AddSubExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExp}
	 * labeled alternative in {@link MapExprGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExp(MapExprGrammarParser.AddSubExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(MapExprGrammarParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(MapExprGrammarParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MapExprGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MapExprGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#activity}.
	 * @param ctx the parse tree
	 */
	void enterActivity(MapExprGrammarParser.ActivityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#activity}.
	 * @param ctx the parse tree
	 */
	void exitActivity(MapExprGrammarParser.ActivityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#flow}.
	 * @param ctx the parse tree
	 */
	void enterFlow(MapExprGrammarParser.FlowContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#flow}.
	 * @param ctx the parse tree
	 */
	void exitFlow(MapExprGrammarParser.FlowContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#current}.
	 * @param ctx the parse tree
	 */
	void enterCurrent(MapExprGrammarParser.CurrentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#current}.
	 * @param ctx the parse tree
	 */
	void exitCurrent(MapExprGrammarParser.CurrentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapExprGrammarParser#containerService}.
	 * @param ctx the parse tree
	 */
	void enterContainerService(MapExprGrammarParser.ContainerServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapExprGrammarParser#containerService}.
	 * @param ctx the parse tree
	 */
	void exitContainerService(MapExprGrammarParser.ContainerServiceContext ctx);
}