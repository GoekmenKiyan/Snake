package org.example;
import javax.swing.*;

public class Game extends JFrame {
    public Game() {

        // Create Icon for the Program
        ImageIcon logo = new ImageIcon("src/main/resources/images/snakeIcon.png");

        this.add(new Snake());
        this.setTitle("S N A K E");
        this.setIconImage(logo.getImage());
        setSize(700, 700);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
