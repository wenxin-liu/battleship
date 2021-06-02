package controller

import model.{Game, PlacementPhase}
import view.View

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object InitialiseGame {
  @tailrec def initialiseGame(currentGameState: PlacementPhase): PlacementPhase = {
    val allShipsPlaced =
      currentGameState.shipsPlaced.submarine &&
        currentGameState.shipsPlaced.destroyer &&
        currentGameState.shipsPlaced.cruiser &&
        currentGameState.shipsPlaced.battleship

    //if all ships are placed, return the current game state as is without any modification
    if (allShipsPlaced) {
      currentGameState
    } else {
      val input = readLine().split(" ")

      input.head match {
        //create new board
        case "M" | "m" =>
          val updatedGameState = Game.createNewBoard

          println(View.render(updatedGameState.canvas) + "\n\n")

          initialiseGame(updatedGameState)

        //place ship
        case "P" | "p" =>
          val Array(_, startX, startY, endX, endY) = input
          val updatedGameState = Game.placeShip(startX, startY, endX, endY, currentGameState)

          println(View.render(updatedGameState.canvas) + "\n\n")

          initialiseGame(updatedGameState)

        //in all other cases, try again
        case _ => initialiseGame(currentGameState)
      }
    }
  }
}
