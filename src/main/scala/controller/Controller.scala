package controller

import model.{ActivePlayer, Game}
import view.View

import scala.annotation.tailrec
import scala.io.StdIn._

object Controller {
  def apply: Any = {
    println(title)

    println("Player 1, please create a new board and place your ships")
    val playerOneCanvas = initialiseGame(Map(), 0)
    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n"); Thread.sleep(900)
    }

    println("Player 2, please create a new board and place your ships")
    val playerTwoCanvas = initialiseGame(Map(), 0)
    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n"); Thread.sleep(900)
    }

    playGame(playerOneCanvas, playerTwoCanvas, ActivePlayer(playerOne = true, playerTwo = false))
  }

  @tailrec def initialiseGame(canvas: Map[(Int, Int), Int], ships: Int): Map[(Int, Int), Int] = {
    if (ships == 4) {
      canvas
    } else {
      val input = readLine().split(" ")

      input.head match {
        case "M" | "m" =>
          println(View.render(Game.createNewBoard) + "\n\n")

          initialiseGame(Game.createNewBoard, ships)
        case "P" | "p" =>
          val Array(_, startX, startY, endX, endY) = input
          val newCanvas = Game.putShip(startX, startY, endX, endY, canvas)

          println(View.render(newCanvas) + "\n\n")

          initialiseGame(newCanvas, ships + 1)
        case _ => initialiseGame(canvas, ships)
      }
    }
  }

  @tailrec def playGame(
                playerOneCanvas: Map[(Int, Int), Int],
                playerTwoCanvas: Map[(Int, Int), Int],
                activeAttacker: ActivePlayer
              ): Any = {
    val playerOneWins: Boolean = !playerTwoCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !playerOneCanvas.valuesIterator.exists(_ == 1)

    val attack = (x: String, y: String, canvas: Map[(Int, Int), Int]) => {
      val newCanvas = Game.Attack(x, y, canvas)

      println(View.renderAttack(newCanvas) + "\n")
      Thread.sleep(5000)
      println("\n\n")

      (newCanvas, ActivePlayer(playerOne = !activeAttacker.playerOne, playerTwo = !activeAttacker.playerTwo))
    }

    if (playerOneWins) {
      println(playerOneWin)
    } else if (playerTwoWins) {
      println(playerTwoWin)
    } else if (activeAttacker.playerOne) {
      println("Player 1, please attack one coordinate on player 2's board:\n\n")
      println(View.renderAttack(playerTwoCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val result = attack(x, y, playerTwoCanvas)

          playGame(playerOneCanvas, result._1, result._2)
        case _ => playGame(playerOneCanvas, playerTwoCanvas, activeAttacker)
      }
    } else if (activeAttacker.playerTwo) {
      println("Player 2, please attack one coordinate on player 1's board: ")
      println(View.renderAttack(playerOneCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val result = attack(x, y, playerOneCanvas)

          playGame(result._1, playerTwoCanvas, result._2)
        case _ => playGame(playerOneCanvas, playerTwoCanvas, activeAttacker)
      }
    } else println("error")
  }

  val title: String =
    """
      |██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░
      |██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗
      |██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝
      |██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░
      |██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░
      |╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░
      |""".stripMargin + "\n\n\n"

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
