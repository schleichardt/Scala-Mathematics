package info.schleichardt.math.exercises

import info.schleichardt.math.MathVector


object QuaternionenAufgabe {
  def main(args: Array[String]) {
    val x = MathVector(2, 0, 3)
    val y = MathVector(1, 3, 4)
    println(x.x(y))
  }
}
