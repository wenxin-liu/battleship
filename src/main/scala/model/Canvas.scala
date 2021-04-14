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
    if (start == end)
      canvas.updated(start._2-1, canvas(start._2-1).updated(start._1-1, 1))
    else canvas
  }
}
