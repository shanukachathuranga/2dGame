import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();


        window.setLocationRelativeTo(null); // Not specify the location of the window = the window will be in the center of the screen
        window.setVisible(true); // Make the window visible

        gamePanel.startGameThread(); // Start the game loop

    }
}