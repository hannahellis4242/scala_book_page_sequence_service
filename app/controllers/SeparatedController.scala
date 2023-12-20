package controllers

import model.{SeparatedKey, SeparatedProblem}
import play.api.libs.json.Json
import play.api.mvc._
import service.Service.{read, solveAndSave}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class SeparatedController @Inject()(val controllerComponents: ControllerComponents,
                                    implicit val myExecutionContext: ExecutionContext) extends BaseController {
  def post(): Action[AnyContent] = Action.async { request: Request[AnyContent] =>
    request
      .body
      .asJson
      .map(_.validate[List[Int]])
      .flatMap(_.asOpt)
      .map(x => solveAndSave(SeparatedProblem(x)).map((s) => Ok(Json.toJson(s))))
      .getOrElse(Future {
        BadRequest("")
      })
  }

  def get(key: String): Action[AnyContent] = Action.async {
    read(SeparatedKey(key))
      .map(_
        .map(value => Ok(Json.toJson(value)))
        .getOrElse(NotFound(""))
      )
  }
}
