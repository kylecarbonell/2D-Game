package Maps;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.*;
import Main.Game;
import java.awt.image.*;

public class Map extends Tile{
    Game gm;
    BufferedImage[][] layout;
    Tile[] tiles;

    int i = 0;
    int j = 0;


    public Map(Game gm){
        this.gm = gm;
        layout = new BufferedImage[gm.rows][gm.col];
        tiles = new Tile[100];
        getImage();
        instantiateMap();
    }

    public void getImage(){
        File grassFile = new File("Character Sprites\\Terrain\\GrassTile.png");
        File sandFile = new File("Character Sprites\\Terrain\\SandTile.png");
        File stoneBrickFile = new File("Character Sprites\\Terrain\\StoneBrickTile.png");
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(grassFile);

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(sandFile);

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(stoneBrickFile);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void instantiateMap(){
        try {
            Scanner scan = new Scanner(new File("Maps\\Map.txt"));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);

                while(lineScan.hasNext()){
                    layout[i][j] = tiles[Integer.valueOf(lineScan.next())].image;
                    i++;
                }
                i=0;
                j++;
                lineScan.close();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g){
        for(int i = 0; i < gm.maxWorldRow; i++){
            for(int j = 0; j < gm.maxWorldCol; j++){

                int worldX = i * gm.tileSize;
                int worldY = j * gm.tileSize;
                int screenX = worldX - gm.player.worldX + gm.player.screenX;
                int screenY = worldY - gm.player.worldY + gm.player.screenY;

                if(worldX + gm.tileSize > gm.player.worldX - gm.player.screenX &&
                    worldX - gm.tileSize< gm.player.worldX + gm.player.screenX &&
                    worldY + gm.tileSize> gm.player.worldY - gm.player.screenY &&
                    worldY - gm.tileSize < gm.player.worldY + gm.player.screenY){

                    g.drawImage(layout[i][j], screenX, screenY, gm.tileSize, gm.tileSize, null);
                }
            }
        }
    }
}
