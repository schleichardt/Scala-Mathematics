package info.schleichardt.math

object VectorsMain {
  def main(args: Array[String]) {
    val dT = new DTMatrix().toConcreteMatrix(Angle.fromDegree(10), Angle.fromDegree(20))
    println(dT * (new MathVector(Seq(-.5, -.2, -.3))))
  }
}
