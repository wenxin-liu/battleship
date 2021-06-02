package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DestroyerTest extends AnyFlatSpec with Matchers {
  it should "have a destroyer (2 cells) at 2,3 to 2,4" in {
    val ship = Ship(Coordinates(Start(x = 1, y = 2), End(x = 1, y = 3)), gameState = Game.createNewBoard)

    ship.updateLocation() shouldBe GameState(
      Map(
        (0, 0) -> 0, (1, 0) -> 0, (2, 0) -> 0, (3, 0) -> 0, (4, 0) -> 0, (5, 0) -> 0, (6, 0) -> 0, (7, 0) -> 0, (8, 0) -> 0, (9, 0) -> 0,
        (0, 1) -> 0, (1, 1) -> 0, (2, 1) -> 0, (3, 1) -> 0, (4, 1) -> 0, (5, 1) -> 0, (6, 1) -> 0, (7, 1) -> 0, (8, 1) -> 0, (9, 1) -> 0,
        (0, 2) -> 0, (1, 2) -> 1, (2, 2) -> 0, (3, 2) -> 0, (4, 2) -> 0, (5, 2) -> 0, (6, 2) -> 0, (7, 2) -> 0, (8, 2) -> 0, (9, 2) -> 0,
        (0, 3) -> 0, (1, 3) -> 1, (2, 3) -> 0, (3, 3) -> 0, (4, 3) -> 0, (5, 3) -> 0, (6, 3) -> 0, (7, 3) -> 0, (8, 3) -> 0, (9, 3) -> 0,
        (0, 4) -> 0, (1, 4) -> 0, (2, 4) -> 0, (3, 4) -> 0, (4, 4) -> 0, (5, 4) -> 0, (6, 4) -> 0, (7, 4) -> 0, (8, 4) -> 0, (9, 4) -> 0,
        (0, 5) -> 0, (1, 5) -> 0, (2, 5) -> 0, (3, 5) -> 0, (4, 5) -> 0, (5, 5) -> 0, (6, 5) -> 0, (7, 5) -> 0, (8, 5) -> 0, (9, 5) -> 0,
        (0, 6) -> 0, (1, 6) -> 0, (2, 6) -> 0, (3, 6) -> 0, (4, 6) -> 0, (5, 6) -> 0, (6, 6) -> 0, (7, 6) -> 0, (8, 6) -> 0, (9, 6) -> 0,
        (0, 7) -> 0, (1, 7) -> 0, (2, 7) -> 0, (3, 7) -> 0, (4, 7) -> 0, (5, 7) -> 0, (6, 7) -> 0, (7, 7) -> 0, (8, 7) -> 0, (9, 7) -> 0,
        (0, 8) -> 0, (1, 8) -> 0, (2, 8) -> 0, (3, 8) -> 0, (4, 8) -> 0, (5, 8) -> 0, (6, 8) -> 0, (7, 8) -> 0, (8, 8) -> 0, (9, 8) -> 0,
        (0, 9) -> 0, (1, 9) -> 0, (2, 9) -> 0, (3, 9) -> 0, (4, 9) -> 0, (5, 9) -> 0, (6, 9) -> 0, (7, 9) -> 0, (8, 9) -> 0, (9, 9) -> 0
      ),
      ShipsPlaced(destroyer = true)
    )
  }

  it should "only allow one destroyer (2x1) on the canvas (covers horizontal ship logic)" in {
    val firstDestroyer = Ship(Coordinates(Start(x = 0, y = 0), End(x = 1, y = 0)), gameState = Game.createNewBoard).updateLocation()
    val result = Ship(Coordinates(Start(x = 2, y = 2), End(x = 3, y = 2)), gameState = firstDestroyer).updateLocation()

    result shouldBe GameState(
      Map(
        (0, 0) -> 1, (1, 0) -> 1, (2, 0) -> 0, (3, 0) -> 0, (4, 0) -> 0, (5, 0) -> 0, (6, 0) -> 0, (7, 0) -> 0, (8, 0) -> 0, (9, 0) -> 0,
        (0, 1) -> 0, (1, 1) -> 0, (2, 1) -> 0, (3, 1) -> 0, (4, 1) -> 0, (5, 1) -> 0, (6, 1) -> 0, (7, 1) -> 0, (8, 1) -> 0, (9, 1) -> 0,
        (0, 2) -> 0, (1, 2) -> 0, (2, 2) -> 0, (3, 2) -> 0, (4, 2) -> 0, (5, 2) -> 0, (6, 2) -> 0, (7, 2) -> 0, (8, 2) -> 0, (9, 2) -> 0,
        (0, 3) -> 0, (1, 3) -> 0, (2, 3) -> 0, (3, 3) -> 0, (4, 3) -> 0, (5, 3) -> 0, (6, 3) -> 0, (7, 3) -> 0, (8, 3) -> 0, (9, 3) -> 0,
        (0, 4) -> 0, (1, 4) -> 0, (2, 4) -> 0, (3, 4) -> 0, (4, 4) -> 0, (5, 4) -> 0, (6, 4) -> 0, (7, 4) -> 0, (8, 4) -> 0, (9, 4) -> 0,
        (0, 5) -> 0, (1, 5) -> 0, (2, 5) -> 0, (3, 5) -> 0, (4, 5) -> 0, (5, 5) -> 0, (6, 5) -> 0, (7, 5) -> 0, (8, 5) -> 0, (9, 5) -> 0,
        (0, 6) -> 0, (1, 6) -> 0, (2, 6) -> 0, (3, 6) -> 0, (4, 6) -> 0, (5, 6) -> 0, (6, 6) -> 0, (7, 6) -> 0, (8, 6) -> 0, (9, 6) -> 0,
        (0, 7) -> 0, (1, 7) -> 0, (2, 7) -> 0, (3, 7) -> 0, (4, 7) -> 0, (5, 7) -> 0, (6, 7) -> 0, (7, 7) -> 0, (8, 7) -> 0, (9, 7) -> 0,
        (0, 8) -> 0, (1, 8) -> 0, (2, 8) -> 0, (3, 8) -> 0, (4, 8) -> 0, (5, 8) -> 0, (6, 8) -> 0, (7, 8) -> 0, (8, 8) -> 0, (9, 8) -> 0,
        (0, 9) -> 0, (1, 9) -> 0, (2, 9) -> 0, (3, 9) -> 0, (4, 9) -> 0, (5, 9) -> 0, (6, 9) -> 0, (7, 9) -> 0, (8, 9) -> 0, (9, 9) -> 0
      ),
      ShipsPlaced(destroyer = true)
    )
  }

  it should "only allow one destroyer (2x1) on the canvas (covers vertical ship logic)" in {
    val firstDestroyer = Ship(Coordinates(Start(x = 0, y = 0), End(x = 0, y = 1)), gameState = Game.createNewBoard).updateLocation()
    val result = Ship(Coordinates(Start(x = 2, y = 2), End(x = 2, y = 3)), gameState = firstDestroyer).updateLocation()

    result shouldBe GameState(
      Map(
        (0, 0) -> 1, (1, 0) -> 0, (2, 0) -> 0, (3, 0) -> 0, (4, 0) -> 0, (5, 0) -> 0, (6, 0) -> 0, (7, 0) -> 0, (8, 0) -> 0, (9, 0) -> 0,
        (0, 1) -> 1, (1, 1) -> 0, (2, 1) -> 0, (3, 1) -> 0, (4, 1) -> 0, (5, 1) -> 0, (6, 1) -> 0, (7, 1) -> 0, (8, 1) -> 0, (9, 1) -> 0,
        (0, 2) -> 0, (1, 2) -> 0, (2, 2) -> 0, (3, 2) -> 0, (4, 2) -> 0, (5, 2) -> 0, (6, 2) -> 0, (7, 2) -> 0, (8, 2) -> 0, (9, 2) -> 0,
        (0, 3) -> 0, (1, 3) -> 0, (2, 3) -> 0, (3, 3) -> 0, (4, 3) -> 0, (5, 3) -> 0, (6, 3) -> 0, (7, 3) -> 0, (8, 3) -> 0, (9, 3) -> 0,
        (0, 4) -> 0, (1, 4) -> 0, (2, 4) -> 0, (3, 4) -> 0, (4, 4) -> 0, (5, 4) -> 0, (6, 4) -> 0, (7, 4) -> 0, (8, 4) -> 0, (9, 4) -> 0,
        (0, 5) -> 0, (1, 5) -> 0, (2, 5) -> 0, (3, 5) -> 0, (4, 5) -> 0, (5, 5) -> 0, (6, 5) -> 0, (7, 5) -> 0, (8, 5) -> 0, (9, 5) -> 0,
        (0, 6) -> 0, (1, 6) -> 0, (2, 6) -> 0, (3, 6) -> 0, (4, 6) -> 0, (5, 6) -> 0, (6, 6) -> 0, (7, 6) -> 0, (8, 6) -> 0, (9, 6) -> 0,
        (0, 7) -> 0, (1, 7) -> 0, (2, 7) -> 0, (3, 7) -> 0, (4, 7) -> 0, (5, 7) -> 0, (6, 7) -> 0, (7, 7) -> 0, (8, 7) -> 0, (9, 7) -> 0,
        (0, 8) -> 0, (1, 8) -> 0, (2, 8) -> 0, (3, 8) -> 0, (4, 8) -> 0, (5, 8) -> 0, (6, 8) -> 0, (7, 8) -> 0, (8, 8) -> 0, (9, 8) -> 0,
        (0, 9) -> 0, (1, 9) -> 0, (2, 9) -> 0, (3, 9) -> 0, (4, 9) -> 0, (5, 9) -> 0, (6, 9) -> 0, (7, 9) -> 0, (8, 9) -> 0, (9, 9) -> 0
      ),
      ShipsPlaced(destroyer = true)
    )
  }
}
