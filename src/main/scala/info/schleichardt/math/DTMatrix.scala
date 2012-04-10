package info.schleichardt.math

import scala.math._

object DTMatrix {
  def calculateXCoordinateViewer(distanceToViewer: Double, rotatedVector: MathVector) = {
    (distanceToViewer * rotatedVector.content(0)) / (distanceToViewer - rotatedVector.content(2))
  }

  def calculateYCoordinateViewer(distanceToViewer: Double, rotatedVector: MathVector) = {
    (distanceToViewer * rotatedVector.content(1)) / (distanceToViewer - rotatedVector.content(2))
  }
}

class DTMatrix() {
  def toConcreteMatrix(phi: Angle, psi: Angle): ValueMatrix = {
    import info.schleichardt.math.Angle.implicitAngleToRadiantDouble
    val row1: Seq[Double] = Seq(cos(psi), 0, - sin(psi))
    val row2: Seq[Double] = Seq(- sin(phi) * sin(psi), cos(phi), - sin(phi) * cos(psi))
    val row3: Seq[Double] = Seq(cos(phi) * sin(psi), sin(phi), cos(phi) * cos(psi))
    new ValueMatrix(Seq(row1, row2, row3))
  }
}