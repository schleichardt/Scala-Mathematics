package info.schleichardt.math

import math._

class MathVector(val content: Seq[Double]) {
  def length() = content.length

  override def toString(): String = content.map(x => "%.4f" format x).mkString("(",", ",")T")
}
