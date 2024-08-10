import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16; // 16x16 pixel tiles
    final int scale = 3; // Scale the tiles by 3

    final int tileSize = originalTileSize * scale; // 48x48 pixel tiles
    final int maxScreenCol = 16; // 16 tiles wide
    final int maxScreenRow = 12; // 12 tiles tall
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    //FPS
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler(); // KeyHandler object to handle key input
    Thread gameThread; // Thread for the game loop

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
//                Thread.sleep((long) remainingTime); // Sleep until the remaining time is 0
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

    @Override
    public void run(){

        double drawInterval = 1000000000.0 / FPS; // Calculate the time between draws in nanoseconds (1 second = 1,000,000,000 nanoseconds) 60 FPS = 16,666,666 nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }


    }


    // Update the game state
    public void update(){
        if(keyHandler.upPressed){
            playerY -= playerSpeed; // Move the player up
        }
        else if(keyHandler.downPressed){
            playerY += playerSpeed; // Move the player down
        }
        else if(keyHandler.leftPressed){
            playerX -= playerSpeed; // Move the player left
        }
        else if(keyHandler.rightPressed){
            playerX += playerSpeed; // Move the player right
        }
    }

    // Draw the game state to the screen
    // This method is called by repaint()
    // Graphics g is the object that we use to draw to the screen think it as a paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  // Cast g to Graphics2D to access more features and methods for drawing to the screen  (like drawImage)
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // Draw a white rectangle at (100, 100) with the size of a tile
        g2.dispose(); // Dispose of the graphics object to free up resources and avoid memory leaks (not necessary but good practice)
    }





}
