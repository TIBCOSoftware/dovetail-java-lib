// Generated from MapExprGrammar.g4 by ANTLR 4.7.2
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
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AND=13, OR=14, EQUAL=15, NOTEQUAL=16, GT=17, 
		LT=18, GE=19, LE=20, ASTERISK=21, SLASH=22, MOD=23, PLUS=24, MINUS=25, 
		NAME=26, NUMBER=27, STRING=28, WHITESPACE=29;
	public static final int
		RULE_condition = 0, RULE_atomexpr = 1, RULE_expression = 2, RULE_funcName = 3, 
		RULE_variable = 4, RULE_activity = 5, RULE_flow = 6, RULE_iteratorKey = 7, 
		RULE_iteratorValue = 8, RULE_current = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"condition", "atomexpr", "expression", "funcName", "variable", "activity", 
			"flow", "iteratorKey", "iteratorValue", "current"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "','", "'.'", "'{{'", "'$activity['", "'].'", "'}}'", 
			"'$flow.'", "'$current.iteration.key'", "'$current.iteration.value.'", 
			"'$.'", null, null, "'=='", null, "'>'", "'<'", "'>='", "'<='", "'*'", 
			"'/'", "'%'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AND", "OR", "EQUAL", "NOTEQUAL", "GT", "LT", "GE", "LE", "ASTERISK", 
			"SLASH", "MOD", "PLUS", "MINUS", "NAME", "NUMBER", "STRING", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ParenthesisCondContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(21);
				match(T__0);
				setState(22);
				condition(0);
				setState(23);
				match(T__1);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case NAME:
			case NUMBER:
			case STRING:
				{
				_localctx = new CondExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(25);
				atomexpr();
				setState(28);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(26);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOTEQUAL) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(27);
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
			setState(37);
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
					setState(32);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(33);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(34);
					condition(3);
					}
					} 
				}
				setState(39);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitVarAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericAtomExpContext extends AtomexprContext {
		public TerminalNode NUMBER() { return getToken(MapExprGrammarParser.NUMBER, 0); }
		public NumericAtomExpContext(AtomexprContext ctx) { copyFrom(ctx); }
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
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				_localctx = new FunctionExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				funcName();
				setState(41);
				match(T__0);
				setState(53);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(42);
					match(T__1);
					}
					break;
				case T__4:
				case T__5:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case NAME:
				case NUMBER:
				case STRING:
					{
					setState(43);
					atomexpr();
					setState(48);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(44);
						match(T__2);
						setState(45);
						atomexpr();
						}
						}
						setState(50);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(51);
					match(T__1);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case NUMBER:
				_localctx = new NumericAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StringAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(STRING);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
				_localctx = new VarAtomExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ParenthesisExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(61);
				match(T__0);
				setState(62);
				expression(0);
				setState(63);
				match(T__1);
				}
				break;
			case T__4:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case NAME:
			case NUMBER:
			case STRING:
				{
				_localctx = new AtomExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				atomexpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(74);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(69);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASTERISK) | (1L << SLASH) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(70);
						expression(4);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(72);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(73);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
			setState(79);
			match(NAME);
			setState(80);
			match(T__3);
			setState(81);
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
		public IteratorKeyContext iteratorKey() {
			return getRuleContext(IteratorKeyContext.class,0);
		}
		public IteratorValueContext iteratorValue() {
			return getRuleContext(IteratorValueContext.class,0);
		}
		public CurrentContext current() {
			return getRuleContext(CurrentContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
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
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				activity();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				flow();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				iteratorKey();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				iteratorValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(87);
				current();
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
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(90);
				match(T__4);
				}
			}

			setState(93);
			match(T__5);
			setState(94);
			match(NAME);
			setState(95);
			match(T__6);
			setState(96);
			match(NAME);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(97);
					match(T__3);
					setState(98);
					match(NAME);
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(104);
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
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(107);
				match(T__4);
				}
			}

			setState(110);
			match(T__8);
			setState(111);
			match(NAME);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(112);
					match(T__3);
					setState(113);
					match(NAME);
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(119);
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

	public static class IteratorKeyContext extends ParserRuleContext {
		public IteratorKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteratorKey; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitIteratorKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteratorKeyContext iteratorKey() throws RecognitionException {
		IteratorKeyContext _localctx = new IteratorKeyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_iteratorKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__9);
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

	public static class IteratorValueContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MapExprGrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MapExprGrammarParser.NAME, i);
		}
		public IteratorValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteratorValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitIteratorValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteratorValueContext iteratorValue() throws RecognitionException {
		IteratorValueContext _localctx = new IteratorValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_iteratorValue);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__10);
			setState(125);
			match(NAME);
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(126);
					match(T__3);
					setState(127);
					match(NAME);
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapExprGrammarVisitor ) return ((MapExprGrammarVisitor<? extends T>)visitor).visitCurrent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CurrentContext current() throws RecognitionException {
		CurrentContext _localctx = new CurrentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_current);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__11);
			setState(134);
			match(NAME);
			setState(139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(135);
					match(T__3);
					setState(136);
					match(NAME);
					}
					} 
				}
				setState(141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u0091\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\37\n\2\5\2!\n\2\3\2\3\2\3\2"+
		"\7\2&\n\2\f\2\16\2)\13\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\61\n\3\f\3\16\3\64"+
		"\13\3\3\3\3\3\5\38\n\3\3\3\3\3\3\3\5\3=\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4E\n\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\5\6[\n\6\3\7\5\7^\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7f\n\7\f\7\16\7i\13\7\3\7\5\7l\n\7\3\b\5\bo\n\b\3\b\3\b\3\b\3\b\7\bu"+
		"\n\b\f\b\16\bx\13\b\3\b\5\b{\n\b\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u0083\n\n"+
		"\f\n\16\n\u0086\13\n\3\13\3\13\3\13\3\13\7\13\u008c\n\13\f\13\16\13\u008f"+
		"\13\13\3\13\2\4\2\6\f\2\4\6\b\n\f\16\20\22\24\2\6\3\2\21\26\3\2\17\20"+
		"\3\2\27\31\3\2\32\33\2\u009d\2 \3\2\2\2\4<\3\2\2\2\6D\3\2\2\2\bQ\3\2\2"+
		"\2\nZ\3\2\2\2\f]\3\2\2\2\16n\3\2\2\2\20|\3\2\2\2\22~\3\2\2\2\24\u0087"+
		"\3\2\2\2\26\27\b\2\1\2\27\30\7\3\2\2\30\31\5\2\2\2\31\32\7\4\2\2\32!\3"+
		"\2\2\2\33\36\5\4\3\2\34\35\t\2\2\2\35\37\5\4\3\2\36\34\3\2\2\2\36\37\3"+
		"\2\2\2\37!\3\2\2\2 \26\3\2\2\2 \33\3\2\2\2!\'\3\2\2\2\"#\f\4\2\2#$\t\3"+
		"\2\2$&\5\2\2\5%\"\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\3\3\2\2\2)"+
		"\'\3\2\2\2*+\5\b\5\2+\67\7\3\2\2,8\7\4\2\2-\62\5\4\3\2./\7\5\2\2/\61\5"+
		"\4\3\2\60.\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2"+
		"\2\2\64\62\3\2\2\2\65\66\7\4\2\2\668\3\2\2\2\67,\3\2\2\2\67-\3\2\2\28"+
		"=\3\2\2\29=\7\35\2\2:=\7\36\2\2;=\5\n\6\2<*\3\2\2\2<9\3\2\2\2<:\3\2\2"+
		"\2<;\3\2\2\2=\5\3\2\2\2>?\b\4\1\2?@\7\3\2\2@A\5\6\4\2AB\7\4\2\2BE\3\2"+
		"\2\2CE\5\4\3\2D>\3\2\2\2DC\3\2\2\2EN\3\2\2\2FG\f\5\2\2GH\t\4\2\2HM\5\6"+
		"\4\6IJ\f\4\2\2JK\t\5\2\2KM\5\6\4\5LF\3\2\2\2LI\3\2\2\2MP\3\2\2\2NL\3\2"+
		"\2\2NO\3\2\2\2O\7\3\2\2\2PN\3\2\2\2QR\7\34\2\2RS\7\6\2\2ST\7\34\2\2T\t"+
		"\3\2\2\2U[\5\f\7\2V[\5\16\b\2W[\5\20\t\2X[\5\22\n\2Y[\5\24\13\2ZU\3\2"+
		"\2\2ZV\3\2\2\2ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\13\3\2\2\2\\^\7\7\2\2]\\"+
		"\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\b\2\2`a\7\34\2\2ab\7\t\2\2bg\7\34\2\2"+
		"cd\7\6\2\2df\7\34\2\2ec\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hk\3\2\2"+
		"\2ig\3\2\2\2jl\7\n\2\2kj\3\2\2\2kl\3\2\2\2l\r\3\2\2\2mo\7\7\2\2nm\3\2"+
		"\2\2no\3\2\2\2op\3\2\2\2pq\7\13\2\2qv\7\34\2\2rs\7\6\2\2su\7\34\2\2tr"+
		"\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wz\3\2\2\2xv\3\2\2\2y{\7\n\2\2z"+
		"y\3\2\2\2z{\3\2\2\2{\17\3\2\2\2|}\7\f\2\2}\21\3\2\2\2~\177\7\r\2\2\177"+
		"\u0084\7\34\2\2\u0080\u0081\7\6\2\2\u0081\u0083\7\34\2\2\u0082\u0080\3"+
		"\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\23\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\7\16\2\2\u0088\u008d\7\34"+
		"\2\2\u0089\u008a\7\6\2\2\u008a\u008c\7\34\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\25\3\2\2"+
		"\2\u008f\u008d\3\2\2\2\24\36 \'\62\67<DLNZ]gknvz\u0084\u008d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}