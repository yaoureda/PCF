package evaluator

import ast.Exp
import ast.Exp.*
import ast.Op.*

object Evaluator :
  def eval(exp: Exp): Int = exp match
    case Number(value) => value
    case IfZero(t, e1, e2) =>
      if eval(t) == 0 then eval(e1) else eval(e2)
    case BinaryExp(op, e1, e2)  =>
      op match
        case Plus => eval(e1) + eval(e2)
        case Minus => eval(e1) - eval(e2)
        case Multiply => eval(e1) * eval(e2)
        case Divide => eval(e1) / eval(e2)
    case _ => throw new Exception(s"Unexpected expression: $exp")
    

