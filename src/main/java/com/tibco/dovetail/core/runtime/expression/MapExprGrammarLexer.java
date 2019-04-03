// Generated from MapExprGrammar.g4 by ANTLR 4.7.1
package com.tibco.dovetail.core.runtime.expression;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MapExprGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AND=13, OR=14, EQUAL=15, NOTEQUAL=16, GT=17, 
		LT=18, GE=19, LE=20, ASTERISK=21, SLASH=22, MOD=23, PLUS=24, MINUS=25, 
		NAME=26, NUMBER=27, STRING=28, WHITESPACE=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "LETTER", "DIGIT", "AND", "OR", "EQUAL", "NOTEQUAL", 
		"GT", "LT", "GE", "LE", "ASTERISK", "SLASH", "MOD", "PLUS", "MINUS", "NAME", 
		"NUMBER", "STRING", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'.'", "'{{'", "'$activity['", "'].'", "'}}'", 
		"'$flow.'", "'$current.iteration.key'", "'$current.iteration.value.'", 
		"'$.'", null, null, "'=='", null, "'>'", "'<'", "'>='", "'<='", "'*'", 
		"'/'", "'%'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AND", "OR", "EQUAL", "NOTEQUAL", "GT", "LT", "GE", "LE", "ASTERISK", 
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


	public MapExprGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MapExprGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00f8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u00a2\n\20\3\21\3\21\3\21\3\21\5\21\u00a8\n\21\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\5\23\u00b1\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\6\35\u00c8\n\35\r\35\16\35\u00c9\3\35\7\35\u00cd\n\35\f\35\16\35\u00d0"+
		"\13\35\3\35\3\35\6\35\u00d4\n\35\r\35\16\35\u00d5\3\35\3\35\5\35\u00da"+
		"\n\35\3\36\6\36\u00dd\n\36\r\36\16\36\u00de\3\36\3\36\6\36\u00e3\n\36"+
		"\r\36\16\36\u00e4\5\36\u00e7\n\36\3\37\3\37\7\37\u00eb\n\37\f\37\16\37"+
		"\u00ee\13\37\3\37\3\37\3 \6 \u00f3\n \r \16 \u00f4\3 \3 \3\u00ec\2!\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\2\35\2\37\17"+
		"!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36"+
		"?\37\3\2\n\5\2C\\aac|\3\2\62;\4\2CCcc\4\2PPpp\4\2FFff\4\2QQqq\4\2TTtt"+
		"\5\2\f\f\17\17\"\"\2\u0101\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5C\3\2\2\2\7E\3\2\2\2\tG\3\2\2\2"+
		"\13I\3\2\2\2\rL\3\2\2\2\17W\3\2\2\2\21Z\3\2\2\2\23]\3\2\2\2\25d\3\2\2"+
		"\2\27{\3\2\2\2\31\u0095\3\2\2\2\33\u0098\3\2\2\2\35\u009a\3\2\2\2\37\u00a1"+
		"\3\2\2\2!\u00a7\3\2\2\2#\u00a9\3\2\2\2%\u00b0\3\2\2\2\'\u00b2\3\2\2\2"+
		")\u00b4\3\2\2\2+\u00b6\3\2\2\2-\u00b9\3\2\2\2/\u00bc\3\2\2\2\61\u00be"+
		"\3\2\2\2\63\u00c0\3\2\2\2\65\u00c2\3\2\2\2\67\u00c4\3\2\2\29\u00c7\3\2"+
		"\2\2;\u00dc\3\2\2\2=\u00e8\3\2\2\2?\u00f2\3\2\2\2AB\7*\2\2B\4\3\2\2\2"+
		"CD\7+\2\2D\6\3\2\2\2EF\7.\2\2F\b\3\2\2\2GH\7\60\2\2H\n\3\2\2\2IJ\7}\2"+
		"\2JK\7}\2\2K\f\3\2\2\2LM\7&\2\2MN\7c\2\2NO\7e\2\2OP\7v\2\2PQ\7k\2\2QR"+
		"\7x\2\2RS\7k\2\2ST\7v\2\2TU\7{\2\2UV\7]\2\2V\16\3\2\2\2WX\7_\2\2XY\7\60"+
		"\2\2Y\20\3\2\2\2Z[\7\177\2\2[\\\7\177\2\2\\\22\3\2\2\2]^\7&\2\2^_\7h\2"+
		"\2_`\7n\2\2`a\7q\2\2ab\7y\2\2bc\7\60\2\2c\24\3\2\2\2de\7&\2\2ef\7e\2\2"+
		"fg\7w\2\2gh\7t\2\2hi\7t\2\2ij\7g\2\2jk\7p\2\2kl\7v\2\2lm\7\60\2\2mn\7"+
		"k\2\2no\7v\2\2op\7g\2\2pq\7t\2\2qr\7c\2\2rs\7v\2\2st\7k\2\2tu\7q\2\2u"+
		"v\7p\2\2vw\7\60\2\2wx\7m\2\2xy\7g\2\2yz\7{\2\2z\26\3\2\2\2{|\7&\2\2|}"+
		"\7e\2\2}~\7w\2\2~\177\7t\2\2\177\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081"+
		"\u0082\7p\2\2\u0082\u0083\7v\2\2\u0083\u0084\7\60\2\2\u0084\u0085\7k\2"+
		"\2\u0085\u0086\7v\2\2\u0086\u0087\7g\2\2\u0087\u0088\7t\2\2\u0088\u0089"+
		"\7c\2\2\u0089\u008a\7v\2\2\u008a\u008b\7k\2\2\u008b\u008c\7q\2\2\u008c"+
		"\u008d\7p\2\2\u008d\u008e\7\60\2\2\u008e\u008f\7x\2\2\u008f\u0090\7c\2"+
		"\2\u0090\u0091\7n\2\2\u0091\u0092\7w\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7\60\2\2\u0094\30\3\2\2\2\u0095\u0096\7&\2\2\u0096\u0097\7\60\2\2\u0097"+
		"\32\3\2\2\2\u0098\u0099\t\2\2\2\u0099\34\3\2\2\2\u009a\u009b\t\3\2\2\u009b"+
		"\36\3\2\2\2\u009c\u009d\t\4\2\2\u009d\u009e\t\5\2\2\u009e\u00a2\t\6\2"+
		"\2\u009f\u00a0\7(\2\2\u00a0\u00a2\7(\2\2\u00a1\u009c\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a2 \3\2\2\2\u00a3\u00a4\t\7\2\2\u00a4\u00a8\t\b\2\2\u00a5"+
		"\u00a6\7~\2\2\u00a6\u00a8\7~\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a5\3\2\2"+
		"\2\u00a8\"\3\2\2\2\u00a9\u00aa\7?\2\2\u00aa\u00ab\7?\2\2\u00ab$\3\2\2"+
		"\2\u00ac\u00ad\7#\2\2\u00ad\u00b1\7?\2\2\u00ae\u00af\7>\2\2\u00af\u00b1"+
		"\7@\2\2\u00b0\u00ac\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1&\3\2\2\2\u00b2\u00b3"+
		"\7@\2\2\u00b3(\3\2\2\2\u00b4\u00b5\7>\2\2\u00b5*\3\2\2\2\u00b6\u00b7\7"+
		"@\2\2\u00b7\u00b8\7?\2\2\u00b8,\3\2\2\2\u00b9\u00ba\7>\2\2\u00ba\u00bb"+
		"\7?\2\2\u00bb.\3\2\2\2\u00bc\u00bd\7,\2\2\u00bd\60\3\2\2\2\u00be\u00bf"+
		"\7\61\2\2\u00bf\62\3\2\2\2\u00c0\u00c1\7\'\2\2\u00c1\64\3\2\2\2\u00c2"+
		"\u00c3\7-\2\2\u00c3\66\3\2\2\2\u00c4\u00c5\7/\2\2\u00c58\3\2\2\2\u00c6"+
		"\u00c8\5\33\16\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3"+
		"\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00ce\3\2\2\2\u00cb\u00cd\5\35\17\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d9\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d3\7]\2\2\u00d2"+
		"\u00d4\5\35\17\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3"+
		"\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7_\2\2\u00d8"+
		"\u00da\3\2\2\2\u00d9\u00d1\3\2\2\2\u00d9\u00da\3\2\2\2\u00da:\3\2\2\2"+
		"\u00db\u00dd\5\35\17\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc"+
		"\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e6\3\2\2\2\u00e0\u00e2\7\60\2\2"+
		"\u00e1\u00e3\5\35\17\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e0\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7<\3\2\2\2\u00e8\u00ec\7$\2\2\u00e9\u00eb\13\2\2\2"+
		"\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ec\u00ea"+
		"\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7$\2\2\u00f0"+
		">\3\2\2\2\u00f1\u00f3\t\t\2\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2"+
		"\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7"+
		"\b \2\2\u00f7@\3\2\2\2\17\2\u00a1\u00a7\u00b0\u00c9\u00ce\u00d5\u00d9"+
		"\u00de\u00e4\u00e6\u00ec\u00f4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}