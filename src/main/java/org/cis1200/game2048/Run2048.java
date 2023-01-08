package org.cis1200.game2048;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class Run2048 implements Runnable, ActionListener {
    public static final GameBoard b = new GameBoard();
    Popup p;
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");

        // Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the center of the screen
        int x = (int) (screenSize.getWidth() / 2 - frame.getWidth() / 2);
        int y = (int) (screenSize.getHeight() / 2 - frame.getHeight() / 2);

        // Set the location of the window to the center of the screen
        frame.setLocation(x, y);
        frame.setResizable(true);

        final InstructionPanel instructionPanel = new InstructionPanel();
        instructionPanel.setLocation(x, y);

        // Game board
        b.setMaximumSize(new Dimension(500, 400));
        frame.add(b, BorderLayout.CENTER);

        // Reset button
        final ScorePanel control_panel = new ScorePanel(b);
        frame.add(control_panel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 0, 0));
        buttonPanel.setPreferredSize(new Dimension(500, 50));

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b.reset();
            }
        });
        reset.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
        reset.setForeground(new Color(0x423C34));
        reset.setPreferredSize(new Dimension(100, 100));
        buttonPanel.add(reset);

        // Undo move button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> b.undoButton());
        undo.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
        undo.setForeground(new Color(0x423C34));
        undo.setPreferredSize(new Dimension(100, 100));
        buttonPanel.add(undo);

        buttonPanel.setBounds(450, 20, 100, 60);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        b.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.show();
    }
}