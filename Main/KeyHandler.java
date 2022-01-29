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

    public int entered = 0;
    public int fighting = 0;
    public boolean enterPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int code = e.getKeyCode();
        if(gm.gamestate == gm.battleMenuState){
            
            if(gm.battle.state == gm.battle.sequenceState){
                if(code == KeyEvent.VK_W){
                    gm.battle.choice--;
                    if(gm.battle.choice < 0){
                        gm.battle.choice = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gm.battle.choice++;
                    if(gm.battle.choice > 2){
                        gm.battle.choice = 0;
                    }
                }
                if(code == KeyEvent.VK_SPACE){
                    if(gm.battle.choice == 2){
                        gm.battle.run();
                    }
                    else if(gm.battle.choice == 1){

                    }
                    else if(gm.battle.choice == 0){
                        gm.battle.fight();
                    }
                }
            }
            if(code == KeyEvent.VK_SPACE){
                if(!gm.battle.stack.isEmpty()){
                    gm.battle.stack.pop(); 
                }
            }
        }
        else{
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
            if(code == KeyEvent.VK_E){
                if(gm.gamestate == gm.fruitState){
                    gm.gamestate = gm.townState;
                    return;
                }
                gm.gamestate = gm.fruitState;
            }
    
            if(code == KeyEvent.VK_ESCAPE){
                if(gm.gamestate == gm.townState){
                    gm.gamestate = gm.pauseState;
                }
                else if(gm.gamestate == gm.pauseState){
                    gm.gamestate = gm.townState;
                }
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
