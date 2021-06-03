# A command line Battleship game, in Scala


## Rules
This is a two-player game. Each player has a 10x10 canvas and four ships to place, each of a different type:

- 1 x submarine (1 cell on the canvas)
- 1 x destroyer (2 cells)
- 1 x cruiser (3 cells)
- 1 x battleship (4 cells)

A ship can be placed horizontally or vertically, but not diagonally. In addition, no ships can be in adjacent cells on the canvas.

Once placed, the players can attack the other player's canvas by issuing coordinates. A player wins by being the first to take down all the ships on the other player's canvas.


## How to run
Make sure SBT and Scala 2.13 are installed. 

Next, clone this repository locally.

From the project root:

`sbt run`


## Game commands

### Initialise game
* To make a new canvas:   `M`

### Ship Placement
During the ship placement phase, the board looks like:  

<pre>
    A  B  C  D  E  F  G  H  I  J  
 1  0  0  0  0  0  0  0  0  0  0  
 2  0  0  0  0  0  0  0  0  1  0  
 3  0  0  0  0  0  0  0  0  0  0  
 4  0  1  1  0  0  0  0  0  0  0  
 5  0  0  0  0  0  0  0  0  0  0  
 6  0  0  0  0  0  0  0  0  0  0  
 7  0  0  0  0  0  0  0  1  0  0  
 8  0  1  1  1  0  0  0  1  0  0  
 9  0  0  0  0  0  0  0  1  0  0  
10  0  0  0  0  0  0  0  1  0  0
</pre>

where a `0` represents an empty cell and a `1` represents a placed ship. Each player starts with an empty canvas only of `0`s. As each ship is placed, the cells where those ships occupy become `1`s.


* To place your 1-cell submarine: `P X1 Y1 X2 Y2` where X1=X2 and Y1=Y2. For example: `P A 1 A 1`  
    

* To place your 2-cell destroyer, 3-cell cruiser and 4-cell battleship: `P X1 Y1 X2 Y2`. For example: `P H 7 H 10` to place the 4-cell battleship vertically. Each ship can be horizontal or vertical, but not diagonal. Ships cannot be in adjacent cells.


### Attack
During the attack phase, the board looks like below:
<pre>
    A  B  C  D  E  F  G  H  I  J
 1  o  -  -  -  -  -  X  X  X  -
 2  -  -  -  -  -  -  -  -  -  -
 3  -  -  -  -  -  -  -  -  -  -
 4  -  -  -  -  -  -  -  -  -  -
 5  -  -  o  -  -  -  -  -  -  -
 6  -  -  -  -  -  o  -  -  -  -
 7  -  -  -  -  -  -  -  -  o  -
 8  -  -  -  -  -  -  -  -  -  -
 9  -  -  -  -  -  -  -  -  -  -
10  X  -  o  -  -  -  X  -  -  -
</pre>

where `X` represents a hit and `o` represents a miss.

* To attack a coordinate: `A X Y` e.g. `A B 1`


### Win
The game finishes when one player hits all the coordinates that have ships placed on the other player's board.