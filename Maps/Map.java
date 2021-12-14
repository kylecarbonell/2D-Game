package Maps;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.*;
import Main.Game;

public class Map extends Tile{
    Game gm;
    Tile[] layout;


    public Map(Game gm){
        this.gm = gm;
        layout = new Tile[gm.rows];
        getImage();
    }

    public void getImage(){
        File grassFile = new File("Character Sprites\\Terrain\\GrassTile.png");
        File sandFile = new File("Character Sprites\\Terrain\\SandTile.png");
        try {
            for(int i = 0; i < gm.rows; i++){
                if(i == gm.rows/2 || i == gm.rows/2 - 1){
                    layout[i] = new Tile();
                    layout[i].image = ImageIO.read(sandFile);
                }
                else{

                    layout[i] = new Tile();
                    layout[i].image = ImageIO.read(grassFile);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void paint(Graphics2D g) throws IOException{
        for(int i = 0; i < gm.rows; i++){
            for(int j = 0; j < gm.col; j++){
                g.drawImage(layout[i].image, 0 + i*gm.tileSize, 0 + j*gm.tileSize, gm.tileSize, gm.tileSize, null);
            }
        }

        //Work on reading and splitting files and text
        File mapFile = new File("Maps\\Map.txt");
        try {
            BufferedReader mapReader = new BufferedReader(new FileReader(mapFile));
            String read = "";
            while((read = mapReader.readLine()) != null){
                String[] split = read.split("\\s+");

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
