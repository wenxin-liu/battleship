package model

class Destroyer(coordinates: Coordinates, gameState: PlacementPhase) extends Ship(coordinates, gameState) {
  private val horizontalDestroyer =
    coordinates.end.x - coordinates.start.x == 1 && coordinates.end.y - coordinates.start.y == 0

  private val verticalDestroyer =
    coordinates.end.y - coordinates.start.y == 1 && coordinates.end.x - coordinates.start.x == 0

  protected override val exceedsMaxNumber: Boolean = gameState.shipsPlaced.destroyer

  override def updateLocation(): PlacementPhase = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => gameState
      case _ if horizontalDestroyer =>
        val newCanvas = updateHorizontalShip(gameState.canvas)
        val destroyerPlaced = gameState.shipsPlaced.copy(destroyer = true)

        gameState.copy(canvas = newCanvas, shipsPlaced = destroyerPlaced)
      case _ if verticalDestroyer =>
        val newCanvas = updateVerticalShip(gameState.canvas)
        val destroyerPlaced = gameState.shipsPlaced.copy(destroyer = true)

        gameState.copy(canvas = newCanvas, shipsPlaced = destroyerPlaced)
      case _ => gameState
    }
  }
}