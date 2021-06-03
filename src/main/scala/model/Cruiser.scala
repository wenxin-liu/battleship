package model

class Cruiser(coordinates: Coordinates, gameState: PlacementPhase) extends Ship(coordinates, gameState) {
  private val horizontalCruiser =
    coordinates.end.x - coordinates.start.x == 2 && coordinates.end.y - coordinates.start.y == 0

  private val verticalCruiser =
    coordinates.end.y - coordinates.start.y == 2 && coordinates.end.x - coordinates.start.x == 0

  protected override val exceedsMaxNumber: Boolean = gameState.shipsPlaced.cruiser

  override def updateLocation(): PlacementPhase = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => gameState
      case _ if horizontalCruiser =>
        val newCanvas = updateHorizontalShip(gameState.canvas)
        val cruiserPlaced = gameState.shipsPlaced.copy(cruiser = true)

        PlacementPhase(canvas = newCanvas, shipsPlaced = cruiserPlaced)
      case _ if verticalCruiser =>
        val newCanvas = updateVerticalShip(gameState.canvas)
        val cruiserPlaced = gameState.shipsPlaced.copy(cruiser = true)

        PlacementPhase(canvas = newCanvas, shipsPlaced = cruiserPlaced)
      case _ => gameState
    }
  }
}
