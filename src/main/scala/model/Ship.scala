package model

case class ShipsPlaced(
                       submarine: Boolean = false,
                       destroyer: Boolean = false,
                       cruiser: Boolean = false,
                       battleship: Boolean = false
                     )

class Ship(coordinates: Coordinates, gameState: GameState) {
  protected val exceedsMaxNumber = false

  protected val validLocation: Boolean = {
    val fullShipCoordinates: Seq[(Int, Int)] =
      for {
        i <- coordinates.start.x to coordinates.end.x
        j <- coordinates.start.y to coordinates.end.y
      } yield (i, j)

    val neighbours: Set[(Int, Int)] = Game.calculateNeighbours(gameState.canvas)

    val invalidLocation: Seq[Boolean] =
      for {
        fullShipCoordinates <- fullShipCoordinates
      } yield neighbours.contains(fullShipCoordinates)

    if (invalidLocation.contains(true))
      false
    else
      true
  }

  protected def updateHorizontalShip(canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    var newCanvas = canvas
    for (i <- coordinates.start.x to coordinates.end.x) {
      newCanvas = newCanvas.transform {
        case (x, _) if x == (i, coordinates.start.y) => 1
        case (_, v) if v == 1 => 1
        case _ => 0
      }
    }
    newCanvas
  }

  protected def updateVerticalShip(canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    var newCanvas = canvas
    for (i <- coordinates.start.y to coordinates.end.y) {
      newCanvas = newCanvas.transform {
        case (x, _) if x == (coordinates.start.x, i) => 1
        case (_, v) if v == 1 => 1
        case _ => 0
      }
    }
    newCanvas
  }

  def updateLocation(): GameState = gameState
}

object Ship {
  def apply(coordinates: Coordinates, gameState: GameState): Ship = {
    val submarine = coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
    val destroyer =
    coordinates.end.x - coordinates.start.x == 1 && coordinates.end.y - coordinates.start.y == 0 |
      coordinates.end.y - coordinates.start.y == 1 && coordinates.end.x - coordinates.start.x == 0
    val cruiser =
    coordinates.end.x - coordinates.start.x == 2 && coordinates.end.y - coordinates.start.y == 0 |
      coordinates.end.y - coordinates.start.y == 2 && coordinates.end.x - coordinates.start.x == 0
    val battleship =
      coordinates.end.x - coordinates.start.x == 3 && coordinates.end.y - coordinates.start.y == 0 |
        coordinates.end.y - coordinates.start.y == 3 && coordinates.end.x - coordinates.start.x == 0

    coordinates match {
      case _ if submarine =>
        new Submarine(coordinates, gameState)
      case _ if destroyer => new Destroyer(coordinates, gameState)
      case _ if cruiser => new Cruiser(coordinates, gameState)
      case _ if battleship => new Battleship(coordinates, gameState)
      case _ => new Ship(coordinates, gameState)
    }
  }
}
