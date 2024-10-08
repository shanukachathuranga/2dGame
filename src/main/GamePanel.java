package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16; // 16x16 pixel tiles
    final int scale = 3; // Scale the tiles by 3

    public final int tileSize = originalTileSize * scale; // 48x48 pixel tiles
    final int maxScreenCol = 16; // 16 tiles wide
    final int maxScreenRow = 12; // 12 tiles tall
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    //FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler(); // main.KeyHandler object to handle key input
    Thread gameThread; // Thread for the game loop
    Player player = new Player(this, keyHandler); // Create a new Player object

    //set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler); // Add the key listener to the panel
        this.setFocusable(true); // Allow the panel to get focus, so it can receive key input events (keyPressed, keyReleased) from the user input device (keyboard)
    }

    // Start the game thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Game loop
//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000.0 / FPS; // Calculate the time between draws in nanoseconds (1 second = 1,000,000,000 nanoseconds) 60 FPS = 16,666,666 nanoseconds
//        double nextDrawTime = System.nanoTime() + drawInterval; // Calculate the time when the next draw should occur
//
//        while(gameThread != null){
//
//            long currentTime = System.nanoTime(); // Get the current time in nanoseconds
////            long currentTime = System.currentTimeMillis(); // Get the current time in milliseconds
//
//            // 1 UPDATE: update information about the game state
//            update(); // Calls update
//            // 2 DRAW: draw the game state to the screen
//            repaint(); // Calls paintComponent
//
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime(); // Calculate the remaining time until the next draw should occur
//                remainingTime = remainingTime/1000000; // Convert the remaining time from nanoseconds to milliseconds
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime); // Sleep until the remaining time is 0 (not accurate, but close enough)
//
//                nextDrawTime += drawInterval; // Calculate the time when the next draw should occur
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }

    // Game loop with FPS counter and key input handling (WASD)
    @Override
    public void run(){

        double drawInterval = 1000000000.0 / FPS; // Calculate the time between draws in nanoseconds (1 second = 1,000,000,000 nanoseconds) 60 FPS = 16,666,666 nanoseconds
        double delta = 0;   // Calculate the number of updates that should occur
        long lastTime = System.nanoTime();  // Get the current time in nanoseconds
        long currentTime;   // Get the current time in nanoseconds
        long timer = 0; // Timer to keep track of the time since the last update
        int drawCount = 0;  // Count the number of draws that have occurred

        while (gameThread != null){

            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            timer += currentTime - lastTime; // Calculate the time since the last update
            delta += (currentTime - lastTime) / drawInterval; // Calculate the number of updates that should occur

            lastTime = currentTime; // Update the last time

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            // Print the FPS every second
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }


    }


    // Update the game state
    public void update(){
        player.update(); // Calls update in the player object
    }

    // Draw the game state to the screen
    // This method is called by repaint()
    // Graphics g is the object that we use to draw to the screen think it as a paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  // Cast g to Graphics2D to access more features and methods for drawing to the screen  (like drawImage)
        player.draw(g2); // Calls draw in the player object
        g2.dispose(); // Dispose of the graphics object to free up resources and avoid memory leaks (not necessary but good practice)
    }





}
