package controller

import controller.InitialiseGame.initialiseGame
import controller.PlayGame.playGame
import model.ActivePlayer

object Controller {
  def apply: Any = {
    println(title)

    println("Player 1, please create a new board and place your ships")
    val playerOneCanvas = initialiseGame(Map(), 0)
    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n"); Thread.sleep(900)
    }

    println("Player 2, please create a new board and place your ships")
    val playerTwoCanvas = initialiseGame(Map(), 0)
    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n"); Thread.sleep(900)
    }

    playGame(playerOneCanvas, playerTwoCanvas, ActivePlayer(playerOne = true, playerTwo = false))
  }

  val title: String =
    """
      |██████╗░░█████╗░████████╗████████╗██╗░░░░░███████╗░██████╗██╗░░██╗██╗██████╗░
      |██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║░░░░░██╔════╝██╔════╝██║░░██║██║██╔══██╗
      |██████╦╝███████║░░░██║░░░░░░██║░░░██║░░░░░█████╗░░╚█████╗░███████║██║██████╔╝
      |██╔══██╗██╔══██║░░░██║░░░░░░██║░░░██║░░░░░██╔══╝░░░╚═══██╗██╔══██║██║██╔═══╝░
      |██████╦╝██║░░██║░░░██║░░░░░░██║░░░███████╗███████╗██████╔╝██║░░██║██║██║░░░░░
      |╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚══════╝╚═════╝░╚═╝░░╚═╝╚═╝╚═╝░░░░░
      |""".stripMargin + "\n\n\n"
}
