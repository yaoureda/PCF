package pcf

import parserANTLR.AbstractParser.analyze
import evaluator.Evaluator.eval

import java.io.{FileInputStream, InputStream}

object Pcf:
  def main(args: Array[String]): Unit =
    val in: InputStream =
      if args.isEmpty then
        System.in
      else
        FileInputStream(args(0))
    val exp = analyze(in)
    val typ = typer.Typer.eval(exp, Map())
    println(s"==> ${eval(exp, Map())}:$typ")


