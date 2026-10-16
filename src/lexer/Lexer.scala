package lexer

import lexer.Token.*

import java.io.InputStream
import scala.annotation.tailrec

class Lexer(in: InputStream):
  private var currentChar: Int = -1
  nextChar()

  private def nextChar(): Unit =
    currentChar = in.read()

  def lex(): List[Token] =
    var tokens: List[Token] = List()
    var token = nextToken()
    while token != EOF do
      tokens = token :: tokens
      token = nextToken()
    end while
    tokens.reverse

  @tailrec
  private def nextToken(): Token =
    currentChar match
      case -1 => in.close(); EOF
      case ' ' | '\t' | '\n' | '\r' => nextChar(); nextToken()
      case '0' => nextChar(); NUMBER(0)
      case c if '1' <= c && c <= '9' =>
        nextChar();
        var res = c - '0'
        while '1' <= currentChar && c <= currentChar do
          res = res * 10 + currentChar - '0'
          nextChar();
        end while
        NUMBER(res)
      case '(' => nextChar(); LPAR
      case ')' => nextChar(); RPAR
      case '+' => nextChar(); PLUS
      case '-' => nextChar(); MINUS
      case '*' => nextChar(); MULTIPLY
      case '/' => nextChar(); DIVIDE
      case 'i' =>
        nextChar()
        if currentChar == 'f' then
          nextChar()
          if currentChar == 'z' then
            nextChar()
            IFZ
          else throw new Exception(s"Unexpected character: ${currentChar.toChar}, ascii $currentChar")
        else throw new Exception(s"Unexpected character: ${currentChar.toChar}, ascii $currentChar")
      case _ => throw new Exception(s"Unexpected character: ${currentChar.toChar}, ascii $currentChar")
    end match

object Lexer:
  private var lexer: Option[Lexer] = None
  def apply(in: InputStream): Unit =
    lexer = Some(new Lexer(in))
  def nextToken(): Token = lexer.get.nextToken()