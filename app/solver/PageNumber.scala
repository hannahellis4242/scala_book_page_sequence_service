package solver

object PageNumber {
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
}
