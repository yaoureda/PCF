package test

import pcf.Pcf.main

@main
def test(): Unit =
  test("test/test0.pcf")

def test(file: String): Unit =
  val args = Array(file)
  main(args)


