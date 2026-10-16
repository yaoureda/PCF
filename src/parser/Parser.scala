package parser

import ast.Exp
import ast.Exp.*
import ast.Op.*
import lexer.Lexer.nextToken
import lexer.Token
import lexer.Token.*

object Parser:
  def parse(token: Token): Exp =
    token match
      case NUMBER(value) => Number(value)
      case LPAR => parseComposite(nextToken())
      case _ => throw new Exception(s"Unexpected token: $token")

  def parseComposite(token: Token): Exp =
    token match
      case IFZ =>
        val t = parse(nextToken())
        val e1 = parse(nextToken())
        val e2 = parse(nextToken())
        val token2 = nextToken()
        if token2 == RPAR then
          IfZero(t, e1, e2)
        else throw new Exception(s"Unexpected token: $token")

      case PLUS | MINUS | MULTIPLY | DIVIDE =>
        val op = token match
          case PLUS     => Plus
          case MINUS    => Minus
          case MULTIPLY => Multiply
          case DIVIDE   => Divide
          case _        => throw new Exception(s"Unexpected token: $token")
        val e1 = parse(nextToken())
        val e2 = parse(nextToken())
        if nextToken() == RPAR then
          BinaryExp(op, e1, e2)
        else
          throw new Exception(s"Unexpected token: $token")

      case _ => throw new Exception(s"Unexpected token: $token")
  