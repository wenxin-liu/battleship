package model

class Submarine(coordinates: Coordinates, canvas: Map[(Int, Int), Int]) extends Ship(coordinates, canvas) {
  private val submarine: Boolean =
    coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
  protected override val exceedsMaxNumber: Boolean = Submarine.get

  override def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validLocation | exceedsMaxNumber => canvas
      case _ if submarine =>
        var newCanvas = canvas

        newCanvas = newCanvas.transform {
          case (x, _) if x == (coordinates.start.x, coordinates.start.y) => 1
          case _ => 0
        }

        Submarine.set(true)

        newCanvas
      case _ => canvas
    }
  }
}

object Submarine {
  private var isPlaced = false

  def set(value: Boolean): Unit = isPlaced = value

  def get: Boolean = isPlaced
}