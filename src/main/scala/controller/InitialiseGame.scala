package controller

import model.Game
import view.View

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object InitialiseGame {
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
          val newCanvas = Game.placeShip(startX, startY, endX, endY, canvas)

          println(View.render(newCanvas) + "\n\n")

          initialiseGame(newCanvas, ships + 1)
        case _ => initialiseGame(canvas, ships)
      }
    }
  }
}
