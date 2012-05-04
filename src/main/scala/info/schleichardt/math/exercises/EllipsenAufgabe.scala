package info.schleichardt.math.exercises

import info.schleichardt.math.ValueMatrix

object EllipsenAufgabe {
  def main(args: Array[String]) {
    val A = ValueMatrix(Seq(-2.0, 1), Seq(3.0, 1))
    val B = A.transpose * A
    val v = A * ValueMatrix(Seq(1.0), Seq(0.0902))
    println(v)
  }
}
