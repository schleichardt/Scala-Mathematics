package info.schleichardt.math

import scala.collection.immutable.IndexedSeq
import info.schleichardt.math.ValueMatrix._
import scala._
import collection.mutable.ListBuffer
import collection.{Seq, GenTraversableOnce}

object ValueMatrix {
  def apply(input: Seq[Double]*) = new ValueMatrix(input)
  def identityMatrixForSize(size: Int) = {
    val seq: Seq[Seq[Double]] =
      for (line <- 0 until size) yield {
        for (column <- 0 until size) yield {
          if (line == column)
            1.0
          else
            0.0
        }
      }
    new ValueMatrix(seq)
  }
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

  def -(other: ValueMatrix): ValueMatrix = {
    this + (other * (-1))
  }

  override def equals(that: Any) = {
    that match {
      case other: ValueMatrix => toString().equals(other.toString())
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

  lazy val isNullMatrix: Boolean = {
    val allInALine: Seq[Double] = content.flatMap(x => x)
    allInALine.forall(_ == 0)
  }

  def *(other: ValueMatrix): ValueMatrix = {
    require(columnCount == other.content.length, "length matches")
    val seq: Seq[Seq[Double]] =
      for (lineResult <- 0 until content.length) yield {
        for (columnResult <- 0 until content(0).length) yield {
          (for (column <- 0 until content(0).length) yield {
            content(lineResult)(column) * other.content(column)(columnResult)
          }).sum
        }
      }
    new ValueMatrix(seq)
  }

  lazy val isIdentityMatrix: Boolean = columnCount == content.length && ValueMatrix.identityMatrixForSize(columnCount) == this

  def length = content.length

  def inverse(): ValueMatrix = {
    //http://de.wikipedia.org/wiki/Algorithmus_von_Faddejew-Leverrier
    val cs: ListBuffer[Double] = new ListBuffer()
    val Bs: ListBuffer[ValueMatrix] = new ListBuffer()
    val As: ListBuffer[ValueMatrix] = new ListBuffer()
    As += this
    for (i <- 0 until length){
      val Ai: ValueMatrix = As(i)
      val c = Ai.trace / (i + 1)
      cs += c
      val B = Ai - ValueMatrix.identityMatrixForSize(Ai.length) * c
      Bs += B
      val Anew = As(0) * B
      As += Anew
    }
    Bs(length - 2) * (1.0 / cs(length - 1))
  }

  def isSquareMatrix(): Boolean = length == columnCount

  def isSquareMatrixOfDimension(dimension: Int): Boolean = isSquareMatrix() && length == dimension

/** only works for matrices of dimension 2 and 3 */
  def determinant(): Double = {
    require(isSquareMatrixOfDimension(3) || isSquareMatrixOfDimension(2), "works only for square matrices of dimension 2 or 3")
    var result:Double = 0;
    if (isSquareMatrixOfDimension(3)) {
      result = content(0)(0) * content(1)(1) * content(2)(2) + content(0)(1) * content(1)(2) * content(2)(0) + content(0)(2) * content(1)(0) * content(2)(1) - content(0)(2) * content(1)(1) * content(2)(0) - content(0)(1) * content(1)(0) * content(2)(2) - content(0)(0) * content(1)(2) * content(2)(1)
    } else if (isSquareMatrixOfDimension(2)) {
      result = content(0)(0) *  content(1)(1) -  content(0)(1) *  content(1)(0)
    }
    result
  }

  def *(other: Double): ValueMatrix = {
    val seq: Seq[Seq[Double]] = for (line <- 0 until content.length) yield {
      for (column <- 0 until content(0).length) yield {
        content(line)(column) * other
      }
    }
    new ValueMatrix(seq)
  }

  private def apply(x:Int, y:Int): Double = content(x)(y)

  lazy val trace = diagonalElements.sum

  lazy val diagonalElements: Seq[Double] = for (i <- 0 until length) yield content(i)(i)
}