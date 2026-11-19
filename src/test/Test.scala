import pcf.Pcf.main

@main
def test(): Unit =
  test("test/test0.pcf") // Expect: 14 : INT
  test("test/test1.pcf") // Expect: 10 : INT
  test("test/test2.pcf") // Expect: 8 : INT
  test("test/test3.pcf") // Expect: 12 : INT
  test("test/test4.pcf") // Expect: Closure : (T -> T)
  test("test/test5.pcf") // Expect: Closure : (INT -> INT)
  test("test/test6.pcf") // Expect: 15 : INT
  test("test/test7.pcf") // Expect: 5 : INT
  test("test/test8.pcf") // Expect: TypeError
  test("test/test9.pcf") // Expect: TypeError

def test(file: String): Unit =
  println(s"\n===== $file =====")
  val args = Array(file)
  try
    main(args)
  catch
    case e: typer.TypeError =>
      println(s"TypeError: ${e.getMessage}")


