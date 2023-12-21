package controllers

import model.{SequenceKey, SequenceProblem, SequenceSolution}
import play.api.libs.json.Json
import play.api.mvc._
import service.Service

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class SequenceController @Inject()(val controllerComponents: ControllerComponents,
                                   implicit val service: Service,
                                   implicit val myExecutionContext: ExecutionContext) extends BaseController {
  def post(): Action[AnyContent] = Action.async { request: Request[AnyContent] =>
    request
      .body
      .asJson
      .map(_.validate[List[Int]])
      .flatMap(_.asOpt)
      .map(x => service.solveAndSave(SequenceProblem(x)).map((s) => Ok(Json.toJson(s.value))))
      .getOrElse(Future {
        BadRequest("")
      })
  }

  def get(key: String): Action[AnyContent] = Action.async {
    service.read(SequenceKey(key))
      .map(opt => opt.map {
        case SequenceSolution(value) => value
      })
      .map(_
        .map(value => Ok(Json.toJson(value)))
        .getOrElse(NotFound(""))
      )
  }
}
