package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import solver.Solver.solve

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
