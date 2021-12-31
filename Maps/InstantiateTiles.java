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
    
    public InstantiateTiles(Game gm){
        this.gm = gm;
        tiles = new Tile[100];
        getImage();
    }

    public void getImage(){
        //Sand paths corners
        File botLeftSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Bot_LeftCorner.png");
        File botRightSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Bot_RightCorner.png");
        File topLeftSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Top_LeftCorner.png");
        File topRightSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Top_RightCorner.png");
        
        //Sand paths straights
        File topSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Top.png");
        File botSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Bot.png");
        File leftSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Left.png");
        File rightSand = new File("Character Sprites\\Terrain\\Paths\\Sand_Path_Right.png");


        File grassFile = new File("Character Sprites\\Terrain\\GrassTile.png");
        File sandFile = new File("Character Sprites\\Terrain\\Paths\\Sand_Path.png");
        
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(grassFile);
            tiles[0].collision = false;
            tiles[0].encounter = true;

            //Sand Paths
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(sandFile);
            tiles[1].collision = false;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(botLeftSand);
            tiles[2].collision = false;
            
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(botRightSand);
            tiles[3].collision = false;
            
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(topRightSand);
            tiles[4].collision = false;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(topLeftSand);
            tiles[5].collision = false;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(topSand);
            tiles[6].collision = false;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(leftSand);
            tiles[7].collision = false;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(rightSand);
            tiles[8].collision = false;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(botSand);
            tiles[9].collision = false;
            


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void instantiateMap(int[][] layout, File file){
        //Create and instantiate 2 different maps
        try {
            int i = 0;
            int j = 0;
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
