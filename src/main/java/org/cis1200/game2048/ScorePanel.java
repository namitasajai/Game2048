package org.cis1200.game2048;


import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel {

    public static final Color fColor = new Color(0x493A2B);
    public static final Font tFont = new Font("Helvetica", Font.BOLD, 100);
    private static JLabel displayScore;

    private static JLabel displayHighScore;
    private static JLabel displayTitle;

    public ScorePanel(GameBoard board) {
        setPreferredSize(new Dimension(200, 200));
        setLayout(null);

        Font font = new Font("Helvetica", Font.PLAIN, 20);

        displayTitle = new JLabel("2048");
        displayTitle.setForeground(fColor);
        displayTitle.setFont(tFont);
        displayTitle.setBounds(50, 22, 250, 100);
        add(displayTitle);

        displayScore = new JLabel("Score: 0");
        displayScore.setForeground(fColor);
        displayScore.setFont(font);
        displayScore.setBounds(50, 120, 250, 30);
        add(displayScore);

        displayHighScore = new JLabel("High Score: 0");
        displayHighScore.setForeground(fColor);
        displayHighScore.setFont(font);
        displayHighScore.setBounds(50, 150, 250, 30);
        add(displayHighScore);
    }

    public static void setScore(int score) {
        displayScore.setText("Score: " + score);
    }

    public static void setHighScore(int highScore) {
        displayHighScore.setText("High Score: " + highScore);
    }

}
