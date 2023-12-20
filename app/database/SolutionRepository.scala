package database

import model._
import org.bson.types.ObjectId
import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.Future

object SolutionRepository {
  private val url = "mongodb://localhost:27017"
  private val dbName = "solutions"
  private val sequenceCollection = "sequence"
  private val separatedCollection = "separated"

  private def collection(solution: Solution) = solution match {
    case SequenceSolution(_) => sequenceCollection
    case SeparatedSolution(_) => separatedCollection
  }

  private def collection(key: Key) = key match {
    case SequenceKey(_) => sequenceCollection
    case SeparatedKey(_) => separatedCollection
  }

  def create(solution: Solution): Future[ObjectId] =
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collection(solution))
      .insertOne(solution.toBson)
      .map(x => x.getInsertedId.asObjectId().getValue)
      .head()

  private def findByKey(key: Key) = {
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collection(key))
      .find(equal("_id", BsonObjectId(key.value)))
  }

  def find(key: SequenceKey): Future[Option[Array[Int]]] =
    findByKey(key).map(_
        .get("solution")
        .map(_
          .asArray()
          .toArray
          .map(_.asInstanceOf[Int])
        )
      )
      .head()

  def find(key: SeparatedKey): Future[Option[Array[Array[Int]]]] =
    findByKey(key)
      .map(_.get("solution")
        .map(_.asArray()
          .toArray
          .map(_.asInstanceOf[Array[Int]])
        )
      )
      .head()
}
