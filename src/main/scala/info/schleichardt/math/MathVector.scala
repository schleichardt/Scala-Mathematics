package info.schleichardt.math

import math._

class MathVector(val content: Seq[Double]) {
  def length() = content.length

  override def toString(): String = content.map(x => "%.4f" format x).mkString("(",", ",")T")

  def +(other: MathVector)= {
    require(length == other.length, "length matches")
//TODO beliebige Länge
    new MathVector(Seq(content(0) + other.content(0), content(1) + other.content(1), content(2) + other.content(2)))
  }

  def -(other: MathVector)= {
    require(length == other.length, "length matches")
//TODO beliebige Länge
    new MathVector(Seq(content(0) - other.content(0), content(1) - other.content(1), content(2) - other.content(2)))  
  }

  def x(other: MathVector)= {
    require(length == other.length, "length matches")
//TODO beliebige Länge
    val row1 = content(1) * other.content(2) - content(2) * other.content(1)
    val row2 = content(2) * other.content(0) - content(0) * other.content(2)
    val row3 = content(0) * other.content(1) - content(1) * other.content(0)
    new MathVector(Seq(row1, row2, row3))
  }

  def *(other: MathVector)= {
    require(length == other.length, "length matches")
    content(0) * other.content(0) + content(1) * other.content(1) + content(2) * other.content(2)
  }
}
