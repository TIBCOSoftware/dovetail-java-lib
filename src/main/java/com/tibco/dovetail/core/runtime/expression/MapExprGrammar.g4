grammar MapExprGrammar;
 
condition			: '(' condition ')'													#parenthesisCond
					| condition (AND|OR) condition										#andOrCond
					| atomexpr ((EQUAL | NOTEQUAL | GT | LT | GE | LE) atomexpr)?			#condExp														
					;
					
atomexpr          	: funcName '(' (')' | atomexpr (',' atomexpr)* ')')                   #functionExp
                    | NUMBER                                    							#numericAtomExp
                    | STRING																#stringAtomExp
                    | variable		                                       				#varAtomExp
                    ;
                    
expression          : '(' expression ')'                        							#parenthesisExp
                    | expression (ASTERISK|SLASH | MOD) expression    					#mulDivModExp
                    | expression (PLUS|MINUS) expression        							#addSubExp
                    | atomexpr									                   		#atomExp
                    ;
                    
funcName				: NAME '.' NAME
					;
variable				: activity
				    | flow
				    | iteratorKey
				    | iteratorValue
				    | current
				    | property
//				    | containerService
					;
					
activity				: ('{{')? '$activity[' NAME '].' NAME ('.' NAME)* ('}}')?;
flow					: ('{{')? '$flow.' NAME ('.' NAME)* ('}}')?;
iteratorKey			: '$iteration.key';
iteratorValue	    : '$iteration[value]'('.' NAME)*;
current				: '$.' NAME ('.' NAME)*;
property				: '$property["' NAME ('.' NAME)* '"]';
 
fragment LETTER     : [a-zA-Z_] ;
fragment DIGIT      : [0-9] ;
 
AND		    		    : [aA][nN][dD] | '&&' ;
OR			        : [oO][rR] | '||' ;
EQUAL				: '==' ;
NOTEQUAL				: '!=' | '<>' ;
GT					: '>' ;
LT					: '<' ;
GE					: '>=' ;
LE					: '<=' ;
ASTERISK            : '*' ;
SLASH               : '/' ;
MOD				    : '%' ;
PLUS                : '+' ;
MINUS               : '-' ;
 
 
NAME                : LETTER+ DIGIT* ('[' DIGIT+ ']')?;
 
NUMBER              : DIGIT+ ('.' DIGIT+)? ;

STRING				: '"' (.)*? '"';
 
WHITESPACE          : [ \r\n]+ -> skip;
