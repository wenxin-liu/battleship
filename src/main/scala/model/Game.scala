package model

import model.Canvas._
import model.Common._

case class GameState(activePlayer: ActivePlayer, playerOneShips: Int, playerTwoShips: Int)
case class ActivePlayer(playerOne: Boolean, playerTwo: Boolean)
case class PlayerOne(wins: Boolean)
case class PlayerTwo(wins: Boolean)

object Game {
  def createNewBoard: Map[(Int, Int), Int] = {
    Submarine.set(false)
    Destroyer.set(false)
    Cruiser.set(false)
    Battleship.set(false)

    newCanvas
  }

  def putShip(
               startX: String,
               startY: String,
               endX: String,
               endY: String,
               canvas: Map[(Int, Int), Int]
             ): Map[(Int, Int), Int] = {
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
      canvas
    )

    ship.updateLocation()
  }

  def Attack(inputX: String, inputY: String, canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    val x: Int = calculateCoordinates(inputX)
    val y: Int = calculateCoordinates(inputY)

    canvas.transform {
      case (k, v) if k == (x, y) && v == 1 => 2
      case (k, v) if k == (x, y) && v == 0 => 3
      case (_, v) => v
    }
  }
}
