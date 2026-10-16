package parserANTLR

import ast.Exp

import java.io.InputStream

object AbstractParser :
  def analyze(in: InputStream): Exp =
    val concreteTree = ConcreteParser.analyze(in)
    val visitor = new ASTVisitor
    visitor.visit(concreteTree).asInstanceOf[Exp]
 
