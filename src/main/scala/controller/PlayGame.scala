package controller

import model.{ActivePlayer, Game}
import view.View

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object PlayGame {
  @tailrec def playGame(
                         playerOneOriginalCanvas: Map[(Int, Int), Int],
                         playerTwoOriginalCanvas: Map[(Int, Int), Int],
                         activePlayerState: ActivePlayer
                       ): Any = {
    val playerOneWins: Boolean = !playerTwoOriginalCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !playerOneOriginalCanvas.valuesIterator.exists(_ == 1)

    val attack = (x: String, y: String, canvas: Map[(Int, Int), Int]) => {
      val newCanvas = Game.attack(x, y, canvas)

      println(View.renderAttack(newCanvas) + "\n")
      Thread.sleep(5000)
      println("\n\n")

      //If player one was the active player, so activePlayerState.playerOne = true
      //make activePlayerState.playerOne = false
      //If player two was not the active player, so activePlayerState.playerTwo = false
      //now make activePlayerState.playerTwo = true, changing player two to be the active player

      (newCanvas, ActivePlayer(playerOne = !activePlayerState.playerOne, playerTwo = !activePlayerState.playerTwo))
    }

    if (playerOneWins) println(playerOneWinMessage)
    else if (playerTwoWins) println(playerTwoWinMessage)
    else if (activePlayerState.playerOne) {
      println("Player 1, please attack one coordinate on player 2's board:\n\n")
      println(View.renderAttack(playerTwoOriginalCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input
          val result = attack(xCoordinate, yCoordinate, playerTwoOriginalCanvas)

          val playerTwoNewCanvas = result._1
          val activePlayerNewState = result._2

          playGame(playerOneOriginalCanvas, playerTwoNewCanvas, activePlayerNewState)

        case _ => playGame(playerOneOriginalCanvas, playerTwoOriginalCanvas, activePlayerState)
      }
    } else if (activePlayerState.playerTwo) {
      println("Player 2, please attack one coordinate on player 1's board: ")
      println(View.renderAttack(playerOneOriginalCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, xCoordinate, yCoordinate) = input
          val result = attack(xCoordinate, yCoordinate, playerOneOriginalCanvas)

          val playerOneNewCanvas = result._1
          val activePlayerNewState = result._2

          playGame(playerOneNewCanvas, playerTwoOriginalCanvas, activePlayerNewState)

        case _ => playGame(playerOneOriginalCanvas, playerTwoOriginalCanvas, activePlayerState)
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
