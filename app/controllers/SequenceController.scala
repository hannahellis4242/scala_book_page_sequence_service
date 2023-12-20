package controllers

import model.{SequenceKey, SequenceProblem}
import play.api.libs.json.Json
import play.api.mvc._
import service.Service.{read, solveAndSave}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class SequenceController @Inject()(val controllerComponents: ControllerComponents,
                                   implicit val myExecutionContext: ExecutionContext) extends BaseController {
  def post(): Action[AnyContent] = Action.async { request: Request[AnyContent] =>
    request
      .body
      .asJson
      .map(_.validate[List[Int]])
      .flatMap(_.asOpt)
      .map(x => solveAndSave(SequenceProblem(x)).map((s) => Ok(Json.toJson(s))))
      .getOrElse(Future {
        BadRequest("")
      })
  }

  def get(key: String): Action[AnyContent] = Action.async {
    read(SequenceKey(key))
      .map(_
        .map(value => Ok(Json.toJson(value)))
        .getOrElse(NotFound(""))
      )
  }
}
