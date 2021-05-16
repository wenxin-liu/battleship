package controller

import model.{ActivePlayer, Game}
import view.View

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object PlayGame {
  @tailrec def playGame(
                         playerOneCanvas: Map[(Int, Int), Int],
                         playerTwoCanvas: Map[(Int, Int), Int],
                         activePlayer: ActivePlayer
                       ): Any = {
    val playerOneWins: Boolean = !playerTwoCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !playerOneCanvas.valuesIterator.exists(_ == 1)

    val attack = (x: String, y: String, canvas: Map[(Int, Int), Int]) => {
      val newCanvas = Game.attack(x, y, canvas)

      println(View.renderAttack(newCanvas) + "\n")
      Thread.sleep(5000)
      println("\n\n")

      (newCanvas, ActivePlayer(playerOne = !activePlayer.playerOne, playerTwo = !activePlayer.playerTwo))
    }

    if (playerOneWins) println(playerOneWin)
    else if (playerTwoWins) println(playerTwoWin)
    else if (activePlayer.playerOne) {
      println("Player 1, please attack one coordinate on player 2's board:\n\n")
      println(View.renderAttack(playerTwoCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val result = attack(x, y, playerTwoCanvas)

          playGame(playerOneCanvas, result._1, result._2)

        case _ => playGame(playerOneCanvas, playerTwoCanvas, activePlayer)
      }
    } else if (activePlayer.playerTwo) {
      println("Player 2, please attack one coordinate on player 1's board: ")
      println(View.renderAttack(playerOneCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val result = attack(x, y, playerOneCanvas)

          playGame(result._1, playerTwoCanvas, result._2)

        case _ => playGame(playerOneCanvas, playerTwoCanvas, activePlayer)
      }
    } else println("error")
  }

  val playerOneWin: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin

  val playerTwoWin: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ░░███╔═╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ██╔══╝░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin
}
