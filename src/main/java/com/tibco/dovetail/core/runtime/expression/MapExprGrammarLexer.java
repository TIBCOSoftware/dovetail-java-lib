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
		T__9=10, T__10=11, T__11=12, T__12=13, AND=14, OR=15, EQUAL=16, NOTEQUAL=17, 
		GT=18, LT=19, GE=20, LE=21, ASTERISK=22, SLASH=23, MOD=24, PLUS=25, MINUS=26, 
		NAME=27, NUMBER=28, STRING=29, WHITESPACE=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "LETTER", "DIGIT", "AND", "OR", "EQUAL", 
		"NOTEQUAL", "GT", "LT", "GE", "LE", "ASTERISK", "SLASH", "MOD", "PLUS", 
		"MINUS", "NAME", "NUMBER", "STRING", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "','", "'.'", "'{{'", "'$activity['", "'].'", "'}}'", 
		"'$flow.transactionInput.'", "'$current.iteration.key'", "'$current.iteration.value.'", 
		"'$.'", "'$flow.containerServiceStub'", null, null, "'=='", null, "'>'", 
		"'<'", "'>='", "'<='", "'*'", "'/'", "'%'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "AND", "OR", "EQUAL", "NOTEQUAL", "GT", "LT", "GE", "LE", 
		"ASTERISK", "SLASH", "MOD", "PLUS", "MINUS", "NAME", "NUMBER", "STRING", 
		"WHITESPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u0126\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00d0\n\21\3\22\3\22\3\22\3\22\5\22\u00d6\n\22\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\5\24\u00df\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\6\36"+
		"\u00f6\n\36\r\36\16\36\u00f7\3\36\7\36\u00fb\n\36\f\36\16\36\u00fe\13"+
		"\36\3\36\3\36\6\36\u0102\n\36\r\36\16\36\u0103\3\36\3\36\5\36\u0108\n"+
		"\36\3\37\6\37\u010b\n\37\r\37\16\37\u010c\3\37\3\37\6\37\u0111\n\37\r"+
		"\37\16\37\u0112\5\37\u0115\n\37\3 \3 \7 \u0119\n \f \16 \u011c\13 \3 "+
		"\3 \3!\6!\u0121\n!\r!\16!\u0122\3!\3!\3\u011a\2\"\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\2\37\2!\20#\21%\22\'\23"+
		")\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A \3\2\n\5\2C"+
		"\\aac|\3\2\62;\4\2CCcc\4\2PPpp\4\2FFff\4\2QQqq\4\2TTtt\5\2\f\f\17\17\""+
		"\"\2\u012f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13"+
		"K\3\2\2\2\rN\3\2\2\2\17Y\3\2\2\2\21\\\3\2\2\2\23_\3\2\2\2\25w\3\2\2\2"+
		"\27\u008e\3\2\2\2\31\u00a8\3\2\2\2\33\u00ab\3\2\2\2\35\u00c6\3\2\2\2\37"+
		"\u00c8\3\2\2\2!\u00cf\3\2\2\2#\u00d5\3\2\2\2%\u00d7\3\2\2\2\'\u00de\3"+
		"\2\2\2)\u00e0\3\2\2\2+\u00e2\3\2\2\2-\u00e4\3\2\2\2/\u00e7\3\2\2\2\61"+
		"\u00ea\3\2\2\2\63\u00ec\3\2\2\2\65\u00ee\3\2\2\2\67\u00f0\3\2\2\29\u00f2"+
		"\3\2\2\2;\u00f5\3\2\2\2=\u010a\3\2\2\2?\u0116\3\2\2\2A\u0120\3\2\2\2C"+
		"D\7*\2\2D\4\3\2\2\2EF\7+\2\2F\6\3\2\2\2GH\7.\2\2H\b\3\2\2\2IJ\7\60\2\2"+
		"J\n\3\2\2\2KL\7}\2\2LM\7}\2\2M\f\3\2\2\2NO\7&\2\2OP\7c\2\2PQ\7e\2\2QR"+
		"\7v\2\2RS\7k\2\2ST\7x\2\2TU\7k\2\2UV\7v\2\2VW\7{\2\2WX\7]\2\2X\16\3\2"+
		"\2\2YZ\7_\2\2Z[\7\60\2\2[\20\3\2\2\2\\]\7\177\2\2]^\7\177\2\2^\22\3\2"+
		"\2\2_`\7&\2\2`a\7h\2\2ab\7n\2\2bc\7q\2\2cd\7y\2\2de\7\60\2\2ef\7v\2\2"+
		"fg\7t\2\2gh\7c\2\2hi\7p\2\2ij\7u\2\2jk\7c\2\2kl\7e\2\2lm\7v\2\2mn\7k\2"+
		"\2no\7q\2\2op\7p\2\2pq\7K\2\2qr\7p\2\2rs\7r\2\2st\7w\2\2tu\7v\2\2uv\7"+
		"\60\2\2v\24\3\2\2\2wx\7&\2\2xy\7e\2\2yz\7w\2\2z{\7t\2\2{|\7t\2\2|}\7g"+
		"\2\2}~\7p\2\2~\177\7v\2\2\177\u0080\7\60\2\2\u0080\u0081\7k\2\2\u0081"+
		"\u0082\7v\2\2\u0082\u0083\7g\2\2\u0083\u0084\7t\2\2\u0084\u0085\7c\2\2"+
		"\u0085\u0086\7v\2\2\u0086\u0087\7k\2\2\u0087\u0088\7q\2\2\u0088\u0089"+
		"\7p\2\2\u0089\u008a\7\60\2\2\u008a\u008b\7m\2\2\u008b\u008c\7g\2\2\u008c"+
		"\u008d\7{\2\2\u008d\26\3\2\2\2\u008e\u008f\7&\2\2\u008f\u0090\7e\2\2\u0090"+
		"\u0091\7w\2\2\u0091\u0092\7t\2\2\u0092\u0093\7t\2\2\u0093\u0094\7g\2\2"+
		"\u0094\u0095\7p\2\2\u0095\u0096\7v\2\2\u0096\u0097\7\60\2\2\u0097\u0098"+
		"\7k\2\2\u0098\u0099\7v\2\2\u0099\u009a\7g\2\2\u009a\u009b\7t\2\2\u009b"+
		"\u009c\7c\2\2\u009c\u009d\7v\2\2\u009d\u009e\7k\2\2\u009e\u009f\7q\2\2"+
		"\u009f\u00a0\7p\2\2\u00a0\u00a1\7\60\2\2\u00a1\u00a2\7x\2\2\u00a2\u00a3"+
		"\7c\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7g\2\2\u00a6"+
		"\u00a7\7\60\2\2\u00a7\30\3\2\2\2\u00a8\u00a9\7&\2\2\u00a9\u00aa\7\60\2"+
		"\2\u00aa\32\3\2\2\2\u00ab\u00ac\7&\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae"+
		"\7n\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7y\2\2\u00b0\u00b1\7\60\2\2\u00b1"+
		"\u00b2\7e\2\2\u00b2\u00b3\7q\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7v\2\2"+
		"\u00b5\u00b6\7c\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9"+
		"\7g\2\2\u00b9\u00ba\7t\2\2\u00ba\u00bb\7U\2\2\u00bb\u00bc\7g\2\2\u00bc"+
		"\u00bd\7t\2\2\u00bd\u00be\7x\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7e\2\2"+
		"\u00c0\u00c1\7g\2\2\u00c1\u00c2\7U\2\2\u00c2\u00c3\7v\2\2\u00c3\u00c4"+
		"\7w\2\2\u00c4\u00c5\7d\2\2\u00c5\34\3\2\2\2\u00c6\u00c7\t\2\2\2\u00c7"+
		"\36\3\2\2\2\u00c8\u00c9\t\3\2\2\u00c9 \3\2\2\2\u00ca\u00cb\t\4\2\2\u00cb"+
		"\u00cc\t\5\2\2\u00cc\u00d0\t\6\2\2\u00cd\u00ce\7(\2\2\u00ce\u00d0\7(\2"+
		"\2\u00cf\u00ca\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\"\3\2\2\2\u00d1\u00d2"+
		"\t\7\2\2\u00d2\u00d6\t\b\2\2\u00d3\u00d4\7~\2\2\u00d4\u00d6\7~\2\2\u00d5"+
		"\u00d1\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6$\3\2\2\2\u00d7\u00d8\7?\2\2\u00d8"+
		"\u00d9\7?\2\2\u00d9&\3\2\2\2\u00da\u00db\7#\2\2\u00db\u00df\7?\2\2\u00dc"+
		"\u00dd\7>\2\2\u00dd\u00df\7@\2\2\u00de\u00da\3\2\2\2\u00de\u00dc\3\2\2"+
		"\2\u00df(\3\2\2\2\u00e0\u00e1\7@\2\2\u00e1*\3\2\2\2\u00e2\u00e3\7>\2\2"+
		"\u00e3,\3\2\2\2\u00e4\u00e5\7@\2\2\u00e5\u00e6\7?\2\2\u00e6.\3\2\2\2\u00e7"+
		"\u00e8\7>\2\2\u00e8\u00e9\7?\2\2\u00e9\60\3\2\2\2\u00ea\u00eb\7,\2\2\u00eb"+
		"\62\3\2\2\2\u00ec\u00ed\7\61\2\2\u00ed\64\3\2\2\2\u00ee\u00ef\7\'\2\2"+
		"\u00ef\66\3\2\2\2\u00f0\u00f1\7-\2\2\u00f18\3\2\2\2\u00f2\u00f3\7/\2\2"+
		"\u00f3:\3\2\2\2\u00f4\u00f6\5\35\17\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7"+
		"\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fc\3\2\2\2\u00f9"+
		"\u00fb\5\37\20\2\u00fa\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3"+
		"\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0107\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff"+
		"\u0101\7]\2\2\u0100\u0102\5\37\20\2\u0101\u0100\3\2\2\2\u0102\u0103\3"+
		"\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0106\7_\2\2\u0106\u0108\3\2\2\2\u0107\u00ff\3\2\2\2\u0107\u0108\3\2"+
		"\2\2\u0108<\3\2\2\2\u0109\u010b\5\37\20\2\u010a\u0109\3\2\2\2\u010b\u010c"+
		"\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0114\3\2\2\2\u010e"+
		"\u0110\7\60\2\2\u010f\u0111\5\37\20\2\u0110\u010f\3\2\2\2\u0111\u0112"+
		"\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114"+
		"\u010e\3\2\2\2\u0114\u0115\3\2\2\2\u0115>\3\2\2\2\u0116\u011a\7$\2\2\u0117"+
		"\u0119\13\2\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u011b\3"+
		"\2\2\2\u011a\u0118\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u011e\7$\2\2\u011e@\3\2\2\2\u011f\u0121\t\t\2\2\u0120\u011f\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124\u0125\b!\2\2\u0125B\3\2\2\2\17\2\u00cf\u00d5\u00de\u00f7\u00fc"+
		"\u0103\u0107\u010c\u0112\u0114\u011a\u0122\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}