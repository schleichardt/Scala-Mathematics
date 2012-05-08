package info.schleichardt.math.exercises

import info.schleichardt.math.Quaternion


object QuaternionenAufgabe {
  def main(args: Array[String]) {
    val p1: Quaternion = Quaternion(0.9063, 0.2353, 0.2143, 0.2804).x(Quaternion(0, 2, 3, 4))
    val result = p1.x(Quaternion(0.9063, -0.2352, -0.2113, -0.2804))
    println(p1)
  }
}
