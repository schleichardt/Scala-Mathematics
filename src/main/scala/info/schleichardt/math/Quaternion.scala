package info.schleichardt.math



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

  override def toString(): String = "(%.4f, %s,)".format(x0, vector)
}
