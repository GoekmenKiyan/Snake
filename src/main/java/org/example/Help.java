package org.example;

import javax.swing.*;
import java.awt.*;

public class Help extends JFrame {
    static final int width = 600;
    static final int height = 600;
    Image help = new ImageIcon("src/main/resources/images/help.png").getImage();
    Image icon = new ImageIcon("src/main/resources/images/snakeIcon.png").getImage();

    Help(){
        setSize(width,height);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("HELP");
        setIconImage(icon);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(help, 0,0, null);
    }
}

