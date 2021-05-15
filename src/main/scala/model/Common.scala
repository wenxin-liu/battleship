package model

object Common {
  def calculateCoordinates(character: String): Int = character match {
    case "A" | "a" | "1" => 0
    case "B" | "b" | "2" => 1
    case "C" | "c" | "3" => 2
    case "D" | "d" | "4" => 3
    case "E" | "e" | "5" => 4
    case "F" | "f" | "6" => 5
    case "G" | "g" | "7" => 6
    case "H" | "h" | "8" => 7
    case "I" | "i" | "9" => 8
    case "J" | "j" | "10" => 9
  }
}
