package com.kata.tennis.domain

class TennisGame(playerOne: GamePlayer,
                 playerTwo: GamePlayer,
                 score: Score = Score()) {

  def score(): String = score.status match {
    case Running => s"${score.playerOnePoint.displayText} - ${score.playerTwoPoint.displayText}"
    case Advantage => s"${score.status.text} for player ${if (score.playerOnePoint == Forty) 1 else 2}"
    case Won => s"Game to player ${if (score.playerOnePoint == Forty) 1 else 2}"
    case _ => score.status.text
  }

  def playerWonPoint(gamePlayer: GamePlayer): TennisGame = {
    val playerWon = if (playerOne == gamePlayer) 1 else 2
    new TennisGame(playerOne, playerTwo, score.newScore(playerWon))
  }
}

object TennisGame {
  def start(playerOne: GamePlayer, playerTwo: GamePlayer) = new TennisGame(playerOne, playerTwo)
  def of(playerOne: GamePlayer, playerTwo: GamePlayer, score: Score) = new TennisGame(playerOne, playerTwo, score)
}