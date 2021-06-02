package controller

import model.Game.attack
import model.AttackPhase
import view.View.renderAttack

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object PlayGame {
  @tailrec def playGame(gameState: AttackPhase): Any = {
    val playerOneWins: Boolean = !gameState.playerTwoCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !gameState.playerOneCanvas.valuesIterator.exists(_ == 1)

    if (playerOneWins) println(playerOneWinMessage)
    else if (playerTwoWins) println(playerTwoWinMessage)
    else if (gameState.activePlayer.playerOne) {
      println("Player 1, please attack one coordinate on player 2's board:\n\n")
      println(renderAttack(gameState.playerTwoCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input

          val updatedGameState = attack(xCoordinate, yCoordinate, gameState)

          println(renderAttack(updatedGameState.playerTwoCanvas) + "\n")
          Thread.sleep(5000)
          println("\n\n")

          playGame(updatedGameState)

        case _ => playGame(gameState)
      }
    } else if (gameState.activePlayer.playerTwo) {
      println("Player 2, please attack one coordinate on player 1's board: ")
      println(renderAttack(gameState.playerOneCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input
          val updatedGameState = attack(xCoordinate, yCoordinate, gameState)

          println(renderAttack(updatedGameState.playerOneCanvas) + "\n")
          Thread.sleep(5000)
          println("\n\n")

          playGame(updatedGameState)

        case _ => playGame(gameState)
      }
    } else println("error")
  }

  val playerOneWinMessage: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin

  val playerTwoWinMessage: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ░░███╔═╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ██╔══╝░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin
}
