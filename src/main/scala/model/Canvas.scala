package model

case class Coordinates(start: Start, end: End)
case class Start(x: Int, y: Int)
case class End(x: Int, y: Int)

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
    val coordinates = Coordinates(Start(x = start._1-1, y = start._2-1), End(x = end._1-1, y = end._2-1))
    val submarine =
      coordinates.start.x == coordinates.end.x && coordinates.start.y == coordinates.end.y
    val lineIsHorizontal =
      coordinates.end.x - coordinates.start.x != 0 && coordinates.end.y - coordinates.start.y == 0
    val lineIsVertical =
      coordinates.end.x - coordinates.start.x == 0 && coordinates.end.y - coordinates.start.y != 0

    coordinates match {
      case _ if submarine  =>
        canvas.updated(coordinates.start.y, canvas(coordinates.start.y).updated(coordinates.start.x, 1))
      case _ if lineIsHorizontal =>
        val ship = new Ship(coordinates.start.x, coordinates.start.y, coordinates.end.x, coordinates.start.y, canvas)
        ship.updateLocation
      case _ if lineIsVertical =>
        val ship = new Ship(coordinates.start.x, coordinates.start.y, coordinates.start.x, coordinates.end.y, canvas)
        ship.updateLocation
      case _ => canvas
    }
  }
}