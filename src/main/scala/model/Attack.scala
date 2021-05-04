package model

class Attack(x: Int, y: Int, canvas: Map[(Int, Int), Int]){
  def attack: Map[(Int, Int), Int] = {
    canvas.transform {
      case (k, v) if k == (x, y) && v == 1 => 2
      case (k, v) if k == (x, y) && v == 0 => 3
      case (_, v) => v
    }
  }
}
