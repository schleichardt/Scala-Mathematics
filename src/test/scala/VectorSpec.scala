import info.schleichardt.math.MathVector
import org.specs._
import org.specs.runner._
import org.junit.runner.RunWith
import info.schleichardt.math.MathVectorScalar._

@RunWith(classOf[JUnitSuiteRunner])
class VectorSpec extends Specification with JUnit with ScalaTest {
  "vectors" can {
    "be added to other vectors" in {
      MathVector(1,2,3) + MathVector(5, 5, 5) must_== MathVector(6, 7, 8)
    }
    "subtracted from other vectors" in {
      MathVector(6, 7, 8) - MathVector(5, 5, 5) must_== MathVector(1,2,3)
    }
    "be multiplied with others (dot product)" in {
      MathVector(6, 7, 8) * MathVector(5, 5, 5) must_== 6 * 5 + 7 * 5 + 8 * 5
    }
    "be multiplied with others (cross product)" in {
      MathVector(1, -5, 2) x MathVector(2, 0, 3) must_== MathVector(-15, 1, 10)
    }
    "be multiplied with a number (vector on left side)" in {
      MathVector(1, -5, 2) * 2 must_== MathVector(2, -10, 4)
    }
    "be multiplied with a number (vector on right side)" in {
      2 * MathVector(1, -5, 2) must_== MathVector(2, -10, 4)
    }
  }
}