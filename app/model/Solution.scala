package model

import org.mongodb.scala.bson.Document

case class Solution(val solution:Seq[Int]){
  def toDocument:Document= Document("solution"->solution)
}
