package vm

import scala.annotation.tailrec
import Value.*
import Ins.*

type Code = List[Ins]
enum Ins:
  case Push, Add, Sub, Mul, Div
  case Ldi(n: Int)
  case Test(c1: Code, c2: Code)

enum Value:
  case IntVal(n: Int)

type Env = List[Value]
case class VMState(a: Value, s:List[Value|Env], e: Env, c: Code)

object VM:
  def execute(c: Code): Value =
    execute(IntVal(0), List(), List(), c)

  @tailrec
  def execute(a: Value, s:List[Value|Env], e: Env, c: Code): Value = (a, s, e, c) match
    case (_, _, _, List()) => a
    case (_, _, _, Push::c) => execute(a, a::s, e, c)
    case (_, _, _, Ldi(n)::c) => execute(IntVal(n), s, e, c)
    case (IntVal(n), IntVal(m)::s, _, Add::c) => execute(IntVal(m+n), s, e, c)
    case (IntVal(n), IntVal(m)::s, _, Sub::c) => execute(IntVal(m-n), s, e, c)
    case (IntVal(n), IntVal(m)::s, _, Mul::c) => execute(IntVal(m*n), s, e, c)
    case (IntVal(n), IntVal(m)::s, _, Div::c) => execute(IntVal(m/n), s, e, c)
    case (IntVal(0), _, _, Test(i, _)::c) => execute(a, s, e, i:::c)
    case (_, _, _, Test(_, j)::c) => execute(a, s, e, j:::c)
    case state => throw Exception(s"unexpected VM state $state")

@main
def test(): Unit =
  println(VM.execute(List(Ldi(1), Push, Ldi(2), Add, Test(List(Ldi(1)),List(Ldi(2))))))



