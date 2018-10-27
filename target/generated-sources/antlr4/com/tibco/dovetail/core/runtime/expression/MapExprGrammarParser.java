// Generated from MapExprGrammar.g4 by ANTLR 4.7.1
package com.tibco.dovetail.core.runtime.expression;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MapExprGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AND=12, OR=13, EQUAL=14, NOTEQUAL=15, GT=16, LT=17, 
		GE=18, LE=19, ASTERISK=20, SLASH=21, MOD=22, PLUS=23, MINUS=24, NAME=25, 
		NUMBER=26, STRING=27, WHITESPACE=28;
	public static final int
		RULE_condition = 0, RULE_atomexpr = 1, RULE_expression = 2, RULE_funcName = 3, 
		RULE_variable = 4, RULE_activity = 5, RULE_flow = 6, RULE_current = 7, 
		RULE_containerService = 8;
	public static final String[] ruleNames = {
		"condition", "atomexpr", "expression", "funcName", "variable", "activity", 
		"flow", "current", "containerService"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'.'", "'{{'", "'$activity['", "'].'", "'}}'", 
		"'$flow.transactionInput.'", "'$.'", "'$flow.containerServiceStub'", null, 
		null, "'=='", null, "'>'", "'<'", "'>='", "'<='", "'*'", "'/'", "'%'", 
		"'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"AND", "OR", "EQUAL", "NOTEQUAL", "GT", "LT", "GE", "LE", "ASTERISK", 
		"SLASH", "MOD", "PLUS", "MINUS", "NAME", "NUMBER", "STRING", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MapExprGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MapExprGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondExpContext extends ConditionContext {
		public List<AtomexprContext> atomexpr() {
			return getRuleContexts(AtomexprContext.class);
		}
		public AtomexprContext atomexpr(int i) {
			return getRuleContext(AtomexprContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(MapExprGrammarParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(MapExprGrammarParser.NOTEQUAL, 0); }
		public TerminalNode GT() { return getToken(MapExprGrammarParser.GT, 0); }
		public TerminalNode LT() { return getToken(MapExprGrammarParser.LT, 0); }
		public TerminalNode GE() { return getToken(MapExprGrammarParser.GE, 0); }
		public TerminalNode LE() { return getToken(MapExprGrammarParser.LE, 0); }
		public CondExpContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterCondExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitCondExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitCondExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisCondContext extends ConditionContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ParenthesisCondContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterParenthesisCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitParenthesisCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitParenthesisCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndOrCondContext extends ConditionContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode AND() { return getToken(MapExprGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(MapExprGrammarParser.OR, 0); }
		public AndOrCondContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterAndOrCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitAndOrCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitAndOrCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ParenthesisCondContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(19);
				match(T__0);
				setState(20);
				condition(0);
				setState(21);
				match(T__1);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case NAME:
			case NUMBER:
			case STRING:
				{
				_localctx = new CondExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(23);
				atomexpr();
				setState(26);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(24);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOTEQUAL) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(25);
					atomexpr();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(35);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndOrCondContext(new ConditionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_condition);
					setState(30);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(31);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(32);
					condition(3);
					}
					} 
				}
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomexprContext extends ParserRuleContext {
		public AtomexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomexpr; }
	 
		public AtomexprContext() { }
		public void copyFrom(AtomexprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarAtomExpContext extends AtomexprContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VarAtomExpContext(AtomexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterVarAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitVarAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitVarAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericAtomExpContext extends AtomexprContext {
		public TerminalNode NUMBER() { return getToken(MapExprGrammarParser.NUMBER, 0); }
		public NumericAtomExpContext(AtomexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterNumericAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitNumericAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitNumericAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringAtomExpContext extends AtomexprContext {
		public TerminalNode STRING() { return getToken(MapExprGrammarParser.STRING, 0); }
		public StringAtomExpContext(AtomexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterStringAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitStringAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitStringAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExpContext extends AtomexprContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public List<AtomexprContext> atomexpr() {
			return getRuleContexts(AtomexprContext.class);
		}
		public AtomexprContext atomexpr(int i) {
			return getRuleContext(AtomexprContext.class,i);
		}
		public FunctionExpContext(AtomexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterFunctionExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitFunctionExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitFunctionExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomexprContext atomexpr() throws RecognitionException {
		AtomexprContext _localctx = new AtomexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_atomexpr);
		int _la;
		try {
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				_localctx = new FunctionExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				funcName();
				setState(39);
				match(T__0);
				setState(40);
				atomexpr();
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(41);
					match(T__2);
					setState(42);
					atomexpr();
					}
					}
					setState(47);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(48);
				match(T__1);
				}
				break;
			case NUMBER:
				_localctx = new NumericAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StringAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				match(STRING);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
				_localctx = new VarAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(52);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomExpContext extends ExpressionContext {
		public AtomexprContext atomexpr() {
			return getRuleContext(AtomexprContext.class,0);
		}
		public AtomExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASTERISK() { return getToken(MapExprGrammarParser.ASTERISK, 0); }
		public TerminalNode SLASH() { return getToken(MapExprGrammarParser.SLASH, 0); }
		public TerminalNode MOD() { return getToken(MapExprGrammarParser.MOD, 0); }
		public MulDivModExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterMulDivModExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitMulDivModExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitMulDivModExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisExpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterParenthesisExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitParenthesisExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitParenthesisExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(MapExprGrammarParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MapExprGrammarParser.MINUS, 0); }
		public AddSubExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterAddSubExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitAddSubExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitAddSubExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ParenthesisExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(56);
				match(T__0);
				setState(57);
				expression(0);
				setState(58);
				match(T__1);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case NAME:
			case NUMBER:
			case STRING:
				{
				_localctx = new AtomExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				atomexpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(69);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(63);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(64);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASTERISK) | (1L << SLASH) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(65);
						expression(4);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(66);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(67);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(68);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MapExprGrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MapExprGrammarParser.NAME, i);
		}
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitFuncName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(NAME);
			setState(75);
			match(T__3);
			setState(76);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public ActivityContext activity() {
			return getRuleContext(ActivityContext.class,0);
		}
		public FlowContext flow() {
			return getRuleContext(FlowContext.class,0);
		}
		public CurrentContext current() {
			return getRuleContext(CurrentContext.class,0);
		}
		public ContainerServiceContext containerService() {
			return getRuleContext(ContainerServiceContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				activity();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				flow();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				current();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				containerService();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActivityContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MapExprGrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MapExprGrammarParser.NAME, i);
		}
		public ActivityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterActivity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitActivity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitActivity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActivityContext activity() throws RecognitionException {
		ActivityContext _localctx = new ActivityContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_activity);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(84);
				match(T__4);
				}
			}

			setState(87);
			match(T__5);
			setState(88);
			match(NAME);
			setState(89);
			match(T__6);
			setState(90);
			match(NAME);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(91);
					match(T__3);
					setState(92);
					match(NAME);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(98);
				match(T__7);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MapExprGrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MapExprGrammarParser.NAME, i);
		}
		public FlowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterFlow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitFlow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitFlow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowContext flow() throws RecognitionException {
		FlowContext _localctx = new FlowContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_flow);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(101);
				match(T__4);
				}
			}

			setState(104);
			match(T__8);
			setState(105);
			match(NAME);
			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(106);
					match(T__3);
					setState(107);
					match(NAME);
					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(113);
				match(T__7);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CurrentContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MapExprGrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MapExprGrammarParser.NAME, i);
		}
		public CurrentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_current; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterCurrent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitCurrent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitCurrent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CurrentContext current() throws RecognitionException {
		CurrentContext _localctx = new CurrentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_current);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__9);
			setState(117);
			match(NAME);
			setState(122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(118);
					match(T__3);
					setState(119);
					match(NAME);
					}
					} 
				}
				setState(124);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainerServiceContext extends ParserRuleContext {
		public ContainerServiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containerService; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).enterContainerService(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapExprGrammarListener ) ((MapExprGrammarListener)listener).exitContainerService(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitContainerService(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainerServiceContext containerService() throws RecognitionException {
		ContainerServiceContext _localctx = new ContainerServiceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_containerService);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u0082\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\35\n\2\5\2\37\n\2\3\2\3\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\38\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6U"+
		"\n\6\3\7\5\7X\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7`\n\7\f\7\16\7c\13\7\3\7"+
		"\5\7f\n\7\3\b\5\bi\n\b\3\b\3\b\3\b\3\b\7\bo\n\b\f\b\16\br\13\b\3\b\5\b"+
		"u\n\b\3\t\3\t\3\t\3\t\7\t{\n\t\f\t\16\t~\13\t\3\n\3\n\3\n\2\4\2\6\13\2"+
		"\4\6\b\n\f\16\20\22\2\6\3\2\20\25\3\2\16\17\3\2\26\30\3\2\31\32\2\u008c"+
		"\2\36\3\2\2\2\4\67\3\2\2\2\6?\3\2\2\2\bL\3\2\2\2\nT\3\2\2\2\fW\3\2\2\2"+
		"\16h\3\2\2\2\20v\3\2\2\2\22\177\3\2\2\2\24\25\b\2\1\2\25\26\7\3\2\2\26"+
		"\27\5\2\2\2\27\30\7\4\2\2\30\37\3\2\2\2\31\34\5\4\3\2\32\33\t\2\2\2\33"+
		"\35\5\4\3\2\34\32\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\24\3\2\2\2\36"+
		"\31\3\2\2\2\37%\3\2\2\2 !\f\4\2\2!\"\t\3\2\2\"$\5\2\2\5# \3\2\2\2$\'\3"+
		"\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2()\5\b\5\2)*\7\3\2\2*"+
		"/\5\4\3\2+,\7\5\2\2,.\5\4\3\2-+\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2"+
		"\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\4\2\2\638\3\2\2\2\648\7\34\2\2"+
		"\658\7\35\2\2\668\5\n\6\2\67(\3\2\2\2\67\64\3\2\2\2\67\65\3\2\2\2\67\66"+
		"\3\2\2\28\5\3\2\2\29:\b\4\1\2:;\7\3\2\2;<\5\6\4\2<=\7\4\2\2=@\3\2\2\2"+
		">@\5\4\3\2?9\3\2\2\2?>\3\2\2\2@I\3\2\2\2AB\f\5\2\2BC\t\4\2\2CH\5\6\4\6"+
		"DE\f\4\2\2EF\t\5\2\2FH\5\6\4\5GA\3\2\2\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2"+
		"IJ\3\2\2\2J\7\3\2\2\2KI\3\2\2\2LM\7\33\2\2MN\7\6\2\2NO\7\33\2\2O\t\3\2"+
		"\2\2PU\5\f\7\2QU\5\16\b\2RU\5\20\t\2SU\5\22\n\2TP\3\2\2\2TQ\3\2\2\2TR"+
		"\3\2\2\2TS\3\2\2\2U\13\3\2\2\2VX\7\7\2\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2"+
		"YZ\7\b\2\2Z[\7\33\2\2[\\\7\t\2\2\\a\7\33\2\2]^\7\6\2\2^`\7\33\2\2_]\3"+
		"\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2ca\3\2\2\2df\7\n\2\2ed\3"+
		"\2\2\2ef\3\2\2\2f\r\3\2\2\2gi\7\7\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk"+
		"\7\13\2\2kp\7\33\2\2lm\7\6\2\2mo\7\33\2\2nl\3\2\2\2or\3\2\2\2pn\3\2\2"+
		"\2pq\3\2\2\2qt\3\2\2\2rp\3\2\2\2su\7\n\2\2ts\3\2\2\2tu\3\2\2\2u\17\3\2"+
		"\2\2vw\7\f\2\2w|\7\33\2\2xy\7\6\2\2y{\7\33\2\2zx\3\2\2\2{~\3\2\2\2|z\3"+
		"\2\2\2|}\3\2\2\2}\21\3\2\2\2~|\3\2\2\2\177\u0080\7\r\2\2\u0080\23\3\2"+
		"\2\2\22\34\36%/\67?GITWaehpt|";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}