grammar Pcf;

exp: NUMBER                     # Number
   | exp exp                    # App
   | exp OP1 exp                # BinaryExp1
   | exp OP2 exp                # BinaryExp2
   | '(' exp ')'                 # ParExp
   | 'fun' VAR '->' exp          # Fun
   | 'ifz' exp 'then' exp 'else' exp   # IfZero
   | VAR                         # Var
   | 'let' VAR '=' exp 'in' exp    # Let
   ;

NUMBER: '0' | [1-9][0-9]* ;
OP1: '*' | '/' ;
OP2: '+' | '-' ;
VAR: [a-z][a-z0-9]* ;
WS : [ \r\t\n]+ -> skip ;
