package controller

import controller.InitialiseGame.initialiseGame
import controller.PlayGame.playGame
import model.{ActivePlayer, GameState}

object Controller {
  def apply: Any = {
    println(title)

    println("Player 1, please create a new board and place your ships")
    val playerOneState = initialiseGame(currentGameState = GameState())

    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n")
      Thread.sleep(900)
    }

    println("Player 2, please create a new board and place your ships")
    val playerTwoState = initialiseGame(currentGameState = GameState())

    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n")
      Thread.sleep(900)
    }

    playGame(playerOneState.canvas, playerTwoState.canvas, ActivePlayer(playerOne = true, playerTwo = false))
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
