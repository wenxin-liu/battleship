package view

object View {
  def render(canvas: Map[(Int, Int), Int]): String = {
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

  def renderAttack(canvas: Map[(Int, Int), Int]): String = {
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
            .replace("3", ".")
      }

    val middleRows: String = (for {
      i <- 0 to 8
    } yield s"\u0020${anonymisedRow.zipWithIndex(i)._2 + 1}${anonymisedRow.zipWithIndex(i)._1}").mkString("\n", "\n", "\n")

    val lastRow: String = s"${anonymisedRow.zipWithIndex(9)._2 + 1}${anonymisedRow.zipWithIndex(9)._1}"

    topBorder + middleRows + lastRow
  }
}
