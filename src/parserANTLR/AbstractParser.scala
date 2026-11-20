package parserANTLR

import ast.Exp

import java.io.InputStream

object AbstractParser :
  def analyze(verbose: Boolean, in: InputStream): Exp =
    val concreteTree = ConcreteParser.analyze(in)
    val visitor = new ASTVisitor
    val exp = visitor.visit(concreteTree)
    if verbose then println(s"AST: $exp")
    exp
 
