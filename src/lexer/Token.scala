package lexer

enum Token:
  case NUMBER(value: Int)
  case LPAR
  case RPAR
  case PLUS, MINUS, MULTIPLY, DIVIDE
  case IFZ
  case EOF
