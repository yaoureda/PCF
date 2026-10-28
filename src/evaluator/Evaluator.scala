package evaluator

import ast.Exp
import ast.Exp.*
import ast.Op.*

object Evaluator :
  private type Env = Map[String, Int]
  def eval(exp: Exp, e: Env = Map()): Int = exp match
    case Number(value) => value
    case BinaryExp(op, e1, e2)  =>
      op match
        case Plus => eval(e1, e) + eval(e2, e)
        case Minus => eval(e1, e) - eval(e2, e)
        case Multiply => eval(e1, e) * eval(e2, e)
        case Divide => eval(e1, e) / eval(e2, e)
    case IfZero(t, e1, e2) =>
      if eval(t, e) == 0 then eval(e1, e) else eval(e2, e)
    case Var(x) =>
      e.getOrElse(x, throw new Exception(s"Variable $x not found in environment"))
    case Let(x, e1, e2) =>
      val v1 = eval(e1, e)
      val newEnv = e + (x -> v1)
      eval(e2, newEnv)
    case _ => throw new Exception(s"Unexpected expression: $exp")

    

