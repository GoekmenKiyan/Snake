package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Button extends JButton {
    Font arcadeClassic;
    Button(String text){

        /**
         * https://www.youtube.com/watch?v=43duJsYmhxQ
         * Using Custom Font in this Java Program
         */
        try{
            arcadeClassic = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PixelMplus10-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PixelMplus10-Regular.ttf")));
        }
        catch(IOException | FontFormatException e){
        }

        setText(text);
        setFont(arcadeClassic);
        setFocusable(false);
        setBackground(new Color(225, 225, 217));
        setForeground(Color.black);
    }
}
