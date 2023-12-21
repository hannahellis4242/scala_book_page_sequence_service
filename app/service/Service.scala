package service

import database.SolutionRepository
import jakarta.inject.Inject
import model._
import solver.Solver.solve

import scala.concurrent.Future

class Service @Inject()(val repository: SolutionRepository) {
  def solveAndSave(problem: Problem): Future[Key] = repository.create(solve(problem))

  def read(key: Key): Future[Option[Solution]] = repository.find(key)
}
