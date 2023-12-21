package solver

import org.scalatest
import test.FlatTest

import scala.sys.exit

class SolverTest extends FlatTest{
  private def testSequence(): Unit = {
    case class Params(input: Seq[Int], expected: Seq[Int])
    val params = List(
      Params(List[Int](), Vector[Int]()),
      Params(List(1), Vector(4, 1, 2, 3)),
      Params(List(2), Vector(8, 1, 2, 7, 6, 3, 4, 5)),
      Params(List(1, 1), Vector(4, 1, 2, 3, 8, 5, 6, 7)),
      Params(List(2, 1), Vector(8, 1, 2, 7, 6, 3, 4, 5, 12, 9, 10, 11)))
    params.foreach(params => runTest[Params]("sequence test",
      params,
      (p: Params) => Solver.sequence(p.input) should be(p.expected)))
  }

  private def testSeparated(): Unit = {
    case class Params(input: Seq[Int], expected: Seq[Seq[Int]])
    val params = List(
      Params(List[Int](), Vector[Vector[Int]]()),
      Params(List(1), Vector(Vector(4, 1, 2, 3))),
      Params(List(2), Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5))),
      Params(List(1, 1), Vector(Vector(4, 1, 2, 3), Vector(8, 5, 6, 7))),
      Params(List(2, 1), Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5), Vector(12, 9, 10, 11))))
    params.foreach(params => runTest[Params]("sequence test",
      params,
      (p: Params) => Solver.separated(p.input) should be(p.expected)))
  }

  override protected def runAll():Unit = {
    testSequence()
    testSeparated()
  }
}
