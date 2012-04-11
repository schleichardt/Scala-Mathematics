import info.schleichardt.math.ValueMatrix
import org.junit.runner.RunWith
import org.specs.runner.{ScalaTest, JUnit, JUnitSuiteRunner}
import org.specs.Specification

@RunWith(classOf[JUnitSuiteRunner])
class MatrixSpec extends Specification with JUnit with ScalaTest {
  "matrices" can {
    "be added" in {
      val first = ValueMatrix(Seq(1, -3, 2), Seq(1, 2, 7))
      val second = ValueMatrix(Seq(0, 3, 5), Seq(2, 1, -1))
      first + second must_== ValueMatrix(Seq(1, 0, 7), Seq(3, 3, 6))
    }
  }
}
