package database

import model._
import org.mongodb.scala.bson.{BsonArray, BsonDocument, BsonInt64, BsonObjectId, BsonValue}
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoClient}

import scala.concurrent.Future

object SolutionRepositoryMongo extends SolutionRepository {
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

  private def toBson(x: Any): BsonValue = x match {
    case x: Int => BsonInt64(x)
    case x: Seq[Any] => x.foldLeft[BsonArray](BsonArray())((acc, x) => {
      acc.add(toBson(x))
      acc
    })
  }

  private def toDocument(solution: Solution) = solution match {
    case SequenceSolution(value) => BsonDocument()
      .append("solution", toBson(value))
    case SeparatedSolution(value) => BsonDocument()
      .append("solution", toBson(value))
  }

  def create(solution: Solution): Future[Key] =
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collection(solution))
      .insertOne(toDocument(solution))
      .map(x => x.getInsertedId.asObjectId().getValue)
      .map(id => solution match {
        case SequenceSolution(_) => SequenceKey(id.toString)
        case SeparatedSolution(_) => SeparatedKey(id.toString)
      })
      .head()

  private def findByKey(key: Key) = {
    MongoClient(url)
      .getDatabase(dbName)
      .getCollection(collection(key))
      .find(equal("_id", BsonObjectId(key.value)))
  }

  private def toSequenceSolution(doc: Document): Option[SequenceSolution] =
    doc.get("solution")
      .map(_.asArray().toArray.map(_.asInstanceOf[Int])
      ).map(SequenceSolution(_))

  private def toSeparatedSolution(doc: Document): Option[SeparatedSolution] =
    doc.get("solution")
      .map(_.asArray().toArray.map(_.asInstanceOf[Seq[Int]])
      ).map(SeparatedSolution(_))

  def find(key: Key): Future[Option[Solution]] = findByKey(key).map(doc => key match {
    case SequenceKey(_) => toSequenceSolution(doc)
    case SeparatedKey(_) => toSeparatedSolution(doc)
  }).head()
}
