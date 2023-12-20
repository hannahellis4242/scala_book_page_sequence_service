package model

import org.mongodb.scala.bson.BsonDocument

trait Solution {
  def toBson: BsonDocument
}
