package model

class Battleship(coordinates: Coordinates, gameState: GameState) extends Ship(coordinates, gameState) {
  private val horizontalBattleship =
    coordinates.end.x - coordinates.start.x == 3 && coordinates.end.y - coordinates.start.y == 0
  private val verticalBattleship =
    coordinates.end.y - coordinates.start.y == 3 && coordinates.end.x - coordinates.start.x == 0
  protected override val exceedsMaxNumber: Boolean = gameState.shipsPlaced.battleship

  override def updateLocation(): GameState = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => gameState
      case _ if horizontalBattleship =>
        val newCanvas = updateHorizontalShip(gameState.canvas)
        val battleshipPlaced = gameState.shipsPlaced.copy(battleship = true)

        GameState(canvas = newCanvas, shipsPlaced = battleshipPlaced)
      case _ if verticalBattleship =>
        val newCanvas = updateVerticalShip(gameState.canvas)
        val battleshipPlaced = gameState.shipsPlaced.copy(battleship = true)

        GameState(canvas = newCanvas, shipsPlaced = battleshipPlaced)
      case _ => gameState
    }
  }
}

//object Battleship {
//  private var isPlaced = false
//
//  def set(value: Boolean): Unit = isPlaced = value
//
//  def get: Boolean = isPlaced
//}
