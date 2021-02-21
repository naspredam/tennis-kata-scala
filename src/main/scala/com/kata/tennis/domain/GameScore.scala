package com.kata.tennis.domain

sealed abstract class ScoreStatus(val displayText: String)
case object Running extends ScoreStatus("running")
case object Deuce extends ScoreStatus("deuce")
case object Advantage extends ScoreStatus("advantage")
case object Won extends ScoreStatus("won")

import PointHelper._
case class Score(playerOnePoint: TennisPoint = Love,
                 playerTwoPoint: TennisPoint = Love,
                 status: ScoreStatus = Running) {

  private val bothOnForty = () => (playerOnePoint, playerTwoPoint) == (Forty, Forty)

  private val advantageOnPlayer = () => status == Advantage

  private val wonGame = (playerWon: Int) => playerWon match {
    case 1 => playerOnePoint == Forty && playerOnePoint.inPoint() - playerTwoPoint.inPoint() >= 1
    case 2 => playerTwoPoint == Forty && playerTwoPoint.inPoint() - playerOnePoint.inPoint() >= 1
  }

  def newScore(playerWon: Int): Score = playerWon match {
    case player if wonGame(player) => Score(playerOnePoint, playerTwoPoint, Won)
    case _ if advantageOnPlayer() => Score(Forty, Forty, Deuce)
    case 1 if bothOnForty() => Score(Forty, Thirty, Advantage)
    case 2 if bothOnForty() => Score(Thirty, Forty, Advantage)
    case 1 => Score(playerOnePoint.next(), playerTwoPoint)
    case 2 => Score(playerOnePoint, playerTwoPoint.next())
  }

}