import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //Screen settings
    final int originalTileSize = 16; // 16x16 pixel tiles
    final int scale = 3; // Scale the tiles by 3

    final int tileSize = originalTileSize * scale; // 48x48 pixel tiles
    final int maxScreenCol = 16; // 16 tiles wide
    final int maxScreenRow = 12; // 12 tiles tall
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }


}
