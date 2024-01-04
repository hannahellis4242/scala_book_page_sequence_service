package solver

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import solver.PageNumber.pageNumber

class PageNumbersTest extends AnyFunSuite with Matchers {
  case class TestParameters(sheetsInSignature: Int,
                            pageIndex: Int,
                            expectedPageNumber: Option[Int])

  private val params = List(
    TestParameters(1, 50, None),
    TestParameters(1, 0, Some(4)),
    TestParameters(2, 0, Some(8)),
    TestParameters(3, 0, Some(12)),
    TestParameters(1, 1, Some(1)),
    TestParameters(2, 1, Some(1)),
    TestParameters(3, 1, Some(1)),
    TestParameters(1, 2, Some(2)),
    TestParameters(2, 2, Some(2)),
    TestParameters(1, 3, Some(3)),
    TestParameters(2, 3, Some(7)),
    TestParameters(3, 3, Some(11)),
    TestParameters(1, 4, None),
    TestParameters(2, 4, Some(6)),
    TestParameters(3, 4, Some(10)),
    TestParameters(4, 4, Some(14)),
    TestParameters(1, 5, None),
    TestParameters(2, 5, Some(3)),
    TestParameters(3, 5, Some(3)),
    TestParameters(1, 6, None),
    TestParameters(2, 6, Some(4)),
    TestParameters(1, 7, None),
    TestParameters(2, 7, Some(5)),
    TestParameters(3, 7, Some(9)),
    TestParameters(3, 8, Some(8)),
    TestParameters(3, 9, Some(5)),
    TestParameters(3, 10, Some(6)),
    TestParameters(3, 11, Some(7)),
    TestParameters(3, 12, None),
    TestParameters(4, 0, Some(16)),
    TestParameters(5, 0, Some(20)),
    TestParameters(4, 3, Some(15)),
    TestParameters(8, 3, Some(31)),
    TestParameters(5, 4, Some(18)),
    TestParameters(6, 4, Some(22)),
    TestParameters(4, 7, Some(13)),
    TestParameters(5, 8, Some(16)),
    TestParameters(4, 9, Some(5)),
    TestParameters(4, 12, Some(10)),
    TestParameters(8, 12, Some(26)),
    TestParameters(5, 13, Some(7)),
    TestParameters(4, 15, Some(9)),
    TestParameters(5, 15, Some(13)),
    TestParameters(6, 15, Some(17)),
    TestParameters(4, 18, None),
    TestParameters(5, 18, Some(10)),
    TestParameters(8, 19, Some(23)),
    TestParameters(7, 21, Some(11)),
    TestParameters(8, 27, Some(19)))

  params.foreach { params =>
    test(s"Test Params : $params") {
      (p: TestParameters) => pageNumber(p.sheetsInSignature, p.pageIndex) should be(p.expectedPageNumber)
    }
  }
}