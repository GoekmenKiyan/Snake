package org.example;

import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class Food {

    private final int posX;
    private final int posY;

    public BufferedImage appleImg;


    public Food() {
        posX = generatePos(Graphics.WIDTH);
        posY = generatePos(Graphics.HEIGHT);
            try {
                appleImg = ImageIO.read(new File("src/main/resources/images/applesnake.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    private int generatePos(int size) {
        Random random = new Random();
        return random.nextInt(size / Graphics.TICK_SIZE) * Graphics.TICK_SIZE;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
