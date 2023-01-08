package org.cis1200.game2048;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;


/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {
    public void printBoard(int[][] board) {
        System.out.println(Arrays.deepToString(board));
    }
    private final int[][] simpleBoard = {
        {0, 0, 0, 2},
        {0, 2, 0, 2},
        {0, 0, 0, 2},
        {0, 2, 0, 2},
    };

    private final int[][] simpleBoardDown = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 4},
            {0, 4, 0, 4},
    };
    private final int[][] simpleBoardUp = {
            {0, 4, 0, 4},
            {0, 0, 0, 4},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    private final int[][] simpleBoardLeft = {
            {2, 0, 0, 0},
            {4, 0, 0, 0},
            {2, 0, 0, 0},
            {4, 0, 0, 0},
    };
    private final int[][] simpleBoardRight = {
            {0, 0, 0, 2},
            {0, 0, 0, 4},
            {0, 0, 0, 2},
            {0, 0, 0, 4},
    };
    @Test
    public void testMoveDown() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.move(Direction.DOWN);
        int[][] board = testBoard.getBoard();
        assertArrayEquals(board, simpleBoardDown);
    }

    @Test
    public void testMoveUp() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.move(Direction.UP);
        int[][] board = testBoard.getBoard();
        assertArrayEquals(board, simpleBoardUp);
    }

    @Test
    public void testMoveLeft() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.move(Direction.LEFT);
        assertArrayEquals(testBoard.getBoard(), simpleBoardLeft);
    }

    @Test
    public void testMoveRight() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.move(Direction.RIGHT);
        int[][] board = testBoard.getBoard();
        assertArrayEquals(board, simpleBoardRight);
    }

    @Test
    public void testUndo() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.move(Direction.RIGHT);
        testBoard.undo();
        assertArrayEquals(testBoard.getBoard(), simpleBoard);
    }

    @Test
    public void testUndoStartScreen() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.undo();
        assertArrayEquals(testBoard.getBoard(), simpleBoard);
    }

    @Test
    public void testReset() {
        Game2048 testBoard = new Game2048();
        testBoard.setBoard(simpleBoard);
        testBoard.reset();
        int countTiles = 0;
        int[][] board = testBoard.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    countTiles++;
                }
            }
        }
        assertEquals(countTiles, 2);
    }

}
