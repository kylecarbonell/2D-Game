package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

    Game gm;
    public KeyHandler(Game gm){
        this.gm = gm;
    }   

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean moving;

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
            moving = true;
        }

        if(code == KeyEvent.VK_A){
            left = true;
            moving = true;
        }

        if(code == KeyEvent.VK_S){
            down = true;
            moving = true;
        }

        if(code == KeyEvent.VK_D){
            right = true;
            moving = true;
        }

        if(code == KeyEvent.VK_ESCAPE){
            if(gm.gamestate == gm.townState){

                gm.gamestate = gm.forestState;
            }
            else if(gm.gamestate == gm.forestState){
                gm.gamestate = gm.townState;
            }
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
        moving = false;
    }

}
