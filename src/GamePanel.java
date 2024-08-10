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

    Thread gameThread; // Thread for the game loop

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }

    // Start the game thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Game loop
    @Override
    public void run() {

        while(gameThread != null){
//          System.out.println("Game loop running");

            // 1 UPDATE: update information about the game state
            update(); // Calls update
            // 2 DRAW: draw the game state to the screen
            repaint(); // Calls paintComponent
        }
    }

    // Update the game state
    public void update(){

    }

    // Draw the game state to the screen
    // This method is called by repaint()
    // Graphics g is the object that we use to draw to the screen think it as a paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  // Cast g to Graphics2D to access more features and methods for drawing to the screen  (like drawImage)
        g2.setColor(Color.WHITE);
        g2.fillRect(100, 100, tileSize, tileSize); // Draw a white rectangle at (100, 100) with the size of a tile
    }





}
