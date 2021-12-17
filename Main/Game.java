package Main;

import javax.swing.*;

import Entities.Player;
import Items.Items;
import Items.SetItems;
import Maps.Map;

import java.awt.*;

public class Game extends JPanel implements Runnable{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    JFrame window;
    Thread thread;

    //Sizes and measurements
    public int tileSize = 64;
    public int rows = screenWidth-2/tileSize;
    public int col = screenHeight+2/tileSize;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    

    int fps = 60;

    //System classes
    KeyHandler keyHandler = new KeyHandler(this);
    public Collision collision = new Collision(this);
    public Map map = new Map(this);
    public UI ui = new UI(this);

    //Objects and players
    public Player player = new Player(this, keyHandler);
    public Items obj[] = new Items[10]; 
    SetItems set = new SetItems(this);

    //Game states
    public int gamestate;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int forestState = 3;
    

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
        setUp();
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void setUp(){
        set.instantiate();
        gamestate = 3;
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
        if(gamestate == playState){
            player.update();
        }
        if(gamestate == pauseState){

        }
        if(gamestate == forestState){
            player.update();
        }

    }

    

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if(gamestate == pauseState){
            ui.paint(g2);
        }
        else{
            //TownMap
            map.paint(g2, 3);

            //Paint world objects
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].paint(g2);
                }
            }
            //Paint main 
            player.paint(g2);
            ui.paint(g2);
        }
        
        

        g2.dispose();
    }


}
