package org.example;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game() {

        this.add(new Snake());
        this.setTitle("S N A K E");
        setSize(700, 700);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
