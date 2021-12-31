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
                    gm.ui.battleStateChoice = 0;
                }
                if(code == KeyEvent.VK_S){
                    gm.ui.battleStateChoice = 1;
                }
                if(code == KeyEvent.VK_SPACE){
                    if(gm.ui.battleStateChoice == gm.battle.FIGHT){
                        gm.battle.fight();
                    }
                    else if(gm.ui.battleStateChoice == gm.battle.RUN){
                        gm.gamestate = gm.forestState;
                    }
                }
            }
            else if(gm.battle.state == gm.battle.dialogueState){
                if(code == KeyEvent.VK_SPACE){
                    enterPressed = true;
                }
            }
            else if(gm.battle.state == gm.battle.entranceState){
                if(code == KeyEvent.VK_SPACE){
                    if(entered < gm.battle.entranceDialogue.length){
                        entered++;
                    }
                }
            }
            else if(gm.battle.state == gm.battle.fightingState){
                if(code == KeyEvent.VK_SPACE){
                    fighting++;
                }
            }
            else if(gm.battle.state == gm.battle.healthState){
                if(code == KeyEvent.VK_SPACE){
                    gm.gamestate = gm.forestState;
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
    
            if(code == KeyEvent.VK_ESCAPE){
                if(gm.gamestate == gm.townState){
                    gm.gamestate = gm.forestState;
                }
                else if(gm.gamestate == gm.forestState){
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
