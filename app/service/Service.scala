package service

import database.SolutionRepository
import solver.Solver.solve

import database.SolutionRepository
import solver.Solver.solve

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

object Service {
  def solveAndSave(problem: Seq[Int]): Future[String] =
    SolutionRepository.create(solve(problem)).map(_.toString)

  def readSolution(key: String)(implicit context :ExecutionContext): Future[Option[Array[Int]]] =
    SolutionRepository.find(key)(context)
}
