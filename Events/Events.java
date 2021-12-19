package Events;

import java.awt.*;


import Main.Game;

public class Events {
    Game gm;
    public int worldX;
    public int worldY;

    public Rectangle hitBox = new Rectangle(0, 0,64,64);
    public int defaultX = hitBox.x;
    public int defaultY = hitBox.y;
    public int hitWidth = hitBox.width;
    public int hitHeight = hitBox.height;

    public int gamestate;
    public int teleportState;

    public Events(Game gm){
        this.gm = gm;
    }

    

}
