package org.example;

import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Apple {

    private final int posX;
    private final int posY;


    /**
     * Variable for the Image of the Red Apple
     */
    public BufferedImage appleImg;


    /**
     * Method for the Apple
     */
    public Apple() {
        // Generate a random x-Coordinate to place the Apple within the GameBoard
        posX = generatePos(Snake.BOARD_WIDTH);
        // Generate a random x-Coordinate to place the Apple within the GameBoard
        posY = generatePos(Snake.BOARD_HEIGHT);
            try {
                // Add the picture of the Apple to its Variable
                appleImg = ImageIO.read(new File("src/main/resources/images/applePixel.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * Place the Apple on the GameBoard
     */
    private int generatePos(int size) {
        Random random = new Random();
        return random.nextInt(size / Snake.TICK_SIZE) * Snake.TICK_SIZE;
    }


    /**
     * Getter Methods for the X and Y Positions
     */
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
