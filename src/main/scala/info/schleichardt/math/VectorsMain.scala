package info.schleichardt.math

object VectorsMain {
  def main(args: Array[String]) {
    val dT = new DTMatrix().toConcreteMatrix(Angle.fromDegree(10), Angle.fromDegree(20))
    val rotatedVector: MathVector = dT * (new MathVector(Seq(-.5, -.2, -.3)))
    println(rotatedVector)
    println(DTMatrix.calculateXCoordinateViewer(4.0, rotatedVector))
    println(DTMatrix.calculateYCoordinateViewer(4.0, rotatedVector))
  }
}
