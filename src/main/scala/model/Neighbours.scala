package model

object Neighbours {
  def calculateNeighbours(canvas: Map[(Int, Int), Int]): Set[(Int, Int)] = {
    val shipLocations: Set[(Int, Int)] = canvas.filter(_._2 == 1).keySet

    shipLocations.flatMap(cell =>
      Set(
        (cell._1 - 1, cell._2 - 1),
        (cell._1, cell._2 - 1),
        (cell._1 + 1, cell._2 - 1),
        (cell._1, cell._2 - 1),
        (cell._1, cell._2),
        (cell._1, cell._2 + 1),
        (cell._1 + 1, cell._2 - 1),
        (cell._1 + 1, cell._2),
        (cell._1 + 1, cell._2 + 1)
      )
    )
  }
}