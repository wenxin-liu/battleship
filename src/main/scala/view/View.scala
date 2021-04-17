package view

object View {
  def Render(canvas: Map[(Int, Int), Int]): String = {
    val topBorder: String = "\u0020\u0020\u0020\u0020" + (for {i <- 'A' to 'J'} yield i).mkString("\u0020\u0020")

    val row: IndexedSeq[String] = for {
      i <- 0 to 9
    } yield "\u0020\u0020" + (for { j <- 0 to 9 } yield canvas(i, j)).mkString("\u0020\u0020")

    val middleRows: String = (for {
      i <- 0 to 8
    } yield s"\u0020${row.zipWithIndex(i)._2+1}${row.zipWithIndex(i)._1}").mkString("\n","\n","\n")

    val lastRow: String = s"${row.zipWithIndex(9)._2+1}${row.zipWithIndex(9)._1}"

    topBorder + middleRows + lastRow
  }
}
