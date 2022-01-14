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
        gm.fruits[0] = new Fruit();
        gm.fruits[0].name = "Strawberry Terry";
        gm.fruits[0].baseDamage = 10;
        gm.fruits[0].baseDefense  = 20;
        gm.fruits[0].baseHealth = 50;
        gm.fruits[0].baseSpeed = 10;
        gm.fruits[0].setStats();

        gm.fruits[1] = new Fruit();
        gm.fruits[1].name = "Mochi";
        gm.fruits[1].baseDamage = 10;
        gm.fruits[1].baseDefense  = 20;
        gm.fruits[1].baseHealth = 50;
        gm.fruits[1].baseSpeed = 10;
        gm.fruits[1].setStats();

        gm.fruits[2] = new Fruit();
        gm.fruits[2].name = "Bluegary";
        gm.fruits[2].baseDamage = 20;
        gm.fruits[2].baseDefense  = 10;
        gm.fruits[2].baseHealth = 50;
        gm.fruits[2].baseSpeed = 15;
        gm.fruits[2].image = blueGaryImage;
        gm.fruits[2].setStats();

        gm.fruits[3] = new Fruit();
        gm.fruits[3].name = "Snapple";
        gm.fruits[3].baseDamage = 30;
        gm.fruits[3].baseDefense  = 5;
        gm.fruits[3].baseHealth = 40;
        gm.fruits[3].baseSpeed = 20;
        gm.fruits[3].setStats();
    }

    public Fruit getFruit(String name){
        for(Fruit i : gm.fruits){
            if(i.name.equals(name)){
                return i;
            }
        }

        return gm.fruits[0];
    }
}
