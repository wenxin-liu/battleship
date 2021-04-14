package model

class Canvas {
  def drawCanvas =
    List(
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    )

  def updateCanvas(start: (Int, Int), end: (Int, Int), canvas: List[List[Int]]): List[List[Int]] = {
    val lineIsHorizontal = end._1 - start._1 != 0 && end._2 - start._2 == 0
    val lineIsVertical = end._1 - start._1 == 0 && end._2 - start._2 != 0

    if (start == end)
      canvas.updated(start._2-1, canvas(start._2-1).updated(start._1-1, 1))
    else if (start != end) {
      if (lineIsHorizontal) {
        var newCanvas: List[List[Int]] = canvas
        for (i <- start._1 - 1 to end._1 - 1) {
          newCanvas = updateHorizontalShip(i, start._2-1, newCanvas)
        }
        newCanvas
      } else if (lineIsVertical) {
        ???
      } else canvas
    } else canvas
}
  def updateHorizontalShip(x: Int, y: Int, canvas: List[List[Int]]): List[List[Int]] = {
    canvas.updated(y, canvas(y).updated(x, 1))
  }
}