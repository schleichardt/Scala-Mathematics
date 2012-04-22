package info.schleichardt.math

import collection.immutable.IndexedSeq
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

//  /** only works for matrices of dimension 2 and 3 */
//  def inverse(): ValueMatrix = {
//    require("inverse works only for matrices of dimension 2 or 3", length == 3 or length == 2
//
//  }

  def *(other: Double): ValueMatrix = {
    val seq: Seq[Seq[Double]] = for (line <- 0 until content.length) yield {
      for (column <- 0 until content(0).length) yield {
        content(line)(column) * other
      }
    }
    new ValueMatrix(seq)
  }

  private def apply(x:Int, y:Int): Double = content(x)(y)
}