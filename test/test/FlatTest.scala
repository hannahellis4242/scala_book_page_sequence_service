package test

import org.scalatest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.sys.exit

abstract class FlatTest extends AnyFlatSpec with Matchers {
  protected def runTest[T](name: String, params: T, fn: T => scalatest.Assertion): Unit = {
    try {
      print(Console.GREEN)
      fn(params)
      println("----------------------")
      println(s"\t$name")
      println(s"\tTest Params : $params")
      println("\tPASS")
      println("======================")
    }
    catch {
      case e: Exception =>
        print(Console.RED)
        println("----------------------")
        println(s"\t$name")
        println(s"\tTest Params : $params")
        println("\tFAILED")
        println(e.getMessage)
        println("======================")
        exit(1)

    }
  }

  protected def runAll():Unit

  runAll()
  println(Console.GREEN + "\n\n******************")
  println(Console.GREEN + "*** ALL PASSED ***")
  println(Console.GREEN + "******************")
}
