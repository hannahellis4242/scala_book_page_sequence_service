package model

import org.mongodb.scala.bson.BsonDocument
import utils.Utils

case class SeparatedSolution(solution: Seq[Seq[Int]]) extends Solution {
  override def toBson: BsonDocument =
    BsonDocument()
      .append("solution", Utils.toBson(solution))
}
