package org.cis1200.game2048;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener {

    private Game2048 tfe;

    // Game constants
    public static final int BOARD_WIDTH = 100;
    public static final int BOARD_HEIGHT = 500;

    private static int tileLength = 100;
    private static int boardStartX = 50;
    private static int boardStartY = 50;
    private boolean gameEnd = false;

    /**
     * Initializes the game board.
     */
    public GameBoard() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new Game2048(); // initializes model for the game

        addKeyListener(this);
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        if (gameEnd) {
            addKeyListener(this);
        }
        ScorePanel.setHighScore(tfe.getHighScore());
        ScorePanel.setScore(tfe.getScore());
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        boolean alreadyEnd = false;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int[][] board = tfe.getBoard();
        g2.setColor(new Color(70, 52, 52));
        g2.drawRoundRect(boardStartX, boardStartY, 4 * tileLength, 4 * tileLength, 1, 1);
        // Draws board grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g2.setColor(new Color(145, 134, 134));
                g2.setStroke(new BasicStroke(14));
                g2.drawRoundRect(boardStartX + tileLength * j,
                        boardStartY + tileLength * i, tileLength,
                        tileLength, 2, 2);
                g2.setColor(new Color(187, 175, 175));
                g2.fillRoundRect(boardStartX + tileLength * j, boardStartY + tileLength * i,
                        tileLength, tileLength, 2, 2);
                if (board[i][j] != 0) {
                    g2.setColor(getTileColor(board[i][j]));
                    g2.fillRoundRect(j * tileLength + boardStartX,
                            i * tileLength + boardStartY, tileLength, tileLength, 2, 2);
                    g2.setColor(new Color(0x493A2B));
                    if (board[i][j] >= 1024) {
                        g2.setFont(new Font("Helvetica", Font.BOLD, 30));
                    } else {
                        g2.setFont(new Font("Helvetica", Font.BOLD, 40));
                    }
                    g2.drawString(String.valueOf(board[i][j]),
                            j * tileLength + getStringLengthX(board[i][j]) + boardStartX,
                            i * tileLength + getStringLengthY(board[i][j]) + boardStartY);
                }
            }
            if (tfe.gameOver() && !alreadyEnd) {
                alreadyEnd = true;
                removeKeyListener(this);
                gameEnd = true;
                new GameOver();
            }
            if (tfe.gameWon() && !alreadyEnd) {
                alreadyEnd = true;
                removeKeyListener(this);
                gameEnd = true;
                new GameWon();
            }
        }
    }

    public void undoButton() {
        if (tfe.undo() && !gameEnd) {
            ScorePanel.setScore(tfe.getScore());
            repaint();
        }
        requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            boolean add = tfe.move(Direction.LEFT);
            if (add) {
                tfe.addTile();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            boolean add = tfe.move(Direction.RIGHT);
            if (add) {
                tfe.addTile();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            boolean add = tfe.move(Direction.DOWN);
            if (add) {
                tfe.addTile();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            boolean add = tfe.move(Direction.UP);
            if (add) {
                tfe.addTile();
            }
        }
        ScorePanel.setScore(tfe.getScore());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(238, 228, 218);
            case 4 -> new Color(237, 224, 200);
            case 8 -> new Color(242, 177, 121);
            case 16 -> new Color(245, 149, 99);
            case 32 -> new Color(246, 124, 95);
            case 64 -> new Color(246, 94, 59);
            case 128 -> new Color(237, 207, 114);
            case 256 -> new Color(237, 204, 97);
            case 512 -> new Color(237, 200, 80);
            case 1024 -> new Color(237, 197, 63);
            case 2048 -> new Color(237, 194, 46);
            default -> Color.WHITE;
        };
    }

    private int getStringLengthX(int value) {
        return switch (value) {
            case 16, 32, 64 -> 23;
            case 128, 256 -> 11;
            case 512, 2048 -> 12;
            case 1024 -> 12;
            default -> 35;
        };
    }
    private int getStringLengthY(int value) {
        return switch (value) {
            case 1024, 2048 -> 57;
            default -> 62;
        };
    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}

