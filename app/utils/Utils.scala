package utils

object Utils {
  def swap[T](xs: Seq[Option[T]]): Option[Seq[T]] =
    if (xs.contains(None)) None
    else Some(xs.map(x => x.get))
}
