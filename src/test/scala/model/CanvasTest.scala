package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CanvasTest extends AnyFlatSpec with Matchers{
  "A canvas" should "be 10 by 10" in {
    val canvas = new Canvas

    canvas.drawCanvas shouldBe List(10, 10)
  }
}
