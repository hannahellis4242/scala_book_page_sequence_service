package solver

import model._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import solver.Solver.solve

class SolverTest extends AnyFunSuite with Matchers {
  case class TestParameters(problem: Problem, expected: Solution)

  Vector(
    TestParameters(SequenceProblem(List[Int]()), SequenceSolution(Vector[Int]())),
    TestParameters(SequenceProblem(List(1)), SequenceSolution(Vector(4, 1, 2, 3))),
    TestParameters(SequenceProblem(List(2)), SequenceSolution(Vector(8, 1, 2, 7, 6, 3, 4, 5))),
    TestParameters(SequenceProblem(List(1, 1)), SequenceSolution(Vector(4, 1, 2, 3, 8, 5, 6, 7))),
    TestParameters(SequenceProblem(List(2, 1)), SequenceSolution(Vector(8, 1, 2, 7, 6, 3, 4, 5, 12, 9, 10, 11))),
    TestParameters(SeparatedProblem(List[Int]()), SeparatedSolution(Vector[Vector[Int]]())),
    TestParameters(SeparatedProblem(List(1)), SeparatedSolution(Vector(Vector(4, 1, 2, 3)))),
    TestParameters(SeparatedProblem(List(2)), SeparatedSolution(Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5)))),
    TestParameters(SeparatedProblem(List(1, 1)), SeparatedSolution(Vector(Vector(4, 1, 2, 3), Vector(8, 5, 6, 7)))),
    TestParameters(SeparatedProblem(List(2, 1)), SeparatedSolution(Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5), Vector(12, 9, 10, 11))))
  )
    .foreach(params => test(s"Test Params : $params") {
      solve(params.problem) should be(params.expected)
    })
}
