package evaluator

import ast.Exp
import ast.Exp.*
import ast.Op.*
import evaluator.Evaluator.Value.IntVal
import evaluator.Evaluator.Value.Closure

object Evaluator :
  enum Value:
    case IntVal(value: Int)
    case Closure(x: String, body: Exp, env: Env)

  given int2Val: Conversion[Int, Value] = n => IntVal(n)

  given val2Int: Conversion[Value, Int] = {
    case IntVal(n) => n
  }

  case class IceCube(x: String, t: Exp, e: Env)

  private type Env = Map[String, Value | IceCube]

  def eval(exp: Exp, e: Env = Map()): Value = exp match
    case Number(value) => value
    case App(func, arg) =>
      eval(func, e) match
        case Closure(x, body, env) =>
          val argVal = eval(arg, e)
          val newEnv = env + (x -> argVal)
          eval(body, newEnv)
        case _ => throw new Exception(s"Expected function: $func")
    case BinaryExp(op, e1, e2)  =>
      op match
        case Plus => eval(e1, e) + eval(e2, e)
        case Minus => eval(e1, e) - eval(e2, e)
        case Multiply => eval(e1, e) * eval(e2, e)
        case Divide => eval(e1, e) / eval(e2, e)
    case Fun(x, body) =>
      Closure(x, body, e)
    case IfZero(t, e1, e2) =>
      eval(t, e) match
        case IntVal(0) => eval(e1, e)
        case IntVal(_) => eval(e2, e)
        case _ => throw new Exception(s"Expected integer: $t")
    case Var(x) =>
      e.get(x) match
        case Some(v: Value) => v
        case Some(IceCube(y, t, env)) =>
          val newEnv = env + (y -> IceCube(y, t, env))
          eval(t, newEnv)
        case None => throw new Exception(s"Unbound variable: $x")
    case Let(x, e1, e2) =>
      val v1 = eval(e1, e)
      val newEnv = e + (x -> v1)
      eval(e2, newEnv)
    case Fix(f, body) =>
      val newEnv = e + (f -> IceCube(f, body, e))
      eval(body, newEnv)
    case _ => throw new Exception(s"Unexpected expression: $exp")

    

