package solver

import solver.PageNumber.pageNumber
import utils.Utils.swap

object Solver {
  private def calculatePages(x: Seq[Int]): Seq[Seq[Int]] =
    swap(x
      .map(size => swap(Range(0, 4 * size)
        .map(index => pageNumber(size, index)))))
      .getOrElse(Vector())

  def sequence(x: Seq[Int]): Seq[Int] = separated(x).flatten

  def separated(x: Seq[Int]): Seq[Seq[Int]] =
    calculatePages(x)
      .foldLeft[(Int, Seq[Seq[Int]])]((0, Vector()))((acc, x) =>
        (acc._1 + x.length, acc._2 :+ x.map(_ + acc._1))
      )._2
}
