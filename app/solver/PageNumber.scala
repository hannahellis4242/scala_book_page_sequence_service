package solver

object PageNumber {
  /* gives back the page number that should be printed on *
   * the given page index (zero indexed) for a signature  *
   * of the given size.                                   */
  def pageNumber(sheetsInSignature: Int, pageIndex: Int): Option[Int] =
    if (pageIndex >= 4 * sheetsInSignature)
      None
    else {
      val sheet = pageIndex / 4
      pageIndex % 4 match {
        case 0 => Some(4 * sheetsInSignature - 2 * sheet)
        case 1 | 2 => Some(pageIndex - 2 * sheet)
        case 3 => Some(4 * sheetsInSignature - 2 * sheet - 1)
      }
    }
}
