package model

class Cruiser(coordinates: Coordinates, canvas: Map[(Int, Int), Int]) extends Ship(coordinates, canvas) {
  private val horizontalCruiser =
    coordinates.end.x - coordinates.start.x == 2 && coordinates.end.y - coordinates.start.y == 0
  private val verticalCruiser =
    coordinates.end.y - coordinates.start.y == 2 && coordinates.end.x - coordinates.start.x == 0
  protected override val exceedsMaxNumber: Boolean = Cruiser.get

  override def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => canvas
      case _ if horizontalCruiser =>
        val newCanvas = updateHorizontalShip(canvas)
        Cruiser.set(true)
        newCanvas
      case _ if verticalCruiser =>
        val newCanvas = updateVerticalShip(canvas)
        Cruiser.set(true)
        newCanvas
      case _ => canvas
    }
  }
}

object Cruiser {
  private var isPlaced = false

  def set(value: Boolean): Unit = isPlaced = value

  def get: Boolean = isPlaced
}
