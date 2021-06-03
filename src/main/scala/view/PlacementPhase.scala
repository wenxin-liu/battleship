package view

object PlacementPhase {
  def printCanvas(canvas: Map[(Int, Int), Int]): Unit = println(convertCanvasType(canvas)+ "\n\n")

  def convertCanvasType(canvas: Map[(Int, Int), Int]): String = {
    val topBorder: String = "\u0020\u0020\u0020\u0020" + (for {i <- 'A' to 'J'} yield i).mkString("\u0020\u0020")

    val row: IndexedSeq[String] = for {
      i <- 0 to 9
    } yield "\u0020\u0020" + (for {j <- 0 to 9} yield canvas(j, i)).mkString("\u0020\u0020")

    val middleRows: String = (for {
      i <- 0 to 8
    } yield s"\u0020${row.zipWithIndex(i)._2 + 1}${row.zipWithIndex(i)._1}").mkString("\n", "\n", "\n")

    val lastRow: String = s"${row.zipWithIndex(9)._2 + 1}${row.zipWithIndex(9)._1}"

    topBorder + middleRows + lastRow
  }

  def switchPlayer(): Unit = {
    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n")
      Thread.sleep(900)
    }
  }

  def playerOneWelcomeMessage(): Unit = println("Player 1, please create a new board and place your ships")

  def playerTwoWelcomeMessage(): Unit = println("Player 2, please create a new board and place your ships")

  def welcomeMessage(): Unit = println(title)

  private val title: String =
    """
      |██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░
      |██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗
      |██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝
      |██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░
      |██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░
      |╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░
      |""".stripMargin + "\n\n\n"
}
