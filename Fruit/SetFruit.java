package Fruit;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.*;

import Main.Game;

public class SetFruit {
    
    Game gm;
    BufferedImage blueGaryImage;

    public SetFruit(Game gm){
        this.gm = gm;
        getImages();
    }

    public void getImages(){
        File blueGaryFile = new File("Character Sprites\\Fruits\\Bluegary.png");

        try {
            blueGaryImage = ImageIO.read(blueGaryFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void instantiate(){
        //id = 0 Strongberry
        gm.fruits[0] = new Fruit(10, 20, 50, 10, 5);
        gm.fruits[0].name = "Strawberry Terry";
        gm.fruits[0].setStats();

        gm.fruits[1] = new Fruit(10, 20, 50, 10, 6);
        gm.fruits[1].name = "Mochi";
        gm.fruits[1].setStats();

        gm.fruits[2] = new Fruit(20, 10, 50, 15, 6);
        gm.fruits[2].name = "Bluegary";
        gm.fruits[2].image = blueGaryImage;
        gm.fruits[2].setStats();

        gm.fruits[3] = new Fruit(30, 5, 40, 20, 4);
        gm.fruits[3].name = "Snapple";
        gm.fruits[3].setStats();
    }

    public Fruit getFruit(String fruitName){
        for(Fruit i : gm.fruits){
            String name = i.name;
            if(name.equals(fruitName)){
                return i;
            }
        }

        return gm.fruits[2];
    }
}
