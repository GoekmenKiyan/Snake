package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Snake extends JPanel implements ActionListener {


    /**
     * Game constants
     */

    // Size of the Width from the Game Panel
    static final int BOARD_WIDTH = 700;

    // Size of the Height from the Game Panel
    static final int BOARD_HEIGHT = 700;

    // TICK SIZE = Size of the snake + apple
    static final int TICK_SIZE = 30;

    // Calculates the Board Size considering the Size of the Snake + Apple
    static final int BOARD_SIZE = (BOARD_WIDTH * BOARD_HEIGHT) / (TICK_SIZE * TICK_SIZE);



    /**
     * Font for End Screen
     */

    final Font font = new Font("New Times Roman", Font.BOLD, 25);


    /**
     * Array for Snake Position
     */

    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;


    private static BufferedImage gameOverImg;
    private static BufferedImage instructionsImg;


    /**
     * Apple Variables
     */

    // Define Variable
    Apple apple;

    // Define Variable
    GoldenApple GoldenApple;


    /**
     * Game consumables
     */

    // Variable to count how many Apples have been eaten
    int foodEaten;
    // Variable to count how many Golden Apples have been eaten
    int GoldenFoodEaten;


    char direction = 'R';
    boolean isMoving = false;

    // Timer = Speed of the Snake on the GameBoard
    Timer speed = new Timer(120, this);




    /**
     * Actual Snake including movement + consumables and what happens
     */

    public Snake() {

        Color grass = new Color(169, 209, 167);
        setBackground(grass);

        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);

        // This key listener allows the snake to move as long as an arrow key
        // is pressed, by changing the snake's velocity accordingly. (The begin
        // method below actually moves the snake.)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isMoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                } else {
                    start();
                }
            }
        });

        start();
    }

    protected void start() {
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        snakeLength = 5;
        foodEaten = 0;
        GoldenFoodEaten = 0;
        direction = 'R';
        isMoving = true;
        spawnFood();
        speed.start();
    }


    // Print the Apples on the GameBoard
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (isMoving) {

            //Drawing the Apple
            //g.setColor(Color.RED);

            // Draw the Apple and its form (Circle, Rectangle ...)
            //g.fillRect(food.getPosX(), food.getPosY(), TICK_SIZE, TICK_SIZE);

            // Draw Image of red/normal Apple
            g.drawImage(apple.appleImg, apple.getPosX(), apple.getPosY(),null);

            // Draw Image of Golden/special Apple
            g.drawImage(GoldenApple.GoldenAppleImg, GoldenApple.getPosX(), GoldenApple.getPosY(), null);

            // Drawing the actual Snake
            g.setColor(Color.GREEN);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);
            }
        } else {
            // Print Text after colliding with wall / losing the game
            String scoreText = String.format("Game Over ... Score: %d ... Press any key to play again!", foodEaten);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(scoreText, (BOARD_WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, BOARD_HEIGHT / 2);
        }
    }


    // Move the Snake
    protected void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakePosX[i] = snakePosX[i-1];
            snakePosY[i] = snakePosY[i-1];
        }

        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }


    // Spawn/Print the Apples
    protected void spawnFood() {
        // Print normal/red Apple
        apple = new Apple();
        // Print special/golden Apple
        GoldenApple = new GoldenApple();
    }


    // Increase the length of the Snake after eating an Apple + print a new one
    protected void eatFood() {
        if ((snakePosX[0] == apple.getPosX()) && (snakePosY[0] == apple.getPosY())) {
            snakeLength++;
            foodEaten++;
            spawnFood();
        }
    }

    // Increase Length of the Snake after eating a Golden Apple
    protected void eatGoldenFood() {
        if ((snakePosX[0] == GoldenApple.getPosX()) && (snakePosY[0] == GoldenApple.getPosY())) {
            snakeLength = snakeLength + 3;
            //speed = new Timer(20, this);
            GoldenFoodEaten++;
            spawnFood();
        }
    }


    protected void collisionTest() {
        for (int i = snakeLength; i > 0; i--) {
            if ((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])) {
                isMoving = false;
                break;
            }
        }

        if (snakePosX[0] < 0 || snakePosX[0] > BOARD_WIDTH - TICK_SIZE || snakePosY[0] < 0 || snakePosY[0] > BOARD_HEIGHT - TICK_SIZE) {
            isMoving = false;
        }

        if (!isMoving) {
            speed.stop();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
            collisionTest();
            eatFood();
            eatGoldenFood();
        }

        repaint();
    }
}