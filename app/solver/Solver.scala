package solver

import model._
import solver.PageNumber.pageNumber
import utils.Utils.swap

object Solver {
  private def calculatePages(signatureSizes: Seq[Int]): Seq[Seq[Int]] =
    swap(signatureSizes
      .map(sheetsInSignature =>
        swap(Range(0, 4 * sheetsInSignature)
          .map(pageIndex => pageNumber(sheetsInSignature, pageIndex)))))
      .getOrElse(Vector())

  private def sequence(x: Seq[Int]): Seq[Int] = separated(x).flatten

  private def separated(x: Seq[Int]): Seq[Seq[Int]] =
    calculatePages(x)
      .foldLeft[(Int, Seq[Seq[Int]])]((0, Vector()))((acc, signaturePageNumbers) =>
        (acc._1 + signaturePageNumbers.length, acc._2 :+ signaturePageNumbers.map(_ + acc._1))
      )._2

  def solve(problem: Problem): Solution = problem match {
    case SeparatedProblem(signatureSizes) => SeparatedSolution(separated(signatureSizes))
    case SequenceProblem(signatureSizes) => SequenceSolution(sequence(signatureSizes))
  }
}
