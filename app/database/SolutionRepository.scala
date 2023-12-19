package database

import model.Solution
import org.bson.types.ObjectId
import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.{ExecutionContext, Future}

object SolutionRepository {
  private val url = "mongodb://localhost:27017"
  private val dbName = "solutions"
  private val collectionName = "sequence"

  def create(solution: Seq[Int]): Future[ObjectId] = {
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collectionName)
      .insertOne(Solution(solution).toDocument)
      .map(x => x.getInsertedId.asObjectId().getValue)
      .head()
  }

  def find(key: String)(implicit context: ExecutionContext): Future[Option[Array[Int]]] = {
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collectionName)
      .find(equal("_id", BsonObjectId(key)))
      .map(_
        .get("solution")
        .map(_
          .asArray()
          .toArray
          .map(_.asInstanceOf[Int])
        )
      )
      .head()
  }
}
