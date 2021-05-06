package model

case class ActiveAttacker(playerOne: Boolean, playerTwo: Boolean)
case class PlayerOne(wins: Boolean)
case class PlayerTwo(wins: Boolean)

//TODO: put the zero indexing into calculateCoordinates, don't have it in multiple places in the codebase
class Attack(inputX: String, inputY: String, canvas: Map[(Int, Int), Int]) extends Canvas(canvas) {
  private val x: Int = calculateCoordinates(inputX)-1
  private val y: Int = inputY.toInt-1

  def attack: Map[(Int, Int), Int] = {
    canvas.transform {
      case (k, v) if k == (x, y) && v == 1 => 2
      case (k, v) if k == (x, y) && v == 0 => 3
      case (_, v) => v
    }
  }
}
