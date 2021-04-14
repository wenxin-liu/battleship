package model

class Ship(startX: Int, startY: Int, endX: Int, endY: Int, canvas: Map[(Int, Int), Int]) {
  private val coordinates = Coordinates(Start(x = startX-1, y = startY-1), End(x = endX-1, y = endY-1))

  private val submarine =
    coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
  private val shipIsHorizontal =
    coordinates.end.x - coordinates.start.x != 0 && coordinates.end.y - coordinates.start.y == 0
  private val shipIsVertical =
    coordinates.end.x - coordinates.start.x == 0 && coordinates.end.y - coordinates.start.y != 0

  def validate: Boolean = {
    coordinates match {
      case _ if submarine || shipIsHorizontal || shipIsVertical => true
      case _ => false
    }
  }

  def updateLocation: Map[(Int, Int), Int] = {
    var newCanvas: Map[(Int, Int), Int] = canvas

    if (submarine)
      canvas.transform {
        case (x, _) if x == (coordinates.start.x, coordinates.start.y) => 1
        case _ => 0
      }
    else if (shipIsHorizontal) {
      for (i <- coordinates.start.x to coordinates.end.x) {
        newCanvas = newCanvas.transform {
          case (x, _) if x == (i, coordinates.start.y) => 1
          case (_, v) if v == 1 => 1
          case _ => 0
        }
      }
      newCanvas
    } else if (shipIsVertical) {
      for (i <- coordinates.start.y to coordinates.end.y) {
        newCanvas = newCanvas.transform {
          case (x, _) if x == (coordinates.start.x, i) => 1
          case (_, v) if v == 1 => 1
          case _ => 0
        }
      }
      newCanvas
    } else canvas
  }
}
