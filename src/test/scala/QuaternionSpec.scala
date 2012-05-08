import info.schleichardt.math.Quaternion
import org.junit.runner.RunWith
import org.specs.runner.{ScalaTest, JUnit, JUnitSuiteRunner}
import org.specs.Specification

@RunWith(classOf[JUnitSuiteRunner])
class QuaternionSpec extends Specification with JUnit with ScalaTest {
  "Quaternions" can {
    "be multiplied" in {
      Quaternion(1, 2, 0, 3) x Quaternion(-2, 1, 3, 4) must_== Quaternion(-16, -12, -2, 4)
    }
  }
}
