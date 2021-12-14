package Entities;

import java.awt.*;

public class Entity {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    int playerX;
    int playerY;
    int speed;

    String direction = "";

    int tileSize = 48;
    int animationX = 0;
    int animationY = 0;
}
