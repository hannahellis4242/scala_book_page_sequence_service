package database

import model.{Key, Solution}

import scala.concurrent.Future

trait SolutionRepository {
  def create(solution: Solution): Future[Key]

  def find(key: Key): Future[Option[Solution]]
}
