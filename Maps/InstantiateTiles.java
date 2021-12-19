package Maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.*;
import Main.Game;

public class InstantiateTiles {

    Game gm;
    public Tile[] tiles;
    
    int i = 0;
    int j = 0;
    
    public InstantiateTiles(Game gm){
        this.gm = gm;
        tiles = new Tile[100];
        getImage();
    }
    public void getImage(){
        File grassFile = new File("Character Sprites\\Terrain\\GrassTile.png");
        File sandFile = new File("Character Sprites\\Terrain\\SandTile.png");
        File stoneBrickFile = new File("Character Sprites\\Terrain\\StoneBrickTile.png");
        File waterFile = new File("Character Sprites\\Terrain\\WaterTile.png");
        File treeFile = new File("Character Sprites\\Terrain\\TreeTile.png");

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(grassFile);
            tiles[0].collision = false;

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(sandFile);
            tiles[1].collision = false;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(stoneBrickFile);
            tiles[2].collision = true;
            tiles[2].interactable = true;
            tiles[2].code = "Stone";

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(waterFile);
            tiles[3].collision = true;
            tiles[3].interactable = true;
            tiles[3].code = "Water";

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(treeFile);
            tiles[4].collision = true;
            tiles[4].interactable = true;
            tiles[4].code = "Tree";


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void instantiateMap(int[][] layout, File file){
        //Create and instantiate 2 different maps
        getImage();
        try {
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    String line = scan.nextLine();
                    Scanner lineScan = new Scanner(line);
    
                    while(lineScan.hasNext()){
                        layout[i][j] = Integer.valueOf(lineScan.next());
                        i++;
                    }
                    i = 0;
                    j++;
                    lineScan.close();
                }
                scan.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g, int[][] layout){
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

                    g.drawImage(gm.tiles.tiles[layout[i][j]].image, screenX, screenY, gm.tileSize, gm.tileSize, null);
                }
            }
        }
    }
}
