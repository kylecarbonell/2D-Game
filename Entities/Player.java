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
    BufferedImage right1 = null;
    BufferedImage right2 = null;
    BufferedImage left1 = null;
    
    BufferedImage currentImage = null;

    public Player(Game gm, KeyHandler keyHandler){
        this.gm = gm;
        this.keyHandler = keyHandler;

        setValues();
        getImage();
    }

    public void setValues(){
        playerY = 100;
        playerX = 100;
        speed = 4;
    }

    public void getImage(){
        try{
            File right1File = new File("Character Sprites\\Player\\right1.png");
            File left1File = new File("Character Sprites\\Player\\left1.png");
            File right2File = new File("Character Sprites\\Player\\right2.png");
            right1 = ImageIO.read(right1File);
            right2 = ImageIO.read(right2File);
            left1 = ImageIO.read(left1File);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.up && playerY >= 0){
            playerY -= speed;
        }
        if(keyHandler.down && playerY <= screenHeight-tileSize){
            playerY += speed;
        }
        if(keyHandler.left && playerX >= 0){
            playerX -= speed;
            direction = "left";
        }
        if(keyHandler.right && playerX <= screenWidth-tileSize){
            playerX += speed;
            if(animationX % 2 == 0){
                direction = "right1";
            }
            else{
                direction = "right2";
            }
            System.out.println(direction);
        }

        
    }
    
    public void paint(Graphics2D g){
        switch(direction){
            case "left":
                currentImage = left1;
                break;
            case "right":
                currentImage = right1;
                break;
            default:
                currentImage = right1;
        }
        g.setColor(Color.white);
        g.drawImage(currentImage, playerX, playerY, tileSize, tileSize, null);

        g.dispose();
    }
}
