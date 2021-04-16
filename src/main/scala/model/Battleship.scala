package model

class Battleship(coordinates: Coordinates, canvas: Map[(Int, Int), Int]) extends Ship(coordinates, canvas) {
  private val horizontalBattleship =
    coordinates.end.x - coordinates.start.x == 3 && coordinates.end.y - coordinates.start.y == 0
  private val verticalBattleship =
    coordinates.end.y - coordinates.start.y == 3 && coordinates.end.x - coordinates.start.x == 0
  protected override val exceedsMaxNumber: Boolean = Battleship.get

  override def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => canvas
      case _ if horizontalBattleship =>
        val newCanvas = updateHorizontalShip(canvas)
        Battleship.set(true)
        newCanvas
      case _ if verticalBattleship =>
        val newCanvas = updateVerticalShip(canvas)
        Battleship.set(true)
        newCanvas
      case _ => canvas
    }
  }
}

object Battleship {
  private var isPlaced = false

  def set(value: Boolean): Unit = isPlaced = value

  def get: Boolean = isPlaced
}
