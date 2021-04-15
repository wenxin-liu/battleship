package model

sealed trait ShipType
case object Submarines extends ShipType
case object Destroyers extends ShipType
case object Cruisers extends ShipType
case object Battleships extends ShipType

class Ship(startX: Int, startY: Int, endX: Int, endY: Int, canvas: Map[(Int, Int), Int]) {
  var IsPlaced = false
  private val coordinates = Coordinates(Start(x = startX - 1, y = startY - 1), End(x = endX - 1, y = endY - 1))

  private val submarine =
    coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
  private val shipIsHorizontal =
    coordinates.end.x - coordinates.start.x != 0 && coordinates.end.y - coordinates.start.y == 0
  private val shipIsVertical =
    coordinates.end.x - coordinates.start.x == 0 && coordinates.end.y - coordinates.start.y != 0
  private val horizontalDestroyer =
    coordinates.end.x - coordinates.start.x == 1 && coordinates.end.y - coordinates.start.y == 0
  private val verticalDestroyer =
    coordinates.end.y - coordinates.start.y == 1 && coordinates.end.x - coordinates.start.x == 0
  private val horizontalCruiser =
    coordinates.end.x - coordinates.start.x == 2 && coordinates.end.y - coordinates.start.y == 0
  private val verticalCruiser =
    coordinates.end.y - coordinates.start.y == 2 && coordinates.end.x - coordinates.start.x == 0
  private val horizontalBattleship =
    coordinates.end.x - coordinates.start.x == 3 && coordinates.end.y - coordinates.start.y == 0
  private val verticalBattleship =
    coordinates.end.y - coordinates.start.y == 3 && coordinates.end.x - coordinates.start.x == 0

  private val validType: Boolean = {
    coordinates match {
      case _ if submarine || shipIsHorizontal || shipIsVertical => true
      case _ => false
    }
  }

  private val validLocation: Boolean = {
    val fullShipCoordinates: Seq[(Int, Int)] =
      for {
        i <- coordinates.start.x to coordinates.end.x
        j <- coordinates.start.y to coordinates.end.y
      } yield (i, j)

    val neighbours: Set[(Int, Int)] = Neighbours.calculateNeighbours(canvas)

    val invalidLocation: Seq[Boolean] =
      for {
        fullShipCoordinates <- fullShipCoordinates
      } yield neighbours.contains(fullShipCoordinates)

    if (invalidLocation.contains(true))
      false
    else
      true
  }
  private val tooLong = if (
    coordinates.end.x - coordinates.start.x >= 4 && coordinates.end.y - coordinates.start.y == 0
      | coordinates.end.x - coordinates.start.x == 0 && coordinates.end.y - coordinates.start.y >= 4
  ) true
  else
    false

  private def updateHorizontalShip(canvas: Map[(Int, Int), Int]) = {
    var newCanvas = canvas

    for (i <- coordinates.start.x to coordinates.end.x) {
      newCanvas = newCanvas.transform {
        case (x, _) if x == (i, coordinates.start.y) => 1
        case (_, v) if v == 1 => 1
        case _ => 0
      }
    }

    if (coordinates.end.x - coordinates.start.x == 1) {
      Destroyer.set(true)
    } else if (coordinates.end.x - coordinates.start.x == 2) {
      Cruiser.set(true)
    } else if (coordinates.end.x - coordinates.start.x == 3) {
      Battleship.set(true)
    }

    newCanvas
  }

  private def updateVerticalShip(canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    var newCanvas = canvas

    for (i <- coordinates.start.y to coordinates.end.y) {
      newCanvas = newCanvas.transform {
        case (x, _) if x == (coordinates.start.x, i) => 1
        case (_, v) if v == 1 => 1
        case _ => 0
      }
    }

    if (coordinates.end.y - coordinates.start.y == 1) {
      Destroyer.set(true)
    } else if (coordinates.end.y - coordinates.start.y == 2) {
      Cruiser.set(true)
    } else if (coordinates.end.y - coordinates.start.y == 3) {
      Battleship.set(true)
    }

    newCanvas
  }

  private def shipType = {
    coordinates match {
      case _ if submarine => Submarines
      case _ if horizontalDestroyer | verticalDestroyer => Destroyers
      case _ if horizontalCruiser | verticalCruiser => Cruisers
      case _ if horizontalBattleship | verticalBattleship => Battleships
      case _ => new Exception("invalid ship type")
    }
  }

  def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validType | !validLocation | tooLong =>
        canvas
      case _ if shipType == Submarines && Submarine.get => canvas
      case _ if shipType == Destroyers && Destroyer.get => canvas
      case _ if shipType == Cruisers && Cruiser.get => canvas
      case _ if shipType == Battleships && Battleship.get => canvas
      case _ if submarine => Submarine.updateSubmarine(coordinates, canvas)
      case _ if shipIsHorizontal => updateHorizontalShip(canvas)
      case _ if shipIsVertical => updateVerticalShip(canvas)
      case _ => canvas
    }
  }
}
