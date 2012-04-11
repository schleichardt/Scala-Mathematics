import info.schleichardt.math.Angle
import info.schleichardt.math.MathVector._
import org.junit.runner.RunWith
import org.specs.runner.{ScalaTest, JUnit, JUnitSuiteRunner}
import org.specs.Specification

@RunWith(classOf[JUnitSuiteRunner])
class AngleSpec extends Specification with JUnit with ScalaTest {
  "get radiant" in {
    Angle.fromDegree(0).radiant must_== 0
    Angle.fromDegree(180).radiant must_== math.Pi
    Angle.fromDegree(360).radiant must_== math.Pi * 2
    Angle.fromRadiant(0).radiant must_== 0
    Angle.fromRadiant(math.Pi).radiant must_== math.Pi
    Angle.fromRadiant(math.Pi * 2).radiant must_== math.Pi * 2
  }
}