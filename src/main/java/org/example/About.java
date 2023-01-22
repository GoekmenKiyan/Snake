package org.example;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    Image about = new ImageIcon("src/main/resources/images/about.png").getImage();
    Image icon = new ImageIcon("src/main/resources/images/snakeIcon.png").getImage();
    About() {

        setSize(600, 600);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("ABOUT");
        setIconImage(icon);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(about, 0,0, null);
    }
}
