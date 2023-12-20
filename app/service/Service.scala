package service

import database.SolutionRepository.{create, find}
import model._
import solver.Solver.{separated, sequence}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Service {
  def solveAndSave(problem: Problem): Future[String] = problem match {
    case SequenceProblem(sizes) => create(SequenceSolution(sequence(sizes))).map(_.toString)
    case SeparatedProblem(sizes) => create(SeparatedSolution(separated(sizes))).map(_.toString)
  }

  def read(key: SequenceKey): Future[Option[Array[Int]]] = find(key)

  def read(key: SeparatedKey): Future[Option[Array[Array[Int]]]] = find(key)
}
