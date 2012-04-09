package info.schleichardt.math

import math.Pi

object Angle {
  def fromDegree(degree: Double) = new Angle(degreeToRadiant(degree))

  def degreeToRadiant(degree: Double) = degree * Pi / 180
}

class Angle(val radiant: Double) {}