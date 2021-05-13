package controller

import model.{ActiveAttacker, Attack, Canvas}
import view.View

import scala.io.StdIn._

object Controller {
  def apply: Any = {
    val title: String =
      """
        |██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░
        |██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗
        |██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝
        |██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░
        |██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░
        |╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░
        |""".stripMargin + "\n\n\n"

    println(title)

    println("Player 1, please create a new board and place your ships")
    val playerOneCanvas = initialiseGame(Canvas.apply, 0)

    Thread.sleep(5000)
    for { _ <- 1 to 15 } { println("\n"); Thread.sleep(900)}

    println("Player 2, please create a new board and place your ships")
    val playerTwoCanvas = initialiseGame(Canvas.apply, 0)

    Thread.sleep(5000)
    for { _ <- 1 to 15 } { println("\n"); Thread.sleep(900)}

    playGame(playerOneCanvas.getCanvas, playerTwoCanvas.getCanvas, ActiveAttacker(playerOne = true, playerTwo = false))
  }

  def initialiseGame(canvas: Canvas, ships: Int): Canvas = {
    if (ships == 4) return canvas

    val input = readLine().split(" ")

    input.head match {
      case "M" | "m" =>
        println(View.render(Canvas.apply.makeCanvas.getCanvas) + "\n\n")

        initialiseGame(Canvas.apply.makeCanvas, ships)
      case "P" | "p" if ships < 4 =>
        val Array(_, startX, startY, endX, endY) = input
        val newCanvas = canvas.putShip(startX, startY, endX, endY)

        println(View.render(newCanvas.getCanvas) + "\n\n")

        initialiseGame(newCanvas, ships + 1)
      case _ => initialiseGame(canvas, ships)
    }
  }

  def playGame(
                playerOneCanvas: Map[(Int, Int), Int],
                playerTwoCanvas: Map[(Int, Int), Int],
                activeAttacker: ActiveAttacker
              ): Any = {
    val playerOneWins: Boolean = !playerTwoCanvas.valuesIterator.exists(_ == 1)
    val playerTwoWins: Boolean = !playerOneCanvas.valuesIterator.exists(_ == 1)

    if (playerOneWins) {
      val win =
        """
          |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
          |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝
          |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
          |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
          |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
          |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
          |""".stripMargin

      println(win)
    }
    else if (playerTwoWins) {
      val win =
        """
          |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
          |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝
          |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ░░███╔═╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
          |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ██╔══╝░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
          |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
          |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
          |""".stripMargin

      println(win)
    } else if (activeAttacker.playerOne) {
      println("Player 1, please attack one coordinate on player 2's board:\n\n")
      println(View.renderAttack(playerTwoCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val newCanvas = new Attack(x, y, playerTwoCanvas).attack

          println(View.renderAttack(newCanvas) + "\n")

          Thread.sleep(5000)

          println("\n\n")

          playGame(playerOneCanvas, newCanvas, ActiveAttacker(playerOne = false, playerTwo = true))
        case _ => playGame(playerOneCanvas, playerTwoCanvas, ActiveAttacker(playerOne = true, playerTwo = false))
      }
    } else if (activeAttacker.playerTwo) {
      println("Player 2, please attack one coordinate on player 1's board: ")
      println(View.renderAttack(playerOneCanvas) + "\n\n")

      val input = readLine().split(" ")

      input.head match {
        case "A" | "a" =>
          val Array(_, x, y) = input
          val newCanvas = new Attack(x, y, playerOneCanvas).attack

          println(View.renderAttack(newCanvas) + "\n\n")

          Thread.sleep(5000)

          println("\n\n")

          playGame(newCanvas, playerTwoCanvas, ActiveAttacker(playerOne = true, playerTwo = false))
        case _ => playGame(playerOneCanvas, playerTwoCanvas, ActiveAttacker(playerOne = false, playerTwo = true))
      }
    } else println("error")
  }
}
