package controllers

import play.api.libs.json.Json
import play.api.mvc._
import service.Service

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
      .map(x=>Service.solveAndSave(x).map((s)=>Ok(Json.toJson(s))))
      .getOrElse(Future{BadRequest("")})
}

  def get(key: String): Action[AnyContent] = Action.async {
    Service.readSolution(key)
      .map(_
        .map(value=>Ok(Json.toJson(value)))
        .getOrElse(NotFound(""))
      )
  }
}

package controllers

import play.api._
import libs.json.Json
import mvc._
import service.Service.{readSolution, solve}

import java.util.UUID
import javax.inject.Inject

class SequenceController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def post(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    request
      .body
      .asJson
      .map(_.validate[List[Int]])
      .flatMap(_.asOpt)
      .map(x=>solve(x))
      .map(x=>Ok(Json.toJson(x)))
      .getOrElse(BadRequest(Json.toJson("")))
  }

  def get(key: UUID): Action[AnyContent] = Action { implicit request:Request[AnyContent] =>
    readSolution(key)
      .map(x=>Ok(Json.toJson(x)))
      .getOrElse(NotFound(""))
  }
}
