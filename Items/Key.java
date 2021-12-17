package Items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Game;

public class Key extends Items{

    public Key(Game gm, int worldX, int worldY, String name) {
        super(gm);
        this.worldX = worldX * gm.tileSize;
        this.worldY = worldY*gm.tileSize;
        this.name = name;
        try {
            objectFile = new File("Character Sprites\\Items\\Key.png");
            objectImage = ImageIO.read(objectFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}