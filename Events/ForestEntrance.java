package Events;

import Entities.Entity;
import Entities.Player;
import Main.Game;

public class ForestEntrance extends Events{
    
    public ForestEntrance(Game gm, int worldX, int worldY, int gamestate){
        super(gm);
        this.worldX = worldX*gm.tileSize;
        this.worldY = worldY*gm.tileSize;
        this.gamestate = gamestate;
        this.teleportState = gm.forestState;
    }

    public void action(Player entity){
        gm.gamestate = gm.forestState;
        entity.setValues();
    }
}
