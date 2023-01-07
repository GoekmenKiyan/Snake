package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


/**
 * Main Class for the Game itself
 */
public class Snake extends JPanel implements ActionListener {


    /**
     * Game constants
     */

    // Size of the Width from the Game Panel
    static final int BOARD_WIDTH = 700;

    // Size of the Height from the Game Panel
    static final int BOARD_HEIGHT = 700;

    // TICK SIZE = Size of the snake + apple
    static final int TICK_SIZE = 25;

    // Calculates the Board Size considering the Size of the Snake + Apple
    static final int BOARD_SIZE = (BOARD_WIDTH * BOARD_HEIGHT) / (TICK_SIZE * TICK_SIZE);


    /**
     * Instance variable for Sound
     */
    Sound sound = new Sound();






    /**
     * Font for End Screen
     */
    Font arcadeClassic;







    /**
     * Array for Snake Position
     */

    // PosX can only be inside of the BoardSize
    int[] snakePosX = new int[BOARD_SIZE];

    // PosX can only be inside of the BoardSize
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;








    /**
     * Define Instance Variables for the Game Objects
     */

    // Define Variable
    private Apple apple;

    // Define Variable
    private GoldenApple GoldenApple;







    /**
     * Game consumables
     */

    // Variable to count how many Apples have been eaten
    int applesEaten;
    int goldenApplesEaten;







    char direction = 'R';
    boolean isMoving = false;

    // Timer = Speed of the Snake on the GameBoard
    Timer speed = new Timer(70, this);








    /**
     * Actual Snake including movement + consumables and what happens
     */

    public Snake() {




        /**
         * https://www.youtube.com/watch?v=43duJsYmhxQ
         * Using Custom Font in this Java Program
         */
        try{
            arcadeClassic = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));
        }
        catch(IOException | FontFormatException e){
        }


        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        Color grass = new Color(169, 209, 167);
        this.setBackground(grass);
        this.setFocusable(true);



        /**
         * The KeyListener below moves the Snake according
         * to the currently pressed ArrowKey.
         */
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

    public void start() {

        // Plays the Background Music
        // playMusic(2);

        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        // Setting the Starting Size of the Snake
        snakeLength = 5;
        applesEaten = 0;
        goldenApplesEaten = 0;
        // Select which direction the snake should face when starting the application
        direction = 'R';
        isMoving = true;
        spawnFood();
        speed.start();
    }



    // Print the Apples on the GameBoard
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); // To change body of generated methods

        if (isMoving) {

            // Draw Image of red/normal Apple
            g.drawImage(apple.appleImg, apple.getPosX(), apple.getPosY(),null);

            // Draw Image of Golden/special Apple
            g.drawImage(GoldenApple.GoldenAppleImg, GoldenApple.getPosX(), GoldenApple.getPosY(), null);

            // Drawing the actual Snake
            g.setColor(Color.BLACK);
            for (int i = 0; i < snakeLength; i++) {
                g.fillOval(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);
            }
        } else {
            // Print Text after colliding with wall / losing the game
            String gameOverText = " Game Over \n";
            String pressKeyText = "Press SPACE to play again! \n";
            String scoreText = String.format("Score: %d", (applesEaten + goldenApplesEaten));
            g.setColor(Color.BLACK);
            g.setFont(arcadeClassic);
            g.drawString(gameOverText,(BOARD_WIDTH - getFontMetrics(g.getFont()).stringWidth(gameOverText)) / 2, BOARD_HEIGHT / 4);
            g.drawString(scoreText, (BOARD_WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, BOARD_HEIGHT / 3);
            g.drawString(pressKeyText, (BOARD_WIDTH - getFontMetrics(g.getFont()).stringWidth(pressKeyText)) / 2, BOARD_HEIGHT / 2);
        }
    }


    /**
     * Creating/Implementing Methods to play Music for different occasions
     */

    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        //sound.loop();
    }

    public void stopMusic() {

        sound.stop();
    }

    public void playSE(int i) {

        sound.setFile(i);
        sound.play();
    }



    /**
     * Moving Mechanism of the Snake
     */
    public void move() {
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



    /**
     * Spawn/Print the Apples
     */
    public void spawnFood() {
        // Print normal/red Apple
        apple = new Apple();
        // Print special/golden Apple
        GoldenApple = new GoldenApple();
    }


    /**
     * Increase the length of the Snake after eating an Apple + print a new one
     */
    public void eatApple() {
        if ((snakePosX[0] == apple.getPosX()) && (snakePosY[0] == apple.getPosY())) {
            snakeLength++;
            applesEaten++;
            // Plays "Eating" SoundEffect, which is in Array [0]
            playSE(0);
            spawnFood();
        }
    }

    /**
     * Increase Length of the Snake after eating a Golden Apple
     */
    public void eatGoldenApple() {
        if ((snakePosX[0] == GoldenApple.getPosX()) && (snakePosY[0] == GoldenApple.getPosY())) {
            snakeLength = snakeLength + 3;
            //speed = new Timer(20, this);
            goldenApplesEaten = goldenApplesEaten + 3;
            // Plays "Eating" SoundEffect, which is in Array [0]
            playSE(0);
            spawnFood();

        }
    }



    public void collisionTest() {
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
            // Plays Death Sound
            playSE(1);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
            collisionTest();
            eatApple();
            eatGoldenApple();
        }

        repaint();
    }
}