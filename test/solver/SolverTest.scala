package solver

import model.{Problem, SeparatedProblem, SeparatedSolution, SequenceProblem, SequenceSolution, Solution}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import solver.Solver.solve

class SolverTest extends AnyFunSuite with Matchers{
  case class Params(input: Problem, expected:Solution)
 Vector(
    Params(SequenceProblem(List[Int]()), SequenceSolution(Vector[Int]())),
    Params(SequenceProblem(List(1)), SequenceSolution(Vector(4, 1, 2, 3))),
    Params(SequenceProblem(List(2)), SequenceSolution(Vector(8, 1, 2, 7, 6, 3, 4, 5))),
    Params(SequenceProblem(List(1, 1)), SequenceSolution(Vector(4, 1, 2, 3, 8, 5, 6, 7))),
    Params(SequenceProblem(List(2, 1)), SequenceSolution(Vector(8, 1, 2, 7, 6, 3, 4, 5, 12, 9, 10, 11))),
    Params(SeparatedProblem(List[Int]()), SeparatedSolution(Vector[Vector[Int]]())),
    Params(SeparatedProblem(List(1)), SeparatedSolution(Vector(Vector(4, 1, 2, 3)))),
    Params(SeparatedProblem(List(2)), SeparatedSolution(Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5)))),
    Params(SeparatedProblem(List(1, 1)), SeparatedSolution(Vector(Vector(4, 1, 2, 3), Vector(8, 5, 6, 7)))),
    Params(SeparatedProblem(List(2, 1)), SeparatedSolution(Vector(Vector(8, 1, 2, 7, 6, 3, 4, 5), Vector(12, 9, 10, 11))))
    )
   .foreach(params => test(s"Test Params : $params") {
    solve(params.input) should be(params.expected)
  })
}
