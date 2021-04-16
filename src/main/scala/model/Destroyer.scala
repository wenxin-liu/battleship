package model

class Destroyer(coordinates: Coordinates, canvas: Map[(Int, Int), Int]) extends Ship(coordinates, canvas) {
  private val horizontalDestroyer =
    coordinates.end.x - coordinates.start.x == 1 && coordinates.end.y - coordinates.start.y == 0
  private val verticalDestroyer =
    coordinates.end.y - coordinates.start.y == 1 && coordinates.end.x - coordinates.start.x == 0
  protected override val exceedsMaxNumber: Boolean = Destroyer.get

  override def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => canvas
      case _ if horizontalDestroyer =>
        val newCanvas = updateHorizontalShip(canvas)
        Destroyer.set(true)
        newCanvas
      case _ if verticalDestroyer =>
        val newCanvas = updateVerticalShip(canvas)
        Destroyer.set(true)
        newCanvas
      case _ => canvas
    }
  }
}

object Destroyer {
  private var isPlaced = false

  def set(value: Boolean): Unit = isPlaced = value

  def get: Boolean = isPlaced
}