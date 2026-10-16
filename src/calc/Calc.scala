package calc

import parser.AbstractParser.analyse
import evaluator.Evaluator.eval

import java.io.{FileInputStream, InputStream}

object Calc:
  def main(args: Array[String]): Unit =
    val in: InputStream =
      if args.isEmpty then
        System.in
      else
        FileInputStream(args(0))
    val exp = analyse(in)
    println(s"==> ${eval(exp)}")


