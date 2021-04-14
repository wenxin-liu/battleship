package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NeighboursTest extends AnyFlatSpec with Matchers {
  "Neighbours" should "be correctly calculated" in {
    val canvas = new Canvas
    val canvasWithOneShip: Map[(Int, Int), Int] = canvas.updateCanvas((2, 2), (3, 2), canvas.drawCanvas)

    Neighbours.calculateNeighbours(canvasWithOneShip) shouldBe
      Set((0, 0), (1, 0), (2, 0), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2), (3, 0), (3, 1), (3, 2))
  }
}