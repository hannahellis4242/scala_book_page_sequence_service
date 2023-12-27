package modules

import database.{SolutionRepository, SolutionRepositoryRedis}
import play.api.inject.Binding
import play.api.{Configuration, Environment}

import scala.concurrent.ExecutionContext.Implicits.global

class Module extends play.api.inject.Module {
  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] = {
    Seq(bind[SolutionRepository].toInstance(new SolutionRepositoryRedis("redis")))
  }
}