package parser

import ast.Exp
import lexer.Lexer

import java.io.InputStream

object AbstractParser :
  def analyse(in: InputStream): Exp =
    Lexer(in)
    val exp = Parser.parse(Lexer.nextToken())
    val token = Lexer.nextToken()
    if token != lexer.Token.EOF then
      throw new Exception(s"Unexpected token $token after parsing complete expression $exp")
    exp  

