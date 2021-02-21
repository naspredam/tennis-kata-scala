package com.kata.tennis.domain

sealed abstract class TennisPoint(val displayText: String)
case object Love extends TennisPoint("0")
case object Fifteen extends TennisPoint("15")
case object Thirty extends TennisPoint("30")
case object Forty extends TennisPoint("40")

object PointHelper {
  implicit class PointOps(tennisPoint: TennisPoint) {
    def next(): TennisPoint = tennisPoint match {
      case Love => Fifteen
      case Fifteen => Thirty
      case Thirty => Forty
    }

    def inPoint(): Int = tennisPoint match {
      case Love => 0
      case Fifteen => 1
      case Thirty => 2
      case Forty => 3
    }

  }
}