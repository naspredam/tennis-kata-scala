package com.kata.tennis.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TennisGameTest extends AnyFlatSpec with Matchers {

  private val playerOne: GamePlayer = GamePlayer()
  private val playerTwo: GamePlayer = GamePlayer()

  "Game" should "start the game with score love-love" in {
    val game = TennisGame.start(playerOne, playerTwo)
    game.score() shouldEqual "0 - 0"
  }

  "Game" should "be fifteen-love when player one wins a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScore = playerOne winPointOn game
    gameWithNewScore.score() shouldEqual "15 - 0"
  }

  "Game" should "be love-fifteen when player two wins a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScore = playerTwo winPointOn game
    gameWithNewScore.score() shouldEqual "0 - 15"
  }

  "Game" should "be fifteen-fifteen when both players won a point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScoreFirst = playerOne winPointOn game
    val gameWithNewScore = playerTwo winPointOn gameWithNewScoreFirst
    gameWithNewScore.score() shouldEqual "15 - 15"
  }

  "Game" should "be thirty-love when players one won (only player one) two point" in {
    val game = TennisGame.start(playerOne, playerTwo)
    val gameWithNewScoreFirst = playerOne winPointOn game
    val gameWithNewScore = playerOne winPointOn gameWithNewScoreFirst
    gameWithNewScore.score() shouldEqual "30 - 0"
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
    startedGame.score() shouldEqual "0 - 0"

    val gameWithOnePointPlayed = playerOne winPointOn startedGame
    gameWithOnePointPlayed.score() shouldEqual "15 - 0"

    val gameWithTwoPointPlayed = playerOne winPointOn gameWithOnePointPlayed
    gameWithTwoPointPlayed.score() shouldEqual "30 - 0"

    val gameWithThreePointPlayed = playerOne winPointOn gameWithTwoPointPlayed
    gameWithThreePointPlayed.score() shouldEqual "40 - 0"

    val gameWithFourPointPlayed = playerOne winPointOn gameWithThreePointPlayed
    gameWithFourPointPlayed.score() shouldEqual "Game to player 1"
  }

  "Game" should "be won by player two, when we have a full game played, with some difficulties..." in {
    val startedGame = TennisGame start(playerOne, playerTwo)
    startedGame.score() shouldEqual "0 - 0"

    val gameWithOnePointPlayed = playerTwo winPointOn startedGame
    gameWithOnePointPlayed.score() shouldEqual "0 - 15"

    val gameWithTwoPointPlayed = playerTwo winPointOn gameWithOnePointPlayed
    gameWithTwoPointPlayed.score() shouldEqual "0 - 30"

    val gameWithThreePointPlayed = playerOne winPointOn gameWithTwoPointPlayed
    gameWithThreePointPlayed.score() shouldEqual "15 - 30"

    val gameWithFourPointPlayed = playerOne winPointOn gameWithThreePointPlayed
    gameWithFourPointPlayed.score() shouldEqual "30 - 30"

    val gameWithFivePointPlayed = playerOne winPointOn gameWithFourPointPlayed
    gameWithFivePointPlayed.score() shouldEqual "40 - 30"

    val gameWithSixPointPlayed = playerTwo winPointOn gameWithFivePointPlayed
    gameWithSixPointPlayed.score() shouldEqual "40 - 40"

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
