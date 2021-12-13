package Main;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    JFrame window;
    Thread thread;

    int tileSize = 48;
    int playerX = 100;
    int playerY = 100;
    int speed = 8;

    int fps = 60;
    KeyHandler keyHandler = new KeyHandler();

    Game(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.addKeyListener(keyHandler);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        window.add(this);
        start();
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        double nextDraw = 1000000000/fps;
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();

        while(thread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / nextDraw;
            lastTime = currentTime;

            if(delta >= 1){
                update();   
                repaint();
                delta--;
            }

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
        }
        if(keyHandler.right && playerX <= screenWidth-tileSize){
            playerX += speed;
        }
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }


}
