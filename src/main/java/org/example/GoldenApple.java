package org.example;

import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GoldenApple {

    private final int posX;
    private final int posY;

    /**
     * Variable for the Image of the Golden Apple
     */
    public BufferedImage GoldenAppleImg;


    /**
     * Method for the GoldenApple
     */
    public GoldenApple() {
        posX = generatePos(Snake.BOARD_WIDTH);
        posY = generatePos(Snake.BOARD_HEIGHT);
        try {
            GoldenAppleImg = ImageIO.read(new File("src/main/resources/images/appleGolden.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Place the GoldenApple on the GameBoard
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