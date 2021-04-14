package model

class Ship(startX: Int, startY: Int, endX: Int, endY: Int, canvas: Map[(Int, Int), Int]) {
  private val submarine = startX == endX && startY == endY
  private val lineIsHorizontal = endX - startX != 0 && endY - startY == 0
  private val lineIsVertical = endX - startX == 0 && endY - startY != 0

  def updateLocation: Map[(Int, Int), Int] = {
    var newCanvas: Map[(Int, Int), Int] = canvas

    if (submarine)
      canvas.transform {
        case (x, _) if x == (startX, startY) => 1
        case _ => 0
      }
    else if (lineIsHorizontal) {
      for (i <- startX to endX) {
        newCanvas = newCanvas.transform {
          case (x, _) if x == (i, startY) => 1
          case (_, v) if v == 1 => 1
          case _ => 0
        }
      }
      newCanvas
    } else if (lineIsVertical) {
      for (i <- startY to endY) {
        newCanvas = newCanvas.transform {
          case (x, _) if x == (startX, i) => 1
          case (_, v) if v == 1 => 1
          case _ => 0
        }
      }
      newCanvas
    } else canvas
  }
}
