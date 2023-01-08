=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 45123620
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays: I used 2D arrays to hold the game board for 2048 as well as paint it in my repaint() method. The array is updated after each move is made while the previous board is held in a collection. This implementation of 2D arrays is appropriate since 2D arrays are mutable in place so changes can be made when moves are made in place.

  2. Collections: I used a stack to hold my moveHistory and scoreHistory to store each game board after each move and each store in order to implement an undo button. Since Collections are ordered and you are able to efficiently add and remove items, this makes it easier to undo (remove a board and past score). This makes a stack an appropriate implementation of Collections.

  3. JUnit Testing: I used JUnit testing to test the logic behind the code that feeds into the GUI. By separating the logic out, I was able to test various cases that might appear during a game of 2048. I constructed several possible boards that may exist during a game that represent various game states cases. Then, I tested whether a change made to the game state after a player's turn still represents what it should represent.

  4. File I/O: File I/O is used to save the game state in order for a user to leave the game and return to it later. The file will be deleted once the game is complete. The file is supposed to save the board and score history so the undo button continues to work.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

InstructionPanel: This class holds the JPanel pop-up window that appears when the game is first instantiated.

ScorePanel: This class holds the JPanel that  creates the JLabels displaying the title and scores at the top of the window.

Direction: The direction class contains enums that correspond to keyPresses. This class simply enumerates the various directions the tiles may slide.

Game2048: This class holds the game logic or the model for the game. This class performs all the operations for the GameBoard class so that the repaint method works.

GameBoard: This class constructs the game board using the Java Swing GUI to paint it. It also is called to repaint the board for every move.

GameOver: This is a pop up window that is displayed when a user has lost the game.

GameWon: This is a pop up window that is displayed when a user has won the game.

Run2048: This file runs the game by compiling each component into a JFrame, allowing for user interaction.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I had trouble getting my JavaGUI JFrame to hold the JPanels in the correct layout and adjusting their size. I also found myself struggling to parse through the large class files in order to find where I placed my methods or where I was changing certain variables.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

I think I did a good job of separating the GUI from the logic of the game but I think in some places the logic of some methods are intertwined in places that did make it slightly harder to test. For instance, I had my addTile() function called for every move at first but this made testing difficult since a random tile was spawned for every move made. I separated these out as a result. The private state is encapsulated for certain variables but less so for others. If I were to refactor, I would create more copies of different fields to ensure encapsulation.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used
  while implementing your game.

None