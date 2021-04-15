package model

object Battleship
  extends Ship(startX = 0, startY = 0, endX = 0, endY = 0, canvas = Map()) {
  def set(value: Boolean): Unit = IsPlaced = value

  def get: Boolean = IsPlaced
}
