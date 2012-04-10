package info.schleichardt.math.exercises

import info.schleichardt.math._

object Tetraeder10042012Main {
  def main(args: Array[String]) {
    val dT = new DTMatrix().toConcreteMatrix(Angle.fromDegree(10), Angle.fromDegree(20))
    val rotatedVector: MathVector = dT * (new MathVector(Seq(-.5, -.2, -.3)))
    println(rotatedVector)
    println(DTMatrix.calculateXCoordinateViewer(4.0, rotatedVector))
    println(DTMatrix.calculateYCoordinateViewer(4.0, rotatedVector))
    println(MathVector(0.5, 0, -.9) x MathVector(0, -.8, .6))
    println((MathVector(0.5, -0.2, -.3) - MathVector(0, -.2, .6)) * MathVector(-.72, .3, .4))
    val g = MathVector(0.3368, 0.1736, 0.9254)
    val n0stern = MathVector(0.72, 0.3, 0.4)
    val n1stern = MathVector(-0.72, 0.3, 0.4)
    val n2stern = MathVector(0, .3, -.8)
    val n3stern = MathVector(0, -.9, 0)
    println("g * n0stern " + (g * n0stern))
    println("g * n1stern " + (g * n1stern))
    println("g * n2stern " + (g * n2stern))
    println("g * n3stern " + (g * n3stern))
  }
}