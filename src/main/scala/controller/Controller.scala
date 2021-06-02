package controller

import controller.InitialiseGame.initialiseGame
import controller.PlayGame.playGame
import model.{ActivePlayer, AttackPhase, PlacementPhase}

object Controller {
  def apply: Any = {
    println(title)

    println("Player 1, please create a new board and place your ships")
    val playerOneState = initialiseGame(currentGameState = PlacementPhase())

    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n")
      Thread.sleep(900)
    }

    println("Player 2, please create a new board and place your ships")
    val playerTwoState = initialiseGame(currentGameState = PlacementPhase())

    Thread.sleep(5000)
    for {_ <- 1 to 15} {
      println("\n")
      Thread.sleep(900)
    }

    playGame(
      AttackPhase(
        playerOneCanvas = playerOneState.canvas,
        playerTwoCanvas = playerTwoState.canvas,
        activePlayer = ActivePlayer(playerOne = true)
      )
    )
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
