package gen

import ast.Exp
import Exp.*
import vm.Code
import vm.Ins.*

object Generator:
  def gen(term: Exp): Code = term match
    case Number(n) => List(Ldi(n))
    case _ => ???
