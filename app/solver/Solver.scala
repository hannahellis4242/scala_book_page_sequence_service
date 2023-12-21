package solver

import model._
import solver.PageNumber.pageNumber
import utils.Utils.swap

object Solver {
  private def calculatePages(x: Seq[Int]): Seq[Seq[Int]] =
    swap(x
      .map(size => swap(Range(0, 4 * size)
        .map(index => pageNumber(size, index)))))
      .getOrElse(Vector())

  private def sequence(x: Seq[Int]): Seq[Int] = separated(x).flatten

  private def separated(x: Seq[Int]): Seq[Seq[Int]] =
    calculatePages(x)
      .foldLeft[(Int, Seq[Seq[Int]])]((0, Vector()))((acc, x) =>
        (acc._1 + x.length, acc._2 :+ x.map(_ + acc._1))
      )._2

  def solve(problem: Problem): Solution = problem match {
    case SeparatedProblem(signatureSizes) => SeparatedSolution(separated(signatureSizes))
    case SequenceProblem(signatureSizes) => SequenceSolution(sequence(signatureSizes))
  }
}
