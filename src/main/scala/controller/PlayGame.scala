package controller

import model.Game.attack
import model.{AttackPhase, PlayerOne, PlayerTwo}
import view.AttackPhase.{playerOneWinMessage, playerTwoWinMessage, printCanvasPostAttack, printCanvasPreAttack}

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object PlayGame {
  @tailrec def playGame(gameState: AttackPhase): Any = {
    val playerOneWins: Boolean = !gameState.playerTwoCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !gameState.playerOneCanvas.valuesIterator.exists(_ == 1)

    if (playerOneWins) playerOneWinMessage()
    else if (playerTwoWins) playerTwoWinMessage()
    else if (gameState.activePlayer == PlayerOne) {
      printCanvasPreAttack(gameState.playerTwoCanvas, activePlayer = gameState.activePlayer)

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input

          val updatedGameState = attack(xCoordinate, yCoordinate, gameState)

          printCanvasPostAttack(updatedGameState.playerTwoCanvas)

          playGame(updatedGameState)

        case _ => playGame(gameState)
      }
    } else if (gameState.activePlayer == PlayerTwo) {
      printCanvasPreAttack(gameState.playerOneCanvas, activePlayer = gameState.activePlayer)

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input
          val updatedGameState = attack(xCoordinate, yCoordinate, gameState)

          printCanvasPostAttack(updatedGameState.playerOneCanvas)

          playGame(updatedGameState)

        case _ => playGame(gameState)
      }
    } else println("error")
  }
}
