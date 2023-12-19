package solver

object Solver {
  def pageNumber(size: Int, index: Int): Option[Int] =
    if (index >= 4*size)
      None
    else {
      val sheet = index / 4
      index % 4 match {
    case 0 => Some(4 * size - 2*sheet)
    case 1 | 2 => Some(index - 2*sheet)
    case 3 => Some(4*size -2*sheet-1 )
  }
    }
  /*{
      if (index >= 4 * size) {
        None
      } else {
        val sheet = index / 4
        Some(size % 4 match {
          case 0 => 4 * size - sheet
          case 1 => index - 2 * sheet
          case 2 => index - 2 * sheet
          case 3 => 4 * size - sheet - 1
        })
      }
    }*/

  def sequence(x: Seq[Int]): Seq[Int] = {
    //TODO
    Vector[Int]()
  }
}
