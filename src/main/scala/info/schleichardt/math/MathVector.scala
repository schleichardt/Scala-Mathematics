package info.schleichardt.math

import math._
import collection.immutable.IndexedSeq

object MathVector {
  def apply(args: Double*) = new MathVector(args)
}

class MathVector(val content: Seq[Double]) {
  def length() = content.length

  override def toString(): String = content.map(x => "%.4f" format x).mkString("(",", ",")T")

  def +(other: MathVector)= {
    require(length == other.length, "length matches")
    val seq: IndexedSeq[Double] = for (i <- 0 until length) yield content(i) + other.content(i)
    new MathVector(seq)
  }

  def -(other: MathVector)= {
    require(length == other.length, "length matches")
    val seq: IndexedSeq[Double] = for (i <- 0 until length) yield content(i) - other.content(i)
    new MathVector(seq)
  }

  def x(other: MathVector)= {
    require(length == other.length && length == 3, "length matches")
    val row1 = content(1) * other.content(2) - content(2) * other.content(1)
    val row2 = content(2) * other.content(0) - content(0) * other.content(2)
    val row3 = content(0) * other.content(1) - content(1) * other.content(0)
    MathVector(row1, row2, row3)
  }

  def *(other: MathVector)= {
    require(length == other.length, "length matches")
    val seq: IndexedSeq[Double] = for (i <- 0 until length) yield content(i) * other.content(i)
    seq.sum
  }

  def *(other: Double): MathVector = new MathVector(content.map(_ * other))

  override def equals(that: Any) = {
    that match {
      case other: MathVector => content.equals(other.content)
      case _ => false
    }
  }
}

object MathVectorScalar {
  implicit def implicitConversionDoubleToMath(double: Double) = MathVectorScalar(double)
}

case class MathVectorScalar(value: Double) {
  def *(vector: MathVector) = vector * value
}
