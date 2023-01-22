package org.example;

import javax.swing.*;
import java.awt.*;

public class Help extends JFrame {
    static final int width = 600;
    static final int height = 600;
    final static Image help = new ImageIcon("src/main/resources/images/help.png").getImage();

    Help(){
        setSize(width,height);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(help, 0,0, null);
    }
}

