package Maps;
import java.io.File;

import java.awt.*;
import Main.Game;

public class ForestMap {
    
    Game gm;
    public int[][] forestLayout;

    File file = new File("Maps\\ForestMap.txt");

    public ForestMap(Game gm){
        this.gm = gm;
        forestLayout = new int[gm.rows][gm.col];
        gm.tiles.instantiateMap(forestLayout, file);
    }
    
    public void paint(Graphics2D g){
        gm.tiles.paint(g, forestLayout);
    }


}
