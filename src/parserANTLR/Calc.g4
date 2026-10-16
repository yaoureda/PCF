grammar Calc;

exp: NUMBER                     # Number
   | '(' OP exp exp ')'         # BinaryExp
   | '(' 'ifz' exp exp exp ')'  # IfZero
   ;

NUMBER: '0' | [1-9][0-9]* ;
OP: '+' | '-' | '*' | '/' ;
WS : [ \r\t\n]+ -> skip ;
