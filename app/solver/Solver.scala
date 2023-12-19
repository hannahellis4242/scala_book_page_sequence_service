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

  def sequence(x: Seq[Int]): Seq[Int] = {
    swap(x
      .map(size=>Range(0,4*size)
        .map(index=>pageNumber(size,index)))
      .foldLeft[(Int,Vector[Option[Int]])]((0,Vector[Option[Int]]()))((acc,x)=>{
        val start = acc._1
        val end = x.map(_.map(y=>y+start))
        (start+x.length,acc._2++end)
      })._2).getOrElse(Vector[Int]())
  }

  def separated(x:Seq[Int]):Seq[Seq[Int]]={
    //TODO
    Vector[Vector[Int]]()
  }
}
