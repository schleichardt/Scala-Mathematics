package info.schleichardt.math.exercises

import info.schleichardt.math.{Angle, RotationQuaternion, Quaternion}
import scala.Predef._


object QuaternionenAufgabe {
  def main(args: Array[String]) {
    val p1: Quaternion = Quaternion(0.9063, 0.2353, 0.2143, 0.2804).x(Quaternion(0, 2, 3, 4))
    val result = p1.x(Quaternion(0.9063, -0.2352, -0.2113, -0.2804))



    val rotationAngle = Angle.fromDegree(50.0)
    val phi= Angle.fromDegree(30.0)
    val psi = Angle.fromDegree(40.0)
    val rotationQuaternion = RotationQuaternion(rotationAngle, phi, psi)
    println("rotationQuaternion " + rotationQuaternion)
    val vector = Quaternion(0, 2, 3, 4)
    val inverse: Quaternion = rotationQuaternion.inverse
    println("norm " + rotationQuaternion.norm)
    println("inverse " + inverse)
    val zwischenErgebnis: Quaternion = rotationQuaternion.x(vector)
    println("zwischenErgebnis " + zwischenErgebnis)

    val xRotated = zwischenErgebnis.x(inverse)
    println("rotiert: " + xRotated)
    /*
    MathVector(x, y, z) (0,5567, 0,5000, 0,6634)T
rotationQuaternion (0,9063, (0,2353, 0,2113, 0,2804)T)
rotiert: (0,0000, (2,3404, 2,1798, 4,3326)T)
     */
  }
}
