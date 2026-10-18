package ast

enum Exp:
  case Number(value: Int)
  case BinaryExp(op: Op, exp1: Exp, exp2: Exp)
  case IfZero(test: Exp, exp1: Exp, exp2: Exp)
  case Var(x: String)
  case Let(x: String, exp1: Exp, exp2: Exp)

enum Op:
  case Plus, Minus, Multiply, Divide
  
object Op:
  def parse(op: String): Op = op match
    case "+" => Plus
    case "-" => Minus
    case "*" => Multiply
    case "/" => Divide
    case _ => ???