package service

import solver.Solver

import java.util.UUID

object Service {
  def solve(problem:Seq[Int]):UUID={
    val key = UUID.randomUUID()
    val solution = Solver.solve(problem)
    key
  }
  def saveSolution(key:UUID,solution:Seq[Int]):Unit={
//TODO
  }
  def readSolution(key:UUID):Option[Seq[Int]]={
    //TODO
    None
  }
}
