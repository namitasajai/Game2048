package org.cis1200.game2048;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;


/**
 * This class is a model for TicTacToe.
 *
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 *
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 *
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class Game2048 {

    private int[][] board;
    private boolean start;
    private boolean gameOver;
    private boolean gameWon;
    private boolean reset;
    private int score;
    private int highScore;
    private Random rand = new Random();
    private Stack<int[][]> moveHistory;
    private Stack<Integer> scoreHistory;

    private File boardFile;
    private File boardHistoryFile;

    /**
     * Constructor sets up game state.
     */
    public Game2048() {
        start = true;
    }

    public boolean move(Direction d) {
        int[][] boardCopy = new int[4][4];
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = Arrays.copyOf(board[i], 4);
        }
        if (d == Direction.LEFT && !gameOver) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (board[i][j] != 0) {
                        moveTile(d, i, j);
                    }
                }
            }
        } else if (d == Direction.RIGHT) {
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        moveTile(d, i, j);
                    }
                }
            }
        } else if (d == Direction.DOWN) {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] != 0) {
                        moveTile(d, i, j);
                    }
                }
            }
        } else if (d == Direction.UP) {
            for (int i = 1; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] != 0) {
                        moveTile(d, i, j);
                    }
                }
            }
        }
        for (int row = 0; row < boardCopy.length; row++) {
            for (int col = 0; col < boardCopy[0].length; col++) {
                if (board[row][col] != boardCopy[row][col]) {
                    return true;
                }
            }
        }

        return false;

    }

    private void moveTile(Direction d, int r, int c) {

        if (d == Direction.LEFT) {
            int temp = c - 1;
            while (temp >= 0) {
                if (board[r][temp] == 0) {
                    board[r][temp] = board[r][temp + 1];
                    board[r][temp + 1] = 0;
                    temp -= 1;
                } else if (board[r][temp] == board[r][temp + 1]) {
                    board[r][temp] = board[r][temp] * 2;
                    board[r][temp + 1] = 0;
                    if (board[r][temp] == 2048) {
                        gameWon = true;
                    }
                    score += board[r][temp];
                    break;
                } else {
                    break;
                }
            }
        } else if (d == Direction.RIGHT) {
            int temp = c + 1;
            while (temp <= 3) {
                if (board[r][temp] == 0) {
                    board[r][temp] = board[r][temp - 1];
                    board[r][temp - 1] = 0;
                    temp += 1;
                } else if (board[r][temp] == board[r][temp - 1]) {
                    board[r][temp] = board[r][temp] * 2;
                    board[r][temp - 1] = 0;
                    if (board[r][temp] == 2048) {
                        gameWon = true;
                    }
                    score += board[r][temp];
                    break;
                } else {
                    break;
                }
            }
        } else if (d == Direction.DOWN) {
            int temp = r + 1;
            while (temp <= 3) {
                if (board[temp][c] == 0) {
                    board[temp][c] = board[temp - 1][c];
                    board[temp - 1][c] = 0;
                    temp += 1;
                } else if (board[temp][c] == board[temp -  1][c]) {
                    board[temp][c] = board[temp][c] * 2;
                    board[temp - 1][c] = 0;
                    if (board[temp][c] == 2048) {
                        gameWon = true;
                    }
                    score += board[temp][c];
                    break;
                } else {
                    break;
                }
            }
        } else if (d == Direction.UP) {
            int temp = r - 1;
            while (temp >= 0) {
                if (board[temp][c] == 0) {
                    board[temp][c] = board[temp + 1][c];
                    board[temp + 1][c] = 0;
                    temp -= 1;
                } else if (board[temp][c] == board[temp + 1][c]) {
                    board[temp][c] = board[temp][c] * 2;
                    board[temp + 1][c] = 0;
                    if (board[temp][c] == 2048) {
                        gameWon = true;
                    }
                    score += board[temp][c];
                    break;
                } else {
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid direction: " + d);
        }
    }
    public void addTile() {
        // Get a list of all empty cells on the grid
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new int[] {i, j});
                }
            }
        }

        // If there are no empty cells, do nothing
        if (emptyCells.isEmpty()) {
            if (gameOver()) {
                gameOver = true;
            }
            return;
        }

        int[] cell = emptyCells.get(rand.nextInt(emptyCells.size()));
        board[cell[0]][cell[1]] = rand.nextInt(10) < 9 ? 2 : 4;

        int[][] copy = new int[4][4];

        for (int a = 0; a < board.length; a++) {
            copy[a] = Arrays.copyOf(board[a], 4);
        }
        moveHistory.push(copy);
        scoreHistory.push(score);
        if (boardFile != null) {
            boardFile.delete();
        }
        if (boardHistoryFile != null) {
            boardHistoryFile.delete();
        }
        saveFile();
        saveHistoryFile();
    }
    public boolean gameWon() {
        return gameWon;
    }
    public boolean gameOver() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int current = board[row][col];
                if (col < board[row].length - 1 && current == board[row][col + 1]) {
                    return false;
                }
                if (row < board.length - 1 && current == board[row + 1][col]) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean undo() {
        if (moveHistory.size() > 1) {
            moveHistory.pop();
            board = moveHistory.peek();
            scoreHistory.pop();
            score = scoreHistory.peek();
            saveFile();
            saveHistoryFile();
            return true;
        }
        return false;
    }


    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new int[4][4];
        gameOver = false;
        gameWon = false;
        setHighScore();
        score = 0;
        moveHistory = new Stack<>();
        scoreHistory = new Stack<>();

        // Initialize two random squares to be 2
        int i = rand.nextInt(4);
        int j = rand.nextInt(4);

        board[i][j] = 2;
        int k = rand.nextInt(4);
        int l = rand.nextInt(4);

        while (k == i && l == j) {
            k = rand.nextInt(4);
            l = rand.nextInt(4);
        }
        board[k][l] = 2;
        boardFile = new File("board.csv");
        boardHistoryFile = new File("boardHistory.csv");
        int[][] copy = new int[4][4];
        if (boardFile.exists() && start && boardHistoryFile.exists()) {
            loadData();
            for (int a = 0; a < board.length; a++) {
                copy[a] = Arrays.copyOf(board[a], 4);
            }
            moveHistory.push(copy);
            scoreHistory.push(0);
        } else {
            for (int a = 0; a < board.length; a++) {
                copy[a] = Arrays.copyOf(board[a], 4);
            }
            moveHistory.push(copy);
            scoreHistory.push(0);
            saveFile();
            saveHistoryFile();
        }
        start = false;
    }

    public void saveHistoryFile() {
        boardHistoryFile = new File("boardHistory.csv");
        Iterator<int[][]> iter = moveHistory.iterator();
        Iterator<Integer> iterScore = scoreHistory.iterator();
        try {
            try {
                boardHistoryFile.delete();
            } catch (Exception ignored) {

            }
            boardHistoryFile.createNewFile();
            try {
                PrintWriter writer = new PrintWriter(boardHistoryFile);
                while(iter.hasNext() && iterScore.hasNext()) {
                    StringBuilder stringbuilder = new StringBuilder();
                    int[][] next = iter.next();
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            stringbuilder.append(next[i][j]).append(", ");
                        }
                        stringbuilder.append("\n");
                    }
                    writer.write(stringbuilder.toString());
                    writer.write(iterScore.next() + "\n");
                }
                writer.close();
            } catch (Exception ignored) {
                System.out.println(ignored);
            }
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }
    public void saveFile() {
        boardFile = new File("board.csv");
        try {
            try {
                boardFile.delete();
            } catch (Exception ignored) {

            }
            boardFile.createNewFile();
            try {
                PrintWriter writer = new PrintWriter(boardFile);
                StringBuilder stringbuilder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        stringbuilder.append(board[i][j]).append(", ");
                    }
                    stringbuilder.append("\n");
                }
                writer.write(stringbuilder.toString());
                writer.write(score + "\n");
                writer.write(String.valueOf(highScore));
                writer.close();
            } catch (Exception ignored) {
                System.out.println(ignored);
            }
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }


    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public int[][] getBoard() {
        int[][] copy = new int[4][4];
        for (int a = 0; a < board.length; a++) {
            copy[a] = Arrays.copyOf(board[a], 4);
        }
        return copy;
    }

    public void loadData() {
        List<List<String>> rawData = new ArrayList<>();
        List<List<String>> rD = new ArrayList<>();
        try {
            FileReader f = new FileReader("board.csv");
            FileReader fHistory = new FileReader("boardHistory.csv");
            BufferedReader bufferedReader = new BufferedReader(f);
            BufferedReader bf = new BufferedReader(fHistory);
            String row = bufferedReader.readLine();
            String r = bf.readLine();

            while (row != null) {
                String[] entries = row.replaceAll(" ", "").split(",");
                rawData.add(Arrays.asList(entries));
                row = bufferedReader.readLine();
            }
            while (r != null) {
                String[] entries = r.replaceAll(" ", "").split(",");
                rD.add(Arrays.asList(entries));
                r = bf.readLine();
            }
        } catch (Exception ignored) {

        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = Integer.parseInt(rawData.get(i).get(j));
            }
        }
        Stack<int[][]> moveTemp = new Stack<>();
        Stack<Integer> scoreTemp = new Stack<>();
        int[][] move = new int[4][4];
        int b = 0;
        for (int i = 0; i < rD.size(); i++) {
            if (rD.get(i).size() == 4) {
                for (int j = 0; j < 4; j++) {
                    move[b][j] = Integer.parseInt(rD.get(i).get(j));
                }
                b++;
            } else {
                moveTemp.push(move);
                move = new int[4][4];
                scoreTemp.push(Integer.parseInt(rD.get(i).get(0)));
                b = 0;
            }
        }
        score = Integer.parseInt((rawData.get(4).get(0)));
        highScore = Integer.parseInt((rawData.get(5).get(0)));
        moveHistory = moveTemp;
        scoreHistory = scoreTemp;
    }

    public Stack<int[][]> getMoveHistory() {
        Stack<int[][]> moveHistoryCopy = (Stack<int[][]>) moveHistory.clone();
        return moveHistoryCopy;
    }

    public void setBoard(int[][] testBoard) {
        board = testBoard;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {
        if (score >= this.highScore){
            highScore = score;
        }
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {

    }
}
