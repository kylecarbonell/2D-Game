package Entities;

import java.awt.*;
import java.awt.image.*;

public class Entity {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    public int worldX;
    public int worldY;
    public int speed;

    public String direction;

    int animationCounter = 0;
    int animationNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    //Images
    BufferedImage defaultImage = null;
    BufferedImage left1 = null;
    BufferedImage left2 = null;
    BufferedImage right1 = null;
    BufferedImage right2 = null;
    BufferedImage up1 = null;
    BufferedImage up2 = null;
    
    BufferedImage currentImage = null;
}
