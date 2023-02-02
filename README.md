---
Game 2048
---

<img width="300" alt="Instruction Window" src="https://user-images.githubusercontent.com/122184153/216217625-456aafbc-7c78-43ee-9771-8ba2bd44cd59.png">
<img width="300" alt="Game 2048" src="https://user-images.githubusercontent.com/122184153/216217699-f22c0686-24e3-410e-8c36-7001388b03d2.png">

## Description
Game of 2048 constructed using JavaSwing GUI. 

## Core Concepts

  1. 2D Arrays: I used 2D arrays to hold the game board for 2048 as well as paint it in my repaint() method. The array is updated after each move is made while the previous board is held in a collection. This implementation of 2D arrays is appropriate since 2D arrays are mutable in place so changes can be made when moves are made in place.

  2. Collections: I used a stack to hold my moveHistory and scoreHistory to store each game board after each move and each store in order to implement an undo button. Since Collections are ordered and you are able to efficiently add and remove items, this makes it easier to undo (remove a board and past score). This makes a stack an appropriate implementation of Collections.

  3. JUnit Testing: I used JUnit testing to test the logic behind the code that feeds into the GUI. By separating the logic out, I was able to test various cases that might appear during a game of 2048. I constructed several possible boards that may exist during a game that represent various game states cases. Then, I tested whether a change made to the game state after a player's turn still represents what it should represent.

  4. File I/O: File I/O is used to save the game state in order for a user to leave the game and return to it later. The file will be deleted once the game is complete. The file is supposed to save the board and score history so the undo button continues to work.

## Implementation

InstructionPanel: This class holds the JPanel pop-up window that appears when the game is first instantiated.

ScorePanel: This class holds the JPanel that  creates the JLabels displaying the title and scores at the top of the window.

Direction: The direction class contains enums that correspond to keyPresses. This class simply enumerates the various directions the tiles may slide.

Game2048: This class holds the game logic or the model for the game. This class performs all the operations for the GameBoard class so that the repaint method works.

GameBoard: This class constructs the game board using the Java Swing GUI to paint it. It also is called to repaint the board for every move.

GameOver: This is a pop up window that is displayed when a user has lost the game.

GameWon: This is a pop up window that is displayed when a user has won the game.

Run2048: This file runs the game by compiling each component into a JFrame, allowing for user interaction.

## External Resources

None

## Contact Information

Created by Namita Sajai, 2021
