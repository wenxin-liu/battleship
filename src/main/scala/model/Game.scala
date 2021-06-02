package model

import model.Canvas._
import model.Common._

case class GameState(
                      canvas: Map[(Int, Int), Int] = Map(),
                      shipsPlaced: ShipsPlaced = ShipsPlaced()
                    )

case class ActivePlayer(playerOne: Boolean, playerTwo: Boolean)
case class PlayerOne(wins: Boolean)
case class PlayerTwo(wins: Boolean)

object Game {
  def createNewBoard: GameState = GameState(canvas = newCanvas)

  def placeShip(
               startX: String,
               startY: String,
               endX: String,
               endY: String,
               gameState: GameState
             ): GameState = {
    val ship = Ship(
      Coordinates(
        Start(
          x = calculateCoordinates(startX),
          y = calculateCoordinates(startY)
        ),
        End(
          x = calculateCoordinates(endX),
          y = calculateCoordinates(endY)
        )
      ),
      gameState
    )

    ship.updateLocation()
  }

  def calculateNeighbours(canvas: Map[(Int, Int), Int]): Set[(Int, Int)] = {
    val shipLocations: Set[(Int, Int)] = canvas.filter(_._2 == 1).keySet

    shipLocations.flatMap(cell =>
      Set(
        (cell._1 - 1, cell._2 - 1),
        (cell._1, cell._2 - 1),
        (cell._1 + 1, cell._2 - 1),
        (cell._1, cell._2 - 1),
        (cell._1, cell._2),
        (cell._1, cell._2 + 1),
        (cell._1 + 1, cell._2 - 1),
        (cell._1 + 1, cell._2),
        (cell._1 + 1, cell._2 + 1)
      )
    )
  }

  def attack(inputX: String, inputY: String, canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    val x: Int = calculateCoordinates(inputX)
    val y: Int = calculateCoordinates(inputY)

    canvas.transform {
      case (k, v) if k == (x, y) && v == 1 => 2
      case (k, v) if k == (x, y) && v == 0 => 3
      case (_, v) => v
    }
  }
}
