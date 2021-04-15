package model

object Submarine
  extends Ship (startX = 0, startY = 0, endX = 0, endY = 0, canvas = Map()) {
  def set(value: Boolean): Unit = IsPlaced = value

  def get: Boolean = IsPlaced

  def updateSubmarine(coordinates: Coordinates, canvas: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    var newCanvas = canvas

    newCanvas = newCanvas.transform {
      case (x, _) if x == (coordinates.start.x, coordinates.start.y) => 1
      case _ => 0
    }

    set(true)

    newCanvas
  }
}
