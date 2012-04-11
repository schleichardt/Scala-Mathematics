package info.schleichardt.math

object ValueMatrix {
  def apply(input: Seq[Double]*) = new ValueMatrix(input)
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

  def +(other: ValueMatrix): ValueMatrix = {
    require(content.length > 0)
    require(content.length == other.content.length)
    require(content(0).length == other.content(0).length)
    val seq: Seq[Seq[Double]] =
      for (line <- 0 until content.length) yield {
        for (column <- 0 until content(0).length) yield {
          content(line)(column) + other.content(line)(column)
        }
      }
    new ValueMatrix(seq)
  }

  override def equals(that: Any) = {
    that match {
      case other: ValueMatrix => content.equals(other.content)
      case _ => false
    }
  }

  lazy val transpose = {
    val seq: Seq[Seq[Double]] =
      for (column <- 0 until content(0).length) yield {
        for (line <- 0 until content.length) yield content(line)(column)
      }
    new ValueMatrix(seq)
  }
}