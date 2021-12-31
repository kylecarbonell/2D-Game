package Main;

import javax.swing.*;

import Battles.Battle;
import Entities.Player;
import Events.Events;
import Events.SetEvents;
import Fruit.SetFruit;
import Fruit.Fruit;
import Items.Items;
import Items.SetItems;
import Maps.ForestMap;
import Maps.InstantiateTiles;
import Maps.TownMap;

import java.awt.*;

public class Game extends JPanel implements Runnable{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

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
    public KeyHandler keyHandler = new KeyHandler(this);
    public InstantiateTiles tiles = new InstantiateTiles(this);
    public Collision collision = new Collision(this);
    public UI ui = new UI(this);

    public TownMap townMap = new TownMap(this);
    public ForestMap forestMap = new ForestMap(this);

    //Events
    public Events[] events = new Events[10];
    public SetEvents setEvents = new SetEvents(this);
    
    //Pokemon Fruit
    public Fruit[] fruits = new Fruit[10];
    public SetFruit setFruit = new SetFruit(this);
    public Battle battle = new Battle(this);
    
    //Objects and players
    public Player player = new Player(this, keyHandler);
    public Items obj[] = new Items[10]; 
    SetItems setItems = new SetItems(this);
    
    //Game states
    public int gamestate;
    public final int titleState = 0;
    public final int pauseState = 1;
    public final int loadingState = 2;
    public final int townState = 3;
    public final int forestState = 4;
    public final int battleMenuState = 5;
    public final int battleSequenceState = 6;
    

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
        setUp();
        start();
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void setUp(){
        setFruit.instantiate();
        setItems.instantiate();
        setEvents.instantiate();
        gamestate = townState;
    }

    @Override
    public void run() {
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
        if(gamestate == townState){
            player.update();
        }
        if(gamestate == pauseState){

        }
        if(gamestate == forestState){
            player.update();
        }
        if(gamestate == battleMenuState){

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
        else if(gamestate == loadingState){
            ui.paint(g2);
        }   
        else if(gamestate == battleMenuState){
            battle.paint(g2);
        }
        else{
            if(gamestate == townState){
                //TownMap
                townMap.paint(g2);
            }
            else if(gamestate == forestState){
                forestMap.paint(g2);
            }
            //Paint world objects
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null && obj[i].gamestate == gamestate){
                    obj[i].paint(g2, gamestate);
                }
            }
            
            //Paint main 
            player.paint(g2);
            ui.paint(g2);

            
        }
        
        

        g2.dispose();
    }


}
