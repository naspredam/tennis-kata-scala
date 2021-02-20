package com.kata.tennis.domain

class GamePlayer()
object GamePlayer {
  def apply(): GamePlayer = new GamePlayer

  implicit class PointWinner(gamePlayer: GamePlayer) {
    def winPointOn(game: TennisGame): TennisGame = game.playerWonPoint(gamePlayer)
  }
}