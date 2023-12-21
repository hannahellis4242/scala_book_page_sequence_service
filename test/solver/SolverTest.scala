package solver

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SolverTest extends AnyFunSuite with Matchers{
  //sequence
  {
    case class Params(input: Seq[Int], expected: Seq[Int])
    val params = List(
      Params(List[Int](), Vector[Int]()),
      Params(List(1), Vector(4, 1, 2, 3)),
      Params(List(2), Vector(8, 1, 2, 7, 6, 3, 4, 5)),
      Params(List(1, 1), Vector(4, 1, 2, 3, 8, 5, 6, 7)),
      Params(List(2, 1), Vector(8, 1, 2, 7, 6, 3, 4, 5, 12, 9, 10, 11)))
    params.foreach(params => test(s"sequence test with Test Params : $params")
    {Solver.sequence(params.input) should be(params.expected)})
  }

  //separated
  {
    case class Params(input: Seq[Int], expected: Seq[Seq[Int]])
    val params = List(
      Params(List[Int](), Vector[Vector[Int]]()),
      Params(List(1), Vector(Vector(4, 1, 2, 3))),
      Params(List(2), Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5))),
      Params(List(1, 1), Vector(Vector(4, 1, 2, 3), Vector(8, 5, 6, 7))),
      Params(List(2, 1), Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5), Vector(12, 9, 10, 11))))
    params.foreach(params => test(s"separated test with Test Params : $params"){
      Solver.separated(params.input) should be(params.expected)})
  }
}
