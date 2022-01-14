package Entities;

import Main.Game;
import Main.KeyHandler;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Fruit.Fruit;

public class Player extends Entity{

    Game gm;
    KeyHandler keyHandler;

    public Fruit[] party;

    public final int screenX;
    public final int screenY;

    int eventIndex;
    int objIndex;

    File partyFile;

    public Player(Game gm, KeyHandler keyHandler){
        this.gm = gm;
        this.keyHandler = keyHandler;

        screenX = screenWidth / 2;
        screenY = screenHeight / 2;

        solidArea = new Rectangle(6, 11, 5, 3);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        party = new Fruit[6];

        partyFile = new File("Entities\\PlayerParty.txt");

        setValues();
        getImage();
    }

    public void setValues(){
        //Spawn
        worldX = gm.tileSize*23; 
        worldY = gm.tileSize*28;
        speed = 4;     
    }

    public void getImage(){
        try{
            File defaultFile = new File("Character Sprites\\Player\\default.png");
            File left1File = new File("Character Sprites\\Player\\left1.png");
            File left2File = new File("Character Sprites\\Player\\left2.png");
            File right1File = new File("Character Sprites\\Player\\right1.png");
            File right2File = new File("Character Sprites\\Player\\right2.png");
            File up1File = new File("Character Sprites\\Player\\up1.png");
            File up2File = new File("Character Sprites\\Player\\up2.png");

            defaultImage = ImageIO.read(defaultFile);
            left1 = ImageIO.read(left1File);
            left2 = ImageIO.read(left2File);
            right1 = ImageIO.read(right1File);
            right2 = ImageIO.read(right2File);
            up1 = ImageIO.read(up1File);
            up2 = ImageIO.read(up2File);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right){

            //Checks collections
            collisionOn = false;
            gm.collision.checkCollision(this, true);

            //Object Actions
            objIndex = gm.collision.checkObject(this, true);
            switchItems();
            
            //Events
            eventIndex = gm.collision.eventCollision(this, true, gm.gamestate);
            switchEvents();

            //Pokemon Encounter
            //getParty();
            //TEMPORARY
            encounter();

            if(!collisionOn){
                if(keyHandler.up && worldY >= 0){
                    direction = "up";
                    worldY -= speed;
                }
                if(keyHandler.down && worldY <= gm.maxWorldCol*gm.tileSize - gm.tileSize){
                    direction = "down";
                    worldY += speed;
                }
                if(keyHandler.left && worldX >= 0){
                    direction = "left";
                    worldX -= speed;
                }
                if(keyHandler.right && worldX <= gm.maxWorldRow*gm.tileSize - gm.tileSize){
                    direction = "right";
                    worldX += speed;
                }
            }

            animationCounter++;

            if(animationCounter > 10){
                if(animationNum == 1){
                    animationNum = 2;
                }
                else if(animationNum == 2){
                    animationNum = 1;
                }
                animationCounter = 0;
            }
        }
        else{
            direction = "default";
        }
    }
    
    public void paint(Graphics2D g){
        switch(direction){
            case "left":
                if(animationNum == 1){
                    currentImage = left1;
                }
                else{
                    currentImage = left2;
                }
                break;
            case "right":
                if(animationNum == 1){
                    currentImage = right1;
                }
                else{
                    currentImage = right2;
                }
                break;
            case "up":
                if(animationNum == 1){
                    currentImage = up1;
                }
                else{
                    currentImage = up2;
                }
                break;
            case "default":
                currentImage = defaultImage;
        }
        g.setColor(Color.white);
        g.drawImage(currentImage, screenX - gm.tileSize/2, screenY - gm.tileSize/2, gm.tileSize, gm.tileSize, null);

        g.dispose();
    }

    public void saveParty(){
        try {
            FileWriter writer = new FileWriter(partyFile);

            for(Fruit fruit : party){
                writer.write(fruit.name);
                writer.write(fruit.currentHealth);
                writer.write(fruit.level);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void getParty(){
        try {
            Scanner reader = new Scanner(partyFile);

            int i = 0;

            String name = "";
            int health = 0;
            int level = 0;

            while(reader.hasNextLine()){
                name = reader.nextLine();
                health = Integer.valueOf(reader.nextInt());
                level = Integer.valueOf(reader.nextInt());

                System.out.println(name);
                System.out.println(health);
                System.out.println(level);

                party[i] = new Fruit(gm.setFruit.getFruit(name), level, health);
                System.out.println(party[i].name + "Health" + party[i].health + "");
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void encounter(){
        //TODO: Change Implement Catch feature

        if(inEncounter){
            Random rand = new Random();
            int randNum = rand.nextInt(200);
            if(randNum == 1){
                gm.stackState.push(gm.battleMenuState);
                randNum = rand.nextInt(3);
                gm.battle.instantiateFruits(randNum);
                gm.battle.update();
            }
        }
    }

    public void switchEvents(){
        if(eventIndex == 0){
            teleport();
        }
    }

    public void switchItems(){
        if(objIndex == 0){
            pickupObj();
        }
    }

    public void pickupObj(){
        gm.obj[objIndex] = null;
    }

    public void teleport(){
        setValues();
        gm.gamestate = gm.events[eventIndex].teleportState;
        System.out.println(gm.gamestate);
    }
}
