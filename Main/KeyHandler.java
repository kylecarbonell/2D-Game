package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

    /*Game game;
    public KeyHandler(Game game){
        this.game = game;
    }   */

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = true;
        }

        if(code == KeyEvent.VK_A){
            left = true;
        }

        if(code == KeyEvent.VK_S){
            down = true;
        }

        if(code == KeyEvent.VK_D){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = false;
        }

        if(code == KeyEvent.VK_A){
            left = false;
        }

        if(code == KeyEvent.VK_S){
            down = false;
        }

        if(code == KeyEvent.VK_D){
            right = false;
        }
    }

}
