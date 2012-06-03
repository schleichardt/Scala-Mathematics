package info.schleichardt.math.exercises

import info.schleichardt.math.{Angle, RotationQuaternion, Quaternion}
import scala.Predef._


object Exercise15052012 {
  def main(args: Array[String]) {
     val rotationAngle = Angle.fromDegree(60.0)
    val phi= Angle.fromDegree(30.0)
    val psi = Angle.fromDegree(40.0)
    val rotationQuaternion = RotationQuaternion(rotationAngle, phi, psi)
    println("rotationQuaternion " + rotationQuaternion)
    val vector = Quaternion(0, 1, 0, 1)
    val inverse: Quaternion = rotationQuaternion.inverse
    println("norm " + rotationQuaternion.norm)
    println("inverse " + inverse)
    val q: Quaternion = rotationQuaternion.x(vector)
    println("q " + q)
  }
}
