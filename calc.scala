import math._

class MathVector(val content: Seq[Double]) {
  def length() = content.length

  override def toString(): String = content.map(x => "%.4f" format x).mkString("(",", ",")T")
}

class ValueMatrix(val content: Seq[Seq[Double]]) {
  override def toString(): String = {
    val builder = new StringBuilder
    content.foreach(line => {
      line.foreach(column => builder.append("%.4f " format column))
      builder.append("\n") 
    })
    builder.toString
  }

  lazy val columnCount = content.head.length

  def *(vector: MathVector): MathVector = {
    require(columnCount == vector.length,"length matches")
    val result = scala.collection.mutable.ListBuffer[Double]()
    for (lineOfMatrix <- 0 until content.length /*instead foo - 1*/){
      var zwischenErgebnis = 0.0
      for (columnOfMatrix <- 0 until columnCount){
        val valueFromMatrix = content(lineOfMatrix)(columnOfMatrix)
        val valueFromVector = vector.content(columnOfMatrix)
        zwischenErgebnis += valueFromMatrix * valueFromVector
      }
      result += zwischenErgebnis
    }
    new MathVector(result)
  }
}

object Angle {
  def fromDegree(degree: Double) = new Angle(degreeToRadiant(degree))

  def degreeToRadiant(degree: Double) = degree * Pi / 180
}

class Angle(val radiant: Double) {}

implicit def angleToDouble(angle: Angle) = angle.radiant

class DTMatrix() {
  def toConcreteMatrix(phi: Angle, psi: Angle): ValueMatrix = {
    val row1: Seq[Double] = Seq(cos(phi), 0, - sin(psi))
    val row2: Seq[Double] = Seq(- sin(phi) * sin(psi), cos(psi), - sin(phi) * cos(psi))
    val row3: Seq[Double] = Seq(cos(phi) * sin(psi), sin(phi), cos(psi) * cos(phi))
    new ValueMatrix(Seq(row1, row2, row3))
  }
}
val dT = new DTMatrix().toConcreteMatrix(Angle.fromDegree(10), Angle.fromDegree(20))
println(dT * (new MathVector(Seq(-.5, -.2, -.3))))