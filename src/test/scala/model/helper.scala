package model

object helper {
  def cleanTestState(): Unit = {
    Destroyer.set(false)
    Cruiser.set(false)
    Battleship.set(false)
  }
}
