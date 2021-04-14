package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CanvasTest extends AnyFlatSpec with Matchers{
  "A canvas" should "be 10 by 10" in {
    canvas.drawCanvas shouldBe
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
  }

  it should "have a submarine (1 cell) at 2,7" in {
    canvas.updateCanvas(start = (2, 7), end = (2, 7), canvas = canvas.drawCanvas) shouldBe
      List(
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      )
  }

  it should "have a battleship (4 cells) at 2,3 to 5,3" in {
    canvas.updateCanvas(start = (2, 3), end = (5, 3), canvas = canvas.drawCanvas) shouldBe
      List(
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 1, 1, 1, 1, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      )
  }

  it should "have a cruiser (3 cells) at 6,7 to 8,7" in {
    canvas.updateCanvas(start = (6, 7), end = (8, 7), canvas = canvas.drawCanvas) shouldBe
      List(
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 1, 1, 1, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      )
  }

  it should "have a destroyer (2 cells) at 2,3 to 2,4" in {
    canvas.updateCanvas(start = (2, 3), end = (2, 4), canvas = canvas.drawCanvas) shouldBe
      List(
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      )
  }

  it should "have a battleship (4 cells) at 10,7 to 10,10" in {
    canvas.updateCanvas(start = (10, 7), end = (10, 10), canvas = canvas.drawCanvas) shouldBe
      List(
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        List(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
      )
  }

  lazy val canvas = new Canvas
}
