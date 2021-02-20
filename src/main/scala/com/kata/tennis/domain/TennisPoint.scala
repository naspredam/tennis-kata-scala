package com.kata.tennis.domain

sealed abstract class TennisPoint(val text: String)
case object Love extends TennisPoint("love")
case object Fifteen extends TennisPoint("fifteen")
case object Thirty extends TennisPoint("thirty")
case object Forty extends TennisPoint("forty")

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