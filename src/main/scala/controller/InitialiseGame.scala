package controller

import model.{Game, GameState}
import view.View

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object InitialiseGame {
  @tailrec def initialiseGame(gameState: GameState, ships: Int): GameState = {
    if (ships == 4) {
      gameState
    } else {
      val input = readLine().split(" ")

      input.head match {
        case "M" | "m" =>
          println(View.render(Game.createNewBoard.canvas) + "\n\n")

          initialiseGame(Game.createNewBoard, ships)
        case "P" | "p" =>
          val Array(_, startX, startY, endX, endY) = input
          val gameWithShip = Game.placeShip(startX, startY, endX, endY, gameState)

          println(View.render(gameWithShip.canvas) + "\n\n")

          initialiseGame(gameWithShip, ships + 1)
        case _ => initialiseGame(gameState, ships)
      }
    }
  }
}
