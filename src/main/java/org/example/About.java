package org.example;

/*
About screen, shows the Project overview, & Developer contacts(Social media handlers).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;

public class About extends JFrame {
    final static JPanel handler_panel = new JPanel();


    final static Image help = new ImageIcon("images/help.png").getImage();


    About() {

        setSize(600, 600);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    //Drawing image of Developer
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(help, 0,0, null);

    }

}
