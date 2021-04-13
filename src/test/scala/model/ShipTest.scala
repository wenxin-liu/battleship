package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShipTest extends AnyFlatSpec with Matchers{
  "A submarine" should "be 1 by 1" in {
    ship.oneByOne shouldBe List((1, 1))
  }

  "A destroyer" should "be 2 by 1 when horizontal" in {
    ship.twoByOne shouldBe List((1, 1), (2, 1))
  }

  it should "be 1 by 2 when vertical" in {
    ship.oneByTwo shouldBe List((1, 1), (1, 2))
  }

  "A cruiser" should "be 3 by 1 when horizontal" in {
    ship.threeByOne shouldBe List((1, 1), (3, 1))
  }

  it should "be 1 by 3 when vertical" in {
    ship.oneByThree shouldBe List((1, 1), (1, 3))
  }

  "A battleship" should "be 4 by 1 when horizontal" in {
    ship.fourByOne shouldBe List((1, 1), (4, 1))
  }

  it should "be 1 by 4 when vertical" in {
    ship.oneByFour shouldBe List((1, 1), (1, 4))
  }

  lazy val ship = new Ship
}
