package info.schleichardt.math.exercises

import info.schleichardt.math._

object Tetraeder10042012Main {
  def main(args: Array[String]) {
    //Übung Aufgabe 2 aus Altklausur 10.4.2012
    val phi = Angle.fromDegree(15)
    val psi = Angle.fromDegree(60)
    val lambda: Double = 50
    val dT = new DTMatrix().toConcreteMatrix(phi, psi)
    println("dT=\n" + dT)
    val p0 = MathVector(-8, -1, 7)
    val p1 = MathVector(7, -7, 3)
    val p2 = MathVector(-2, 0, -10)
    val p3 = MathVector(3.0, 8.0, 0.0)
    val a = dT * p3
    println("\ndT*p3= " + a)
    val xCoord = DTMatrix.calculateXCoordinateViewer(lambda, a)
    val yCoord = DTMatrix.calculateYCoordinateViewer(lambda, a)
    println("x=" + xCoord)//Koordinaten des Bildpunkts der Ecke P3
    println("y=" + yCoord)
    val n3: MathVector = (p0 - p1) x (p1 - p2)
    println("n3=" + n3)
    println("(p3-p0,n3)=" + ((p3-p0) * n3))// > 0 => ist innere Normale
  }
}