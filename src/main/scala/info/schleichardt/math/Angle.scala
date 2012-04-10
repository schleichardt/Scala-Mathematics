package info.schleichardt.math

import math.Pi

object Angle {
  def fromDegree(degree: Double) = new Angle(degreeToRadiant(degree))

  def degreeToRadiant(degree: Double) = degree * Pi / 180

  implicit def implicitAngleToRadiantDouble(angle: Angle) = angle.radiant
}

case class Angle(val radiant: Double) {}