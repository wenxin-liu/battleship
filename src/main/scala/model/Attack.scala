package model

import Common._

case class ActiveAttacker(playerOne: Boolean, playerTwo: Boolean)
case class PlayerOne(wins: Boolean)
case class PlayerTwo(wins: Boolean)

class Attack(inputX: String, inputY: String, canvas: Map[(Int, Int), Int]) extends Canvas(canvas) {
  private val x: Int = calculateCoordinates(inputX)
  private val y: Int = calculateCoordinates(inputY)

  def attack: Map[(Int, Int), Int] = {
    canvas.transform {
      case (k, v) if k == (x, y) && v == 1 => 2
      case (k, v) if k == (x, y) && v == 0 => 3
      case (_, v) => v
    }
  }
}
