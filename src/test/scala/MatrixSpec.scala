import info.schleichardt.math.{TwoRowMatrix, ValueMatrix}
import org.junit.runner.RunWith
import org.specs.runner.{ScalaTest, JUnit, JUnitSuiteRunner}
import org.specs.Specification

@RunWith(classOf[JUnitSuiteRunner])
class MatrixSpec extends Specification with JUnit with ScalaTest {
  "matrices" can {
    "be added" in {
      val first = ValueMatrix(Seq(1.0, -3, 2), Seq(1.0, 2, 7))
      val second = ValueMatrix(Seq(0., 3, 5), Seq(2., 1, -1))
      first + second must_== ValueMatrix(Seq(1., 0, 7), Seq(3., 3, 6))
    }
    "be subtrahated" in {
      val first = ValueMatrix(Seq(2., 2, 2), Seq(2., 2, 2))
      val second = ValueMatrix(Seq(1., 1, 1), Seq(1., 1, 1))
      first - second must_== second
    }
    "be transposed" in {
      val matrix = ValueMatrix(Seq(1., -3, 2), Seq(1., 2, 7))
      val transposedMatrix = matrix.transpose
      transposedMatrix must_== ValueMatrix(Seq(1., 1), Seq(-3., 2), Seq(2., 7))
    }
    "be null matrices" in {
      val nullMatrix = ValueMatrix(Seq(0., 0, 0), Seq(0., 0, 0), Seq(0., 0, 0))
      val notNullMatrix = ValueMatrix(Seq(1., 0, 2), Seq(1., 2, 7))
      nullMatrix.isNullMatrix must be_==(true)
      notNullMatrix.isNullMatrix must be_==(false)
    }
    "be multiplied with each other" in {
      val left = ValueMatrix(Seq(0., 1, 2), Seq(4., 2, 3), Seq(5., 3, 1))
      val right = ValueMatrix(Seq(3., 1, 1), Seq(1., 3, 1), Seq(4., 0, 2))
      left * right must be_==(ValueMatrix(Seq(9., 3, 5), Seq(26., 10, 12), Seq(22., 14, 10)))
    }
    "be multiplied with a number" in {
      ValueMatrix(Seq(0., 1, 2), Seq(-4., 2, 3), Seq(5., 3, 1)) * 2.0 must be_==(ValueMatrix(Seq(0., 2, 4), Seq(-8., 4., 6), Seq(10., 6, 2)))
    }
    "only fitting matrices can be multiplied" in {
      val matrixWith3Rows = ValueMatrix(Seq(0., 1, 2), Seq(4., 2, 3), Seq(5., 3, 1))//3 rows, 3 columns
      val matrixWith2Rows = ValueMatrix(Seq(3., 1, 1), Seq(1., 3, 1))//2 rows, 3 columns
      matrixWith3Rows * matrixWith2Rows must throwA[IllegalArgumentException]
      matrixWith2Rows * matrixWith3Rows must not(throwA[IllegalArgumentException])
    }
    "can be a identity matrix" in {
      val identityMatrix = ValueMatrix(Seq(1., 0, 0), Seq(0., 1, 0), Seq(0., 0, 1))
      val notIdentityMatrix = ValueMatrix(Seq(1., 0, 0), Seq(0., 1, 0), Seq(0., 1, 0))
      identityMatrix.isIdentityMatrix must be_==(true)
      notIdentityMatrix.isIdentityMatrix must be_==(false)
    }
    "have a determinant" in {
      ValueMatrix(Seq(0., 1, 2), Seq(3., 2, 1), Seq(1., 1, 0)).determinant must be_==(3.0)
      ValueMatrix(Seq(1., 2), Seq(4., 3)).determinant must be_==(-5.0)
    }
    "have a main diagonal sum if square matrix" in {
      ValueMatrix(Seq(1., 1, 2), Seq(3., 50, 1), Seq(1., 0, 25)).trace must be_==(1.0+50+25)
    }
    "can have sometimes an inverse matrix" in {
      val matrix = ValueMatrix(Seq(3.,1,5), Seq(3.,3,1), Seq(4.,6,4))
//      val matrix = ValueMatrix(Seq(-3, 3, 5), Seq(-8, 11, 8), Seq(6, -9, -4))
      matrix.inverse must be_==(ValueMatrix(Seq(0.15, 0.65, -0.35), Seq(-0.20, -0.20, 0.30), Seq(0.15, -0.35, 0.15)))
    }
    "can be initialized without Seq" in {
      ValueMatrix(Seq(3., 1, 5), Seq(3., 3, 1), Seq(4., 6, 4)) must be_==(ValueMatrix(3.0, 1, 5)(3, 3, 1)(4, 6, 4))
      ValueMatrix(Seq(3., 1), Seq(3., 3)) must be_==(TwoRowMatrix(3.0, 1)(3.0, 3))
    }
  }
}
