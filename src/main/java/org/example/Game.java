package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Game extends JFrame {

    public Game() {

        // Create Icon for the Program
        ImageIcon logo = new ImageIcon("res/snakeIcon.png");


        /**
         * https://stackoverflow.com/questions/27675814/center-align-the-title-in-jframe
         * Code below has been taken from StackOverflow:
         * The title of the Program will be represented in the middle of the Top Window
         */

        this.setSize(600,300);
        this.setFont(new Font("System", Font.PLAIN, 14));
        Font f = this.getFont();
        FontMetrics fm = this.getFontMetrics(f);
        int x = fm.stringWidth("S N A K E   by   Ueberraschungseffekt");
        int y = fm.stringWidth(" ");
        int z = this.getWidth()/2 - (x/2);
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";
        pad = String.format("%"+w+"s", pad);
        this.setTitle(pad+"S N A K E   by   Ueberraschungseffekt");


        this.add(new Snake());
        //this.setTitle("S N A K E");
        this.setIconImage(logo.getImage());
        setSize(700, 700);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
