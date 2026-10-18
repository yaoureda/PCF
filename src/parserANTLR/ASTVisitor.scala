package parserANTLR

import ast.*
import ast.Exp.*

import scala.jdk.CollectionConverters.*

class ASTVisitor extends PcfBaseVisitor[Exp] :

  override def visitNumber(ctx: PcfParser.NumberContext): Exp =
    Number(ctx.getText.toInt)

  override def visitBinaryExp(ctx: PcfParser.BinaryExpContext): Exp =
    val s = ctx.OP().getText
    val op = Op.parse(s)
    // ctx.term is a Java list, it is translated in a Scala list
    // (initially, to an instance of Buffer, using a collection
    // converter, as Java lists are mutable)
    val concreteExps = ctx.exp.asScala.toList
    val List(exp1, exp2) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    BinaryExp(op, exp1, exp2)

  override def visitIfZero(ctx: PcfParser.IfZeroContext): Exp =
    val concreteExps = ctx.exp.asScala.toList
    val List(exp1, exp2, exp3) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    IfZero(exp1, exp2, exp3)

