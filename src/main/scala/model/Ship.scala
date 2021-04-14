package model

class Ship(startX: Int, startY: Int, endX: Int, endY: Int, canvas: List[List[Int]]) {
  private val lineIsHorizontal = endX - startX != 0 && endY - startY == 0
  private val lineIsVertical = endX - startX == 0 && endY - startY != 0

  def updateLocation: List[List[Int]] = {
    var newCanvas: List[List[Int]] = canvas

    if (lineIsHorizontal) {
      for (i <- startX to endX) {
        newCanvas = canvas.updated(startY, newCanvas(startY).updated(i, 1))
      }
      newCanvas
    } else if (lineIsVertical) {
      for (i <- startY to endY) {
        newCanvas = newCanvas.updated(i, canvas(i).updated(startX, 1))
      }
      newCanvas
    } else canvas
  }
}
