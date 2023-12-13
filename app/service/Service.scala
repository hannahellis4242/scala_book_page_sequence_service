package service

import java.util.UUID

object Service {
  def solve(problem:Seq[Int]):UUID={
    //TODO
    UUID.randomUUID()
  }
  def saveSolution(key:UUID,solution:Seq[Int]):Unit={
//TODO
  }
  def readSolution(key:UUID):Option[Seq[Int]]={
    //TODO
    None
  }
}
