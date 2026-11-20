package pcf

import ast.Exp
import evaluator.Evaluator
import parserANTLR.AbstractParser.analyze
import evaluator.Evaluator.eval
import gen.Generator
import parserANTLR.AbstractParser
import typer.{Type, Typer}
import vm.Code
import vm.VM

import java.io.{FileInputStream, InputStream}

object Pcf:
  def main(args: Array[String]): Unit =
    val (in, filename) =
      if args.length == 0 || args(0).charAt(0) == '-'
      then (System.in, None)
      else (new FileInputStream(args(0)), Some(args(0)))
    val verbose = args.length == 0 || args.length > 1 && args(1) == "-v"
    if (args.contains("-i")) println(s"==> ${interpret(verbose, in)}")
    else
      compile(verbose, in)

  def analyze(verbose: Boolean, in: InputStream): (Exp, Type) =
    val term = AbstractParser.analyze(verbose, in)
    (term, Typer.eval(term, Map()))

  def interpret(verbose: Boolean, in: InputStream): String =
    val (term, a) = analyze(verbose, in)
    val value = Evaluator.eval(term, Map())
    s"$value:$a"

  def compile(verbose: Boolean, in: InputStream): Unit =
    val (term, _) = analyze(verbose, in)
    val code = Generator.gen(term)
    if verbose then println(s"Code: $code")
    if !check(verbose, term, code) then throw Exception("Implementation Error")

    def check(verbose: Boolean, term: Exp, code: Code): Boolean =
      val value = Evaluator.eval(term, Map())
      if verbose then println(s"==> $value")
      val value2 = VM.execute(code)
      if verbose then println(s"VM> $value2")
      value2.toString == value.toString // valid only for integer results


