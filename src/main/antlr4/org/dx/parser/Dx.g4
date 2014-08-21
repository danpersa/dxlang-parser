grammar Dx;

prog:   (   moduleDef
        )*
        EOF
        ;

moduleDef
    : 'module' moduleName '{' functionDef*? '}'
    ;

functionDef
    : 'func' functionName (paramName ':' paramType)* ':' returnType block
    ;

block
    : '{' expression*? '}'
    ;

expression
    : print ';'         # expressionPrint
    | methodCall        # expressionMethodCall
    | functionCall      # expressionFunctionCall
    ;
    
methodCall 
    : object '.' functionCall
    ;
    
functionCall
    : functionName (Identifier ':' arg)*
    | functionName ':' arg (Identifier ':' arg)*
    ;

functionName
    : Identifier
    ;
    
moduleName
    : Identifier
    ;

object
    : Identifier
    ;

returnType
    : Identifier
    ;
    
paramName
    : Identifier
    ;
    
paramType
    : Identifier
    ;

arg
    : expression 
    | Identifier
    ;

print
    : 'print' STRING
    ;

Identifier
    :    DxLetter DxLetterOrDigit*
    ;

fragment
DxLetter
    :    [a-zA-Z$_] // these are the "java letters" below 0xFF
    |    // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierStart(_input.LA(-1))}?
    |    // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

fragment
DxLetterOrDigit
    :    [a-zA-Z0-9$_] // these are the "java letters or digits" below 0xFF
    |    // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierPart(_input.LA(-1))}?
    |    // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

HEX :   '0' ('x'|'X') HEXDIGIT+ [Ll]? ;

INT :   DIGIT+ [Ll]? ;

fragment
HEXDIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
DIGIT:  '0'..'9' ;

STRING
    :   '"' ( ESC | ~[\\"] )*? '"'
    |   '\'' ( ESC | ~[\\'] )*? '\''
    ;

fragment
ESC :   '\\' ([abtnfrv]|'"'|'\'')
    |   UNICODE_ESCAPE
    |   HEX_ESCAPE
    |   OCTAL_ESCAPE
    ;

fragment
UNICODE_ESCAPE
    :   '\\' 'u' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT
    |   '\\' 'u' '{' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT '}'
    ;

fragment
OCTAL_ESCAPE
    :   '\\' [0-3] [0-7] [0-7]
    |   '\\' [0-7] [0-7]
    |   '\\' [0-7]
    ;

fragment
HEX_ESCAPE
    :   '\\' HEXDIGIT HEXDIGIT?
    ;


// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' -> skip;

WS      :   [ \t]+ -> skip ;
