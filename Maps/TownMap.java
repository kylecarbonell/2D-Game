package Maps;
import java.io.File;

import java.awt.*;
import Main.Game;

public class TownMap extends Tile{
    Game gm;
    public int[][] townLayout;

    File file = new File("Maps\\TownMap.txt");

    public TownMap(Game gm){
        this.gm = gm;
        townLayout = new int[gm.rows][gm.col];
        gm.tiles.instantiateMap(townLayout, file);
    }

    public void paint(Graphics2D g){
        gm.tiles.paint(g, townLayout);
    }
}
