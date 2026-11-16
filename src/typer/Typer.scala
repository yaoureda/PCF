package typer

import ast.Exp
import ast.Exp.*
import Exp.*
import ast.Op.*
import evaluator.Evaluator.Value.Closure
import evaluator.Evaluator.eval
import unify.TVar

object Typer:
  type Env=Map[String, Type]
  def eval(exp:Exp, e:Env): Type = exp match
    case Number(_) => INT
    case App(func, arg) =>
      val A = eval(func, e)
      val B = eval(arg, e)
      val C = TVar()
      if A === FUNCTION(B, C) then C
      else throw TypeError(exp.toString)
    case BinaryExp(op, e1, e2)  =>
      val A = eval(e1, e)
      val B = eval(e2, e)
      if A === INT && B === INT then INT
      else throw TypeError(exp.toString)
    case Fun(x, body) =>
      val A = TVar()
      val newEnv = e + (x -> A)
      val B = eval(body, newEnv)
      FUNCTION(A, B)
    case IfZero(t, e1, e2)=>
      val A = eval(t, e)
      val B = eval(e1, e)
      val C = eval(e2, e)
      if A === INT && B === C then B
      else throw TypeError(exp.toString)
    case Var(x) =>
      e.getOrElse(x, throw TypeError(s"Unbound variable: $x"))
    case Let(x, e1, e2) =>
      val A = eval(e1, e)
      val newEnv = e + (x -> A)
      val B = eval(e2, newEnv)
      B
    case Fix(f, body) =>
      val A = TVar()
      val newEnv = e + (f -> A)
      val B = eval(body, newEnv)
      if A === B then B
      else throw TypeError(exp.toString)
    case _ => ???  
