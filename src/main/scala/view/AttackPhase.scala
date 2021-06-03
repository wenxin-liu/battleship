package view

import model.{ActivePlayer, PlayerOne, PlayerTwo}

object AttackPhase {
  def renderPreAttack(canvas: Map[(Int, Int), Int], activePlayer: ActivePlayer): Unit = {
    activePlayer match {
      case PlayerOne => println("Player 1, please attack one coordinate on player 2's board:\n\n")
      case PlayerTwo => println("Player 2, please attack one coordinate on player 1's board:\n\n")
      case _ => println("error")
    }

    println(renderCanvas(canvas) + "\n\n")
  }

  def renderPostAttack(canvas: Map[(Int, Int), Int]): Unit = {
    println(renderCanvas(canvas) + "\n")
    Thread.sleep(5000)
    println("\n\n")
  }

  def playerOneWinMessage(): Unit = println(playerOneWins)

  def playerTwoWinMessage(): Unit = println(playerTwoWins)

  def renderCanvas(canvas: Map[(Int, Int), Int]): String = {
    val topBorder: String = "\u0020\u0020\u0020\u0020" + (for {i <- 'A' to 'J'} yield i).mkString("\u0020\u0020")

    val row: IndexedSeq[String] = for {
      i <- 0 to 9
    } yield "\u0020\u0020" + (for {j <- 0 to 9} yield canvas(j, i)).mkString("\u0020\u0020")

    val anonymisedRow: IndexedSeq[String] =
      row.map {
        row =>
          row
            .replace("0", "-")
            .replace("1", "-")
            .replace("2", "X")
            .replace("3", "o")
      }

    val middleRows: String = (for {
      i <- 0 to 8
    } yield s"\u0020${anonymisedRow.zipWithIndex(i)._2 + 1}${anonymisedRow.zipWithIndex(i)._1}").mkString("\n", "\n", "\n")

    val lastRow: String = s"${anonymisedRow.zipWithIndex(9)._2 + 1}${anonymisedRow.zipWithIndex(9)._1}"

    topBorder + middleRows + lastRow
  }

  private val playerOneWins: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin

  private val playerTwoWins: String =
    """
      |██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗
      |██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝
      |██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ░░███╔═╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░
      |██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ██╔══╝░░  ░░████╔═████║░██║██║╚████║░╚═══██╗
      |██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝
      |╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░
      |""".stripMargin
}
