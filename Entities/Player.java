package Entities;

import Main.Game;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{

    Game gm;
    KeyHandler keyHandler;

    //Images
    BufferedImage defaultImage = null;
    BufferedImage left1 = null;
    BufferedImage left2 = null;
    BufferedImage right1 = null;
    BufferedImage right2 = null;
    
    BufferedImage currentImage = null;

    public final int screenX;
    public final int screenY;

    public Player(Game gm, KeyHandler keyHandler){
        this.gm = gm;
        this.keyHandler = keyHandler;

        screenX = screenWidth / 2;
        screenY = screenHeight / 2;

        setValues();
        getImage();
    }

    public void setValues(){
        worldY = gm.tileSize*13;
        worldX = gm.tileSize*6;
        speed = 4;
    }

    public void getImage(){
        try{
            File defaultFile = new File("Character Sprites\\Player\\default.png");
            File left1File = new File("Character Sprites\\Player\\left1.png");
            File left2File = new File("Character Sprites\\Player\\left2.png");
            File right1File = new File("Character Sprites\\Player\\right1.png");
            File right2File = new File("Character Sprites\\Player\\right2.png");
            defaultImage = ImageIO.read(defaultFile);
            left1 = ImageIO.read(left1File);
            left2 = ImageIO.read(left2File);
            right1 = ImageIO.read(right1File);
            right2 = ImageIO.read(right2File);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.moving){
            if(keyHandler.up && worldY >= 0){
                direction = "up";
                worldY -= speed;
            }
            if(keyHandler.down && worldY <= screenHeight-gm.tileSize){
                direction = "down";
                worldY += speed;
            }
            if(keyHandler.left && worldX >= 0){
                direction = "left";
                worldX -= speed;
            }
            if(keyHandler.right && worldX <= screenWidth-gm.tileSize){
                direction = "right";
                worldX += speed;
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
            case "default":
                currentImage = defaultImage;
        }
        g.setColor(Color.white);
        g.drawImage(currentImage, screenX - gm.tileSize/2, screenY - gm.tileSize/2, gm.tileSize, gm.tileSize, null);

        g.dispose();
    }
}
