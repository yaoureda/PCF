package parserANTLR

import ast.*
import ast.Exp.*

import scala.jdk.CollectionConverters.*

class ASTVisitor extends PcfBaseVisitor[Exp] :

  override def visitNumber(ctx: PcfParser.NumberContext): Exp =
    Number(ctx.getText.toInt)

  override def visitApp(ctx: PcfParser.AppContext): Exp =
    val concreteExps = ctx.exp.asScala.toList
    val List(func, arg) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    App(func, arg)

  override def visitBinaryExp1(ctx: PcfParser.BinaryExp1Context): Exp =
    val s = ctx.OP1().getText
    val op = Op.parse(s)
    // ctx.term is a Java list, it is translated in a Scala list
    // (initially, to an instance of Buffer, using a collection
    // converter, as Java lists are mutable)
    val concreteExps = ctx.exp.asScala.toList
    val List(exp1, exp2) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    BinaryExp(op, exp1, exp2)

  override def visitBinaryExp2(ctx: PcfParser.BinaryExp2Context): Exp =
    val s = ctx.OP2().getText
    val op = Op.parse(s)
    // ctx.term is a Java list, it is translated in a Scala list
    // (initially, to an instance of Buffer, using a collection
    // converter, as Java lists are mutable)
    val concreteExps = ctx.exp.asScala.toList
    val List(exp1, exp2) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    BinaryExp(op, exp1, exp2)

  override def visitFun(ctx: PcfParser.FunContext): Exp =
    val x = ctx.VAR().getText
    val body = visit(ctx.exp())
    Fun(x, body)

  override def visitParExp(ctx: PcfParser.ParExpContext): Exp =
    visit(ctx.exp())

  override def visitIfZero(ctx: PcfParser.IfZeroContext): Exp =
    val concreteExps = ctx.exp.asScala.toList
    val List(test, exp1, exp2) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    IfZero(test, exp1, exp2)

  override def visitVar(ctx: PcfParser.VarContext): Exp =
    Var(ctx.VAR().getText)

  override def visitLet(ctx: PcfParser.LetContext): Exp =
    val x = ctx.VAR().getText
    val concreteExps = ctx.exp.asScala.toList
    val List(exp1, exp2) =
      for (concreteExp <- concreteExps) yield
        visit(concreteExp)
    Let(x, exp1, exp2)

