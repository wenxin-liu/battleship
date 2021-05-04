package controller

import model.Canvas
import view.View
import scala.io.StdIn._

object Controller {
  def apply = {
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
    val playerOneCanvas: Any = initialiseGame(Canvas.apply, 0)

    println("Player 2, please create a new board and place your ships")
    val playerTwoCanvas: Any = initialiseGame(Canvas.apply, 0)
  }

  def initialiseGame(canvas: Canvas, ships: Int): Any = {
    if (ships == 4) return canvas

    val input = readLine().split(" ")

    input.head match {
      case "M" | "m" =>
        println(View.Render(Canvas.apply.makeCanvas.getCanvas)+"\n\n")

        initialiseGame(Canvas.apply.makeCanvas, ships)
      case "P" | "p" if ships < 4 =>
        val Array(_, startX, startY, endX, endY) = input
        val newCanvas = canvas.putShip(startX, startY, endX, endY)

        println(View.Render(newCanvas.getCanvas)+"\n\n")

        initialiseGame(newCanvas, ships + 1)
      case _ => initialiseGame(canvas, ships)
    }
  }
}
