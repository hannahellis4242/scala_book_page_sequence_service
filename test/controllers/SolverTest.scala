package controllers

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import solver.Solver

import scala.sys.exit
//case class TestParams(input: Seq[Int], expected: Seq[Int]);

class SolverTest extends AnyFlatSpec with Matchers {
  def testPageNumbers(): Unit = {
    case class Params(size: Int, index: Int, expected: Option[Int])
    print(Console.GREEN)
    val params = List(
      Params(1, 50, None),
      Params(1, 0, Some(4)),
      Params(2, 0, Some(8)),
      Params(3, 0, Some(12)),
      Params(1, 1, Some(1)),
      Params(2, 1, Some(1)),
      Params(3, 1, Some(1)),
      Params(1, 2, Some(2)),
      Params(2, 2, Some(2)),
      Params(1, 3, Some(3)),
      Params(2, 3, Some(7)),
      Params(3, 3, Some(11)),
      Params(1, 4, None),
      Params(2, 4, Some(6)),
      Params(3, 4, Some(10)),
      Params(4, 4, Some(14)),
      Params(1, 5, None),
      Params(2, 5, Some(3)),
      Params(3, 5, Some(3)),
      Params(1, 6, None),
      Params(2, 6, Some(4)),
      Params(1, 7, None),
      Params(2, 7, Some(5)),
      Params(3, 7, Some(9)),
      Params(3, 8, Some(8)),
      Params(3, 9, Some(5)),
      Params(3, 10, Some(6)),
      Params(3, 11, Some(7)),
      Params(3, 12, None),
      Params(4, 0, Some(16)),
      Params(5, 0, Some(20)),
      Params(4, 3, Some(15)),
      Params(8, 3, Some(31)),
      Params(5, 4, Some(18)),
      Params(6, 4, Some(22)),
      Params(4, 7, Some(13)),
      Params(5, 8, Some(16)),
      Params(4, 9, Some(5)),
      Params(4, 12, Some(10)),
      Params(8, 12, Some(26)),
      Params(5, 13, Some(7)),
      Params(4, 15, Some(9)),
      Params(5, 15, Some(13)),
      Params(6, 15, Some(17)),
      Params(4, 18, None),
      Params(5, 18, Some(10)),
      Params(8, 19, Some(23)),
      Params(7, 21, Some(11)),
      Params(8, 27, Some(19)))

    params.foreach(params => {
      try {
        Solver.pageNumber(params.size, params.index) should be(params.expected)
        println("----------------------")
        println(s"\tTest Params : $params")
        println("\tPASS")
        println("======================")
      }
      catch {
        case e: Exception =>
          print(Console.RED)
          println("----------------------")
          println(s"\tTest Params : $params")
          println("\tFAILED")
          println(e.getMessage)
          println("======================")
          exit(1)

      }
    })
  }

  testPageNumbers()

  println(Console.GREEN + "*** ALL PASSED ***")
  /*
  private val params = List(TestParams(input = Vector(), expected = Vector()),
    TestParams(input = Vector(1), expected = Vector(4, 1, 2, 3)));

  params.foreach(param => Solver.sequence(param.input) should be(param.expected))*/
}
