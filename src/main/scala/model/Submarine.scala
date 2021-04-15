package model

class Submarine (coordinates: Coordinates, canvas: Map[(Int, Int), Int])
  extends Ship (coordinates.start.x, coordinates.start.y, coordinates.end.x, coordinates.end.y, canvas) {
  override def updateLocation(): Map[(Int, Int), Int] = {
    coordinates match {
      case _ if !validLocation => canvas
      case _ if shipType == Submarines && Submarine.get => canvas
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