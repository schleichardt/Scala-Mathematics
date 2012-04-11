import info.schleichardt.math.MathVector
import org.specs._
import org.specs.runner._
import org.junit.runner.RunWith

@RunWith(classOf[JUnitSuiteRunner])
class VectorSpec extends Specification with JUnit with ScalaTest {
  "add vectors" in {
    MathVector(1,2,3) + MathVector(5, 5, 5) must_== MathVector(6, 7, 8)
  }
  "sub vectors" in {
    MathVector(6, 7, 8) - MathVector(5, 5, 5) must_== MathVector(1,2,3)
  }
  "dot product vectors" in {
    MathVector(6, 7, 8) * MathVector(5, 5, 5) must_== 6 * 5 + 7 * 5 + 8 * 5
  }
}