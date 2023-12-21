package database
import com.redis.RedisClient
import model.{Key, SeparatedKey, SeparatedSolution, SequenceKey, SequenceSolution, Solution}
import play.api.libs.json.{JsError, JsSuccess, Json}

import java.util.UUID
import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}

class SolutionRepositoryRedis(implicit context:ExecutionContext) extends SolutionRepository {
  private val host = "localhost"
  private val port = 6379
  override def create(solution: Solution): Future[Key] = {
    val key = UUID.randomUUID().toString
    val client = new RedisClient(host, port)
    solution match {
      case SequenceSolution(value) => Future {
        client.set(key, Json.toJson(value) , expire = Duration(5,"minutes"))
        SequenceKey(key)
      }
      case SeparatedSolution(value) => Future {
        client.set(key,Json.toJson(value),expire = Duration(5,"minutes"))
        SeparatedKey(key)
      }
    }
  }

  override def find(key: Key): Future[Option[Solution]] = {
    val client = new RedisClient(host,port)
      key match {
        case SequenceKey(value) => Future {
          client.get(value)
            .map(Json.parse)
            .map(Json.fromJson[Seq[Int]](_))
            .flatMap(result => result match {
              case JsSuccess(value,_) => Some(value)
              case JsError(_) => None
            })
            .map(SequenceSolution)
        }
        case SeparatedKey(value) => Future {
          client.get(value)
            .map(Json.parse)
            .map(Json.fromJson[Seq[Seq[Int]]](_))
            .flatMap(result => result match {
              case JsSuccess(value, _) => Some(value)
              case JsError(_) => None
            })
            .map(SeparatedSolution)
        }
      }
    }
}
