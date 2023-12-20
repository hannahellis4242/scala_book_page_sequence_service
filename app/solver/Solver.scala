package solver

import utils.Utils.swap

object Solver {
  def pageNumber(size: Int, index: Int): Option[Int] =
    if (index >= 4 * size)
      None
    else {
      val sheet = index / 4
      index % 4 match {
        case 0 => Some(4 * size - 2 * sheet)
        case 1 | 2 => Some(index - 2 * sheet)
        case 3 => Some(4 * size - 2 * sheet - 1)
      }
    }

  private def calculatePages(x:Seq[Int]):Seq[Seq[Int]] =
    swap(x
    .map(size => swap(Range(0, 4 * size)
      .map(index => pageNumber(size, index)))))
    .getOrElse(Vector())

  def sequence(x: Seq[Int]): Seq[Int] = separated(x).flatten
  def separated(x:Seq[Int]):Seq[Seq[Int]] =
    calculatePages(x)
    .foldLeft[(Int, Seq[Seq[Int]])]((0, Vector()))((acc, x) => {
      val end = x.map(_ + acc._1)
      (acc._1 + x.length, acc._2:+end)
    })._2
}
