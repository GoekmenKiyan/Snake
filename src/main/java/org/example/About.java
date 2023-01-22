package org.example;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    final static Image about = new ImageIcon("src/main/resources/images/about.png").getImage();
    About() {

        setSize(600, 600);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(about, 0,0, null);
    }
}
