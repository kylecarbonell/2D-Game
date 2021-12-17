package Maps;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.awt.*;
import Main.Game;

public class Map extends Tile{
    Game gm;
    public File[] mapFiles = new File[2];
    public int[][] layout;
    public int[][] townLayout;
    public int[][] forestLayout;
    public Tile[] tiles;
    File file;

    int i = 0;
    int j = 0;

    public Map(Game gm){
        this.gm = gm;
        layout = new int[gm.rows][gm.col];
        townLayout= new int[gm.rows][gm.col];
        forestLayout = new int[gm.rows][gm.col];
        tiles = new Tile[100];
        getImage();
        instantiateMap();
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
            tiles[0].collision = true;

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

            mapFiles[0] = new File("Maps\\Map.txt");
            mapFiles[1] = new File("Maps\\Forest.txt");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void instantiateMap(){
        //Create and instantiate 2 different maps
        try {
            for(File file : mapFiles){
                if(file == mapFiles[0]){
                    townLayout = layout;
                }
                else if(file == mapFiles[1]){
                    forestLayout = layout;
                }
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
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g, int state){
        int[][] temp = townLayout;
        if(state == gm.forestState){
            temp = forestLayout;
        }
        if(state == gm.playState){
            temp = townLayout;
        }

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

                    g.drawImage(tiles[temp[i][j]].image, screenX, screenY, gm.tileSize, gm.tileSize, null);
                }
            }
        }
    }
}
