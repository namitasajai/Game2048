package org.cis1200.game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWon extends JPanel {
    private static final Color tColor = new Color(87, 80, 73);
    Font tFont = new Font("Helvetica", Font.BOLD, 50);
    Font header = new Font("Helvetica", Font.BOLD, 20);
    public GameWon() {
        // Set the layout for the JPanel
        setLayout(null);

        // Create a new JFrame to hold the popup window
        JFrame frame = new JFrame("Game Won!");
        setLocation(500, 500);
        setBackground(new Color(0xE7D9CF));
        setPreferredSize(new Dimension(800, 400));

        final JLabel titleLabel = new JLabel("Game Won!");
        titleLabel.setForeground(tColor);
        titleLabel.setFont(tFont);
        titleLabel.setBounds(30, 20, 300, 100);
        add(titleLabel);

        final JLabel label2 = new JLabel(
                "<html>" + "Hit reset to play again!" + "</html>");
        label2.setForeground(tColor);
        label2.setFont(header);
        label2.setBounds(30, 100, 300, 100);
        add(label2);

        // Create a button with the text "Reset"
        JButton closeButton = new JButton("Reset");
        closeButton.setBounds(30, 400, 300, 50);
        closeButton.setFont(new Font("Helvetica Neue", Font.ITALIC, 14));
        closeButton.setForeground(new Color(0x3A2E23));

        // Add an action listener to the button
        // This will close the window when the button is clicked
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Run2048.b.reset();
                frame.dispose();
            }
        });

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
