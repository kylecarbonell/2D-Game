package Items;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import Main.Game;
public class Items {
    
    public BufferedImage objectImage;
    File objectFile;

    public int worldX;
    public int worldY;

    public Rectangle hitBox = new Rectangle(0, 0,64,64);
    public int defaultX = hitBox.x;
    public int defaultY = hitBox.y;
    public int hitWidth = hitBox.width;
    public int hitHeight = hitBox.height;

    public String name;
    public boolean collision = false;

    Game gm;

    public Items(Game gm){
        this.gm = gm;
    }

    public void paint(Graphics2D g2){
        int screenX = worldX - gm.player.worldX + gm.player.screenX;
        int screenY = worldY - gm.player.worldY + gm.player.screenY;

        if(worldX + gm.tileSize > gm.player.worldX - gm.player.screenX &&
            worldX - gm.tileSize< gm.player.worldX + gm.player.screenX &&
            worldY + gm.tileSize> gm.player.worldY - gm.player.screenY &&
            worldY - gm.tileSize < gm.player.worldY + gm.player.screenY){
            
            g2.drawImage(objectImage, screenX, screenY, gm.tileSize, gm.tileSize, null);
        }
    }

}
