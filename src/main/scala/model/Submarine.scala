package model

class Submarine(coordinates: Coordinates, gameState: GameState) extends Ship(coordinates, gameState) {
  private val submarine: Boolean =
    coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
  protected override val exceedsMaxNumber: Boolean = gameState.shipsPlaced.submarine

  override def updateLocation(): GameState = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => gameState
      case _ if submarine =>
        var newCanvas = gameState.canvas

        newCanvas = newCanvas.transform {
          case (x, _) if x == (coordinates.start.x, coordinates.start.y) => 1
          case (_, v) if v == 1 => 1
          case _ => 0
        }

        val submarinePlaced = gameState.shipsPlaced.copy(submarine = true)

        GameState(canvas = newCanvas, shipsPlaced = submarinePlaced)
      case _ => gameState
    }
  }
}

//object Submarine {
//  private var isPlaced = false
//
//  def set(value: Boolean): Unit = isPlaced = value
//
//  def get: Boolean = isPlaced
//}