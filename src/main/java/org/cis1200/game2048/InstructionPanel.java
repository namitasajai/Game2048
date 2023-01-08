package org.cis1200.game2048;
import java.awt.*;
import javax.swing.*;

public class InstructionPanel extends JPanel {
    private static final Color textColor = new Color(87, 80, 73);
    Font titleFont = new Font("Helvetica", Font.BOLD, 100);
    Font header = new Font("Helvetica", Font.BOLD, 20);
    Font body = new Font("Helvetica", Font.PLAIN, 14);
    public InstructionPanel() {
        // Set the layout for the JPanel
        setLayout(null);

        // Create a new JFrame to hold the popup window
        JFrame frame = new JFrame("Instruction Window");
        setLocation(500, 500);
        setBackground(new Color(0xE7D9CF));
        setPreferredSize(new Dimension(800, 400));

        final JLabel titleLabel = new JLabel("2048");
        titleLabel.setForeground(textColor);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(30, 20, 300, 100);
        add(titleLabel);

        final JLabel label2 = new JLabel(
                "<html>" + "2048 is a single-player puzzle game " +
                        "played on a 4x4 grid of tiles." + "</html>");
        label2.setForeground(textColor);
        label2.setFont(header);
        label2.setBounds(30, 100, 300, 100);
        add(label2);

        final JLabel label3 = new JLabel("<html>" + "Use the arrow keys on " +
                "your keyboard to move the tiles in the grid. When two tiles with" +
                " the same number touch, they will" +
                " merge into one tile with the sum of their numbers. " +
                "For example, if two tiles with the number 2 touch, they will merge into a " +
                "single tile with the number 4. " +
                "Try to reach the tile 2048 before you are out of moves!" + "</html>");
        label3.setForeground(textColor);
        label3.setFont(body);
        label3.setBounds(30, 170, 300, 200);
        add(label3);

        // Create a button with the text "Close"
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(30, 400, 300, 50);
        closeButton.setFont(new Font("Helvetica Neue", Font.ITALIC, 14));
        closeButton.setForeground(new Color(0x3A2E23));

        // Add an action listener to the button
        // This will close the window when the button is clicked
        closeButton.addActionListener(e -> frame.dispose());

        // Add the button to the JPanel
        add(closeButton);


        // Set the size of the window
        frame.setSize(375, 500);

        // Add the JPanel to the window
        frame.add(this);

        // Set the window to be visible
        frame.setVisible(true);
    }
}
