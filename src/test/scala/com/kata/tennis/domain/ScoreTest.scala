package com.kata.tennis.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScoreTest extends AnyFlatSpec with Matchers {

  "Score" should "be initialized with love love, for the two players" in {
    val score = Score()
    score shouldBe Score(Love, Love)
  }

  "Score" should "be incremented on the next point, fifteen, when player one wins" in {
    val score = Score(Love, Love)
    score.newScore(1) shouldBe Score(Fifteen, Love)
  }

  "Score" should "be incremented on the next point, thirty, when player one wins" in {
    val score = Score(Fifteen, Love)
    score.newScore(1) shouldBe Score(Thirty, Love)
  }

  "Score" should "be incremented on the next point, forty, when player one wins" in {
    val score = Score(Thirty, Love)
    score.newScore(1) shouldBe Score(Forty, Love)
  }

  "Score" should "be incremented on the next point, fifteen, when player two wins" in {
    val score = Score(Love, Love)
    score.newScore(2) shouldBe Score(Love, Fifteen)
  }

  "Score" should "be incremented on the next point, thirty, when player two wins" in {
    val score = Score(Love, Fifteen)
    score.newScore(2) shouldBe Score(Love, Thirty)
  }

  "Score" should "be incremented on the next point, forty, when player two wins" in {
    val score = Score(Love, Thirty)
    score.newScore(2) shouldBe Score(Love, Forty)
  }

  "Score" should "get tie on fifteen when player one wins with player one did win before" in {
    val score = Score(Love, Fifteen)
    score.newScore(1) shouldBe Score(Fifteen, Fifteen)
  }

  "Score" should "get tie on fifteen when player two wins with player one did win before" in {
    val score = Score(Fifteen, Love)
    score.newScore(2) shouldBe Score(Fifteen, Fifteen)
  }

  "Score" should "get thirty-fifteen when player one wins with player one did win before" in {
    val score = Score(Fifteen, Fifteen)
    score.newScore(1) shouldBe Score(Thirty, Fifteen)
  }

  "Score" should "get fifteen-thirty when player one wins with player two did win before" in {
    val score = Score(Fifteen, Fifteen)
    score.newScore(2) shouldBe Score(Fifteen, Thirty)
  }

  "Score" should "get tie thirty when player one wins with fifteen-thirty" in {
    val score = Score(Fifteen, Thirty)
    score.newScore(1) shouldBe Score(Thirty, Thirty)
  }

  "Score" should "get tie thirty when player two wins with thirty-fifteen" in {
    val score = Score(Thirty, Fifteen)
    score.newScore(2) shouldBe Score(Thirty, Thirty)
  }

  "Score" should "get forty-thirty when player one wins from tie thirty" in {
    val score = Score(Thirty, Thirty)
    score.newScore(1) shouldBe Score(Forty, Thirty)
  }

  "Score" should "get thirty-forty when player two wins from tie thirty" in {
    val score = Score(Thirty, Thirty)
    score.newScore(2) shouldBe Score(Thirty, Forty)
  }

  "Score" should "get tie forty when player one wins from thirty-forty" in {
    val score = Score(Thirty, Forty)
    score.newScore(1) shouldBe Score(Forty, Forty)
  }

  "Score" should "get tie forty when player two wins from forty-thirty" in {
    val score = Score(Forty, Thirty)
    score.newScore(2) shouldBe Score(Forty, Forty)
  }

  "Score" should "advantage player one when player one wins from tie forty" in {
    val score = Score(Forty, Forty)
    score.newScore(1) shouldBe Score(Forty, Thirty, Advantage)
  }

  "Score" should "advantage player two when player two wins from tie forty" in {
    val score = Score(Forty, Forty)
    score.newScore(2) shouldBe Score(Thirty, Forty, Advantage)
  }

  "Score" should "be deuce when player one reduces the advantage" in {
    val score = Score(Thirty, Forty, Advantage)
    score.newScore(1) shouldBe Score(Forty, Forty, Deuce)
  }

  "Score" should "be deuce when player two reduces the advantage" in {
    val score = Score(Forty, Thirty, Advantage)
    score.newScore(2) shouldBe Score(Forty, Forty, Deuce)
  }

  "Score" should "be won by player one when does one point when forty-love" in {
    val score = Score(Forty, Love)
    score.newScore(1) shouldBe Score(Forty, Love, Won)
  }

  "Score" should "be won by player one when does one point when forty-fifteen" in {
    val score = Score(Forty, Fifteen)
    score.newScore(1) shouldBe Score(Forty, Fifteen, Won)
  }

  "Score" should "be won by player one when does one point when forty-thirty" in {
    val score = Score(Forty, Thirty)
    score.newScore(1) shouldBe Score(Forty, Thirty, Won)
  }

  "Score" should "be won by player two when does one point when love-forty" in {
    val score = Score(Love, Forty)
    score.newScore(2) shouldBe Score(Love, Forty, Won)
  }

  "Score" should "be won by player two when does one point when forty-fifteen" in {
    val score = Score(Fifteen, Forty)
    score.newScore(2) shouldBe Score(Fifteen, Forty, Won)
  }

  "Score" should "be won by player two when does one point when forty-thirty" in {
    val score = Score(Thirty, Forty)
    score.newScore(2) shouldBe Score(Thirty, Forty, Won)
  }

  "Score" should "be won by player one when the player has already advantage" in {
    val score = Score(Forty, Thirty, Advantage)
    score.newScore(1) shouldBe Score(Forty, Thirty, Won)
  }

  "Score" should "be won by player two when the player has already advantage" in {
    val score = Score(Thirty, Forty, Advantage)
    score.newScore(2) shouldBe Score(Thirty, Forty, Won)
  }
}
