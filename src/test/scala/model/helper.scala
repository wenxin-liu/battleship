package model

object helper {
  def cleanTestState(): Unit = {
    Submarine.set(false)
    Destroyer.set(false)
    Cruiser.set(false)
    Battleship.set(false)
  }
}
