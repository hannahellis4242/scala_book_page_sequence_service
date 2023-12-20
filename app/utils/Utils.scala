package utils

import org.mongodb.scala.bson.{BsonArray, BsonInt64, BsonValue}

object Utils {
  def swap[T](xs: Seq[Option[T]]): Option[Seq[T]] =
    if (xs.contains(None)) None
    else Some(xs.map(x => x.get))

  def toBson(x: Any): BsonValue = x match {
    case x:Int => BsonInt64(x)
    case x:Seq[Any] =>x.foldLeft[BsonArray](BsonArray())((acc, x) => {
      acc.add(toBson(x))
      acc
    })
  }
}
