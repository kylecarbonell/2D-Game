package Main;

import javax.swing.*;

import Entities.Player;
import Maps.Map;

import java.awt.*;

public class Game extends JPanel implements Runnable{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    // int screenWidth = 1600;
    // int screenHeight = 900;

    JFrame window;
    Thread thread;

    //64
    public int tileSize = 64;
    public int rows = screenWidth-2/tileSize;
    public int col = screenHeight+2/tileSize;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    

    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    public Map map = new Map(this);
    public Collision collision = new Collision(this);

    int runOnce = 1;

    Game(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.addKeyListener(keyHandler);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setDoubleBuffered(true);
        this.setBackground(Color.white);
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
        player.update();
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        map.paint(g2);
        player.paint(g2);

        g2.dispose();
    }


}
