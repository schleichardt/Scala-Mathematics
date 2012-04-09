package info.schleichardt.math

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
    for (lineOfMatrix <- 0 until (content.length) /*instead foo - 1*/){
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