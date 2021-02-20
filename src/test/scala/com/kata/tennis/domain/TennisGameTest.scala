package com.kata.tennis.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TennisGameTest extends AnyFlatSpec with Matchers {

  private val playerOne: GamePlayer = GamePlayer()
  private val playerTwo: GamePlayer = GamePlayer()

  "Game" should "start the game with score love-love" in {
    val game = TennisGame.start(playerOne, playerTwo)
    game.score() shouldEqual "love - love"
  }

  "Game" should "be fifteen-love when player one wins a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScore = playerOne winPointOn game
    gameWithNewScore.score() shouldEqual "fifteen - love"
  }

  "Game" should "be love-fifteen when player two wins a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScore = playerTwo winPointOn game
    gameWithNewScore.score() shouldEqual "love - fifteen"
  }

  "Game" should "be fifteen-fifteen when both players won a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScoreFirst = playerOne winPointOn game
    val gameWithNewScore = playerTwo winPointOn gameWithNewScoreFirst
    gameWithNewScore.score() shouldEqual "fifteen - fifteen"
  }

  "Game" should "be thirty-love when players one won (only player one) two point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScoreFirst = playerOne winPointOn game
    val gameWithNewScore = playerOne winPointOn gameWithNewScoreFirst
    gameWithNewScore.score() shouldEqual "thirty - love"
  }

  "Game" should "be deuce when the advantage is reduced" in {
    val game = TennisGame.of(playerOne, playerTwo, Score(Forty, Thirty, Advantage))
    val gameWithNewScore = playerTwo winPointOn game
    gameWithNewScore.score() shouldEqual "deuce"
  }

  "Game" should "be advanced on player on, when winning a point from deuce" in {
    val game = TennisGame.of(playerOne, playerTwo, Score(Forty, Forty, Deuce))
    val gameWithNewScore = playerOne winPointOn game
    gameWithNewScore.score() shouldEqual "advantage for player 1"
  }

  "Game" should "be advanced on player two, when winning a point from deuce" in {
    val game = TennisGame.of(playerOne, playerTwo, Score(Forty, Forty, Deuce))
    val gameWithNewScore = playerTwo winPointOn game
    gameWithNewScore.score() shouldEqual "advantage for player 2"
  }

  "Game" should "be game to player one, when fulfilling rules to win the game" in {
    val game = TennisGame.of(playerOne, playerTwo, Score(Forty, Love))
    val gameWithNewScore = playerOne winPointOn game
    gameWithNewScore.score() shouldEqual "Game to player 1"
  }

  "Game" should "be game to player two, when fulfilling rules to win the game" in {
    val game = TennisGame.of(playerOne, playerTwo, Score(Love, Forty))
    val gameWithNewScore = playerTwo winPointOn game
    gameWithNewScore.score() shouldEqual "Game to player 2"
  }

  "Game" should "be won by player one, when we have a full game played, straightforward..." in {
    val startedGame = TennisGame start(playerOne, playerTwo)
    startedGame.score() shouldEqual "love - love"

    val gameWithOnePointPlayed = playerOne winPointOn startedGame
    gameWithOnePointPlayed.score() shouldEqual "fifteen - love"

    val gameWithTwoPointPlayed = playerOne winPointOn gameWithOnePointPlayed
    gameWithTwoPointPlayed.score() shouldEqual "thirty - love"

    val gameWithThreePointPlayed = playerOne winPointOn gameWithTwoPointPlayed
    gameWithThreePointPlayed.score() shouldEqual "forty - love"

    val gameWithFourPointPlayed = playerOne winPointOn gameWithThreePointPlayed
    gameWithFourPointPlayed.score() shouldEqual "Game to player 1"
  }

  "Game" should "be won by player two, when we have a full game played, with some difficulties..." in {
    val startedGame = TennisGame start(playerOne, playerTwo)
    startedGame.score() shouldEqual "love - love"

    val gameWithOnePointPlayed = playerTwo winPointOn startedGame
    gameWithOnePointPlayed.score() shouldEqual "love - fifteen"

    val gameWithTwoPointPlayed = playerTwo winPointOn gameWithOnePointPlayed
    gameWithTwoPointPlayed.score() shouldEqual "love - thirty"

    val gameWithThreePointPlayed = playerOne winPointOn gameWithTwoPointPlayed
    gameWithThreePointPlayed.score() shouldEqual "fifteen - thirty"

    val gameWithFourPointPlayed = playerOne winPointOn gameWithThreePointPlayed
    gameWithFourPointPlayed.score() shouldEqual "thirty - thirty"

    val gameWithFivePointPlayed = playerOne winPointOn gameWithFourPointPlayed
    gameWithFivePointPlayed.score() shouldEqual "forty - thirty"

    val gameWithSixPointPlayed = playerTwo winPointOn gameWithFivePointPlayed
    gameWithSixPointPlayed.score() shouldEqual "forty - forty"

    val gameWithSevenPointPlayed = playerTwo winPointOn gameWithSixPointPlayed
    gameWithSevenPointPlayed.score() shouldEqual "advantage for player 2"

    val gameWithEightPointPlayed = playerOne winPointOn gameWithSevenPointPlayed
    gameWithEightPointPlayed.score() shouldEqual "deuce"

    val gameWithNinePointPlayed = playerTwo winPointOn gameWithEightPointPlayed
    gameWithNinePointPlayed.score() shouldEqual "advantage for player 2"

    val gameWithTenPointPlayed = playerTwo winPointOn gameWithNinePointPlayed
    gameWithTenPointPlayed.score() shouldEqual "Game to player 2"
  }

}
