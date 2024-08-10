import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    // This method is called when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Get the key code of the key that was pressed
        if(keyCode == KeyEvent.VK_W){
            upPressed = true;
        }
        else if(keyCode == KeyEvent.VK_A){
            leftPressed = true;
        }
        else if(keyCode == KeyEvent.VK_S){
            downPressed = true;
        }
        else if(keyCode == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    // This method is called when a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Get the key code of the key that was released
        if(keyCode == KeyEvent.VK_W){
            upPressed = false;
        }
        else if(keyCode == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(keyCode == KeyEvent.VK_S){
            downPressed = false;
        }
        else if(keyCode == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
