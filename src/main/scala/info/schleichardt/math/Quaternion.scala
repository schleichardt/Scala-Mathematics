package info.schleichardt.math

import scala.math.sqrt;

object Quaternion {
   def apply(x0: Double, x1: Double, x2: Double, x3: Double) = new Quaternion(x0, MathVector(x1, x2,x3))
}

class Quaternion private(val x0: Double, val vector: MathVector) {
  def x(other: Quaternion): Quaternion = {
     val resultX0 = (x0 * other.x0) - (vector * other.vector)
     val resultVector = other.vector * x0 + vector * other.x0 + vector.x(other.vector);
    new Quaternion(resultX0, resultVector)
  }

  override def equals(that: Any) = {
    that match {
      case other: Quaternion => toString() == other.toString()
      case _ => false
    }
  }

  def *(other: Double): Quaternion = Quaternion(other * x0, other * vector.x, other * vector.y, other * vector.z)

  override def toString(): String = "(%.4f, %s)".format(x0, vector)

  lazy val norm: Double = {
    import info.schleichardt.math.MathVectorScalar.implicitConversionDoubleToMath
    sqrt(x0.^(2) + vector.x.^(2) + vector.y.^(2) + vector.z.^(2))
  };

  lazy val inverse: Quaternion = {
    import info.schleichardt.math.MathVectorScalar.implicitConversionDoubleToMath
    new Quaternion(x0, vector * (-1.0)) * norm.^(-2)
  }
}
