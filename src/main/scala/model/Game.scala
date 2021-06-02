package model

import model.Canvas._
import model.Common._

case class ActivePlayer(
                         playerOne: Boolean = false,
                         playerTwo: Boolean = false
                       )

case class AttackPhase(
                        playerOneCanvas: Map[(Int, Int), Int],
                        playerTwoCanvas: Map[(Int, Int), Int],
                        activePlayer: ActivePlayer
                      )

case class PlacementPhase(
                           canvas: Map[(Int, Int), Int] = Map(),
                           shipsPlaced: ShipsPlaced = ShipsPlaced()
                         )

object Game {
  def createNewBoard: PlacementPhase = PlacementPhase(canvas = newCanvas)

  def placeShip(
                 startX: String,
                 startY: String,
                 endX: String,
                 endY: String,
                 gameState: PlacementPhase
               ): PlacementPhase = {
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

  def attack(coordinateX: String, coordinateY: String, gameState: AttackPhase): AttackPhase = {
    val x: Int = calculateCoordinates(coordinateX)
    val y: Int = calculateCoordinates(coordinateY)

    val updateCanvas = (canvas: Map[(Int, Int), Int]) =>
      canvas.transform {
        case (k, v) if k == (x, y) && v == 1 => 2
        case (k, v) if k == (x, y) && v == 0 => 3
        case (_, v) => v
      }

    gameState match {
      case AttackPhase(playerOneCanvas, playerTwoCanvas, ActivePlayer(playerOne, playerTwo))
        if playerOne && !playerTwo =>
        AttackPhase(playerOneCanvas, updateCanvas(playerTwoCanvas), ActivePlayer(playerTwo = true))
      case AttackPhase(playerOneCanvas, playerTwoCanvas, ActivePlayer(playerOne, playerTwo))
        if !playerOne && playerTwo =>
        AttackPhase(updateCanvas(playerOneCanvas), playerTwoCanvas, ActivePlayer(playerOne = true))
      case _ => gameState
    }
  }
}
