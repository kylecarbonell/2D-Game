package Entities;

import Main.Game;
import Main.KeyHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{

    Game gm;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(Game gm, KeyHandler keyHandler){
        this.gm = gm;
        this.keyHandler = keyHandler;

        screenX = screenWidth / 2;
        screenY = screenHeight / 2;

        solidArea = new Rectangle(6, 11, 5, 6);
        

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
            if(keyHandler.up && worldY >= 0){
                direction = "up";
            }
            if(keyHandler.down && worldY <= gm.maxWorldCol*gm.tileSize - gm.tileSize){
                direction = "down";
            }
            if(keyHandler.left && worldX >= 0){
                direction = "left";
            }
            if(keyHandler.right && worldX <= gm.maxWorldRow*gm.tileSize - gm.tileSize){
                direction = "right";
            }

            collisionOn = false;
            gm.collision.colCheck(this);

            if(!collisionOn){
                switch(direction){
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "default":
                        currentImage = defaultImage;
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
                    System.out.println("balls");
                }
                else{
                    currentImage = up2;
                }
            case "default":
                currentImage = defaultImage;
        }
        g.setColor(Color.white);
        g.drawImage(currentImage, screenX - gm.tileSize/2, screenY - gm.tileSize/2, gm.tileSize, gm.tileSize, null);

        g.dispose();
    }
}
