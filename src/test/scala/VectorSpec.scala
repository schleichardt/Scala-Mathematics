import info.schleichardt.math.MathVector
import org.specs._
import org.specs.runner._
import org.junit.runner.RunWith

@RunWith(classOf[JUnitSuiteRunner])
class VectorSpec extends Specification with JUnit with ScalaTest {
  "add Vectors" in {
    MathVector(1,2,3) + MathVector(5, 5, 5) must_== MathVector(6, 7, 8)
  }
}