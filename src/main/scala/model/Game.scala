package model

import model.Canvas._
import model.Common._

sealed trait ActivePlayer
case object PlayerOne extends ActivePlayer
case object PlayerTwo extends ActivePlayer

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
      case AttackPhase(playerOneCanvas, playerTwoCanvas, activePlayer) if activePlayer == PlayerOne =>
        AttackPhase(
          playerOneCanvas = playerOneCanvas,
          playerTwoCanvas = updateCanvas(playerTwoCanvas),
          activePlayer = PlayerTwo
        )

      case AttackPhase(playerOneCanvas, playerTwoCanvas, activePlayer) if activePlayer == PlayerTwo =>
        AttackPhase(
          playerOneCanvas = updateCanvas(playerOneCanvas),
          playerTwoCanvas = playerTwoCanvas,
          activePlayer = PlayerOne
        )

      case _ => gameState
    }
  }
}
