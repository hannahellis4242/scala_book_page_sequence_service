package service

import database.SolutionRepository
import model._
import solver.Solver.solve

import scala.concurrent.Future

class Service(val repository: SolutionRepository) {
  def solveAndSave(problem: Problem): Future[Key] = repository.create(solve(problem))

  def read(key: Key): Future[Option[Solution]] = repository.find(key)
}
