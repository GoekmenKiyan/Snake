package org.example;

import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Poison {

    private final int posX;
    private final int posY;

    public BufferedImage poisonImg;


    public Poison() {
        posX = generatePos(Snake.BOARD_WIDTH);
        posY = generatePos(Snake.BOARD_HEIGHT);
        try {
            poisonImg = ImageIO.read(new File("src/main/resources/images/poison.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int generatePos(int size) {
        Random random = new Random();
        return random.nextInt(size / Snake.TICK_SIZE) * Snake.TICK_SIZE;
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}