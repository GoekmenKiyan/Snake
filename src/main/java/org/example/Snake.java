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

    static final Button start = new Button("Start"), help = new Button("Help"), new_game = new Button("New Game"), about = new Button("About"), pausee = new Button("Pause"), play = new Button("Play");


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

        //Buttons Positions
        start.setBounds(900, 230,120,70);
        pausee.setBounds(900, 230,120,70);
        play.setBounds(900, 230,120,70);
        new_game.setBounds(900, 350,120,70);
        help.setBounds(300, 480,120,70);
        about.setBounds(900,600,120,70);

        //Buttons action listener
        start.addActionListener(this);
        new_game.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);
        pausee.addActionListener(this);
        play.addActionListener(this);



        new_game.setFont(new Font("Georgia", Font.PLAIN, 24));
        new_game.setVisible(false);
        pausee.setVisible(false);
        play.setVisible(false);


        add(start);
        add(help);
        add(new_game);
        add(about);
        add(pausee);
        add(play);

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


        // Create the Background of the GameBoard in a specific color
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


    /**
     * All the Methods that get called up, when starting the game
     */
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
        start.setVisible(false);
        pausee.setVisible(true);
        new_game.setVisible(true);
    }


    /**
     * Print the Snake and Apples on the GameBoard
     */
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g); // To change body of generated methods
        Graphics2D gd = (Graphics2D) g;

        if (isMoving) {

            // Draw Image of red/normal Apple
            g.drawImage(apple.appleImg, apple.getPosX(), apple.getPosY(),null);

            // Draw Image of Golden/special Apple
            g.drawImage(GoldenApple.GoldenAppleImg, GoldenApple.getPosX(), GoldenApple.getPosY(), null);


            //Line between Game Play stage & Button Panel
            gd.setColor(Color.red);
            gd.setStroke(new BasicStroke(7));
            gd.drawLine(550, 700, 550, 0);

            //Score Text on Top Right Corner with Live Updates
            gd.setColor(new Color(19, 56, 5));
            gd.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            gd.drawString("Score: " + (applesEaten + goldenApplesEaten), 620 ,30);

            // Drawing the actual Snake
            g.setColor(Color.BLACK);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);
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
     * Moving Mechanism of the Snake:
     * i.E: When moving to the right, the Snake loses one tick at the end and adds one tick at the head of the snake
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


    /**
     * Method, to see what happens when Snake moves into Wall
     */
    public void collisionTest() {
        // Method when Snake moves into itself
        for (int i = snakeLength; i > 0; i--) {
            if ((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])) {
                isMoving = false;
                break;
            }
        }
        // When Snake moves against wall    Fenster Breite                                         Fenster Höhe
        if (snakePosX[0] < 0 || snakePosX[0] > 550 - TICK_SIZE || snakePosY[0] < 0 || snakePosY[0] > 700 - TICK_SIZE) {
            isMoving = false;
        }
        // When Snake dies
        if (!isMoving) {
            speed.stop();
            // Plays Death Sound
            playSE(1);
        }
    }


    //Play Button Logic
    void play(){
        play.setVisible(false);
        pausee.setVisible(true);
        speed.start();
    }
    //Pause Button Logic
    void pause() {
        play.setVisible(true);
        pausee.setVisible(false);
        speed.stop();
    }


    /**
     * Everything to happen, when Snake is alive and moving on the GameBoard
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
            collisionTest();
            eatApple();
            eatGoldenApple();
        }

        repaint();

        if(e.getSource() == start)
            start();

        if(e.getSource() == new_game){
            start();
            repaint();
        }

        if(e.getSource() == about){
            if(new_game.isVisible() && pausee.isVisible())
                pause();
            new About();
        }

        if(e.getSource() == help){
            if(new_game.isVisible() && pausee.isVisible())
                pause();;
            new Help();
        }

        if(e.getSource() == pausee)
            pause();

        if(e.getSource() == play)
            play();
    }
}