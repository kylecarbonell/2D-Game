package Battles;

import Fruit.Fruit;
import Main.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Battle implements ActionListener {
    Game gm;
    double defenseMultiplier = 0.2;
    public final int FIGHT = 0;
    public final int RUN = 1;

    public int choice = 0;
    public int playerChoice;

    Fruit player;
    Fruit ai;
    public int state;

    public final int entranceState = 0;
    public final int sequenceState = 1;
    public final int dialogueState = 2;
    public final int fightingState = 3;
    public final int healthState = 4;

    public String[] entranceDialogue =  new String[2];
    public String[] fightDialogue = new String[4];
    public String[] healthDialogue = new String[2];

    BufferedImage background = null;

    Graphics2D g;

    String text = "";
    boolean pressEnter = true;

    double aiHealth;
    double aiMaxHealth;

    int aiX = 1300;
    int aiY = 100;

    Timer animation;
    int animationCounter = 0;

    public Stack<String> stack;

    public Battle(Game gm){
        this.gm = gm;
        state = 0;
        try {
            background = ImageIO.read(new File("Character Sprites\\Fruits\\BattleBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        animation = new Timer(100, this);
        stack = new Stack<String>();

        
    }

    public void instantiateFruits(int i){
        player = new Fruit(gm.fruits[0]);
        ai = new Fruit(gm.fruits[2]);
        aiMaxHealth = ai.health;
        aiHealth = 500 * (ai.currentHealth / aiMaxHealth);
        state = dialogueState;
    }

    public void update(){
        enter();
    }

    public void enter(){
        animation.setActionCommand("EntranceText");
        animation.setDelay(10);
        animation.start();

        entranceDialogue[0] = "You have encountered a wild " + ai.name;
        entranceDialogue[1] = "FIGHT!";

        stack.push(entranceDialogue[1]);        
        stack.push(entranceDialogue[0]);
    }
    
    public void checkHealth(){
        state = healthState;
        if(ai.currentHealth <= 0){
            text = ai.name + " has fainted";
            return;
        }

        if(player.currentHealth <= 0){
            text = player.name + " has fainted";
            return;
        }

        text = "FIGHT!";
        state = fightingState;
    }

    public void run(){
        gm.gamestate = gm.forestState;
    }

    public void fight(){
        state = fightingState;
        Fruit first;
        Fruit second;
        if(player.speed >= ai.speed){
            first = player;
            second = ai;
        }
        else{
            first = ai;
            second = player;
        }

        animation.setActionCommand("Ai Attack");
        attack(first, second);
    }

    public void attack(Fruit first, Fruit second){
            animation.start();
            animation.setDelay(10);

            sleep(500);
            second.currentHealth -= first.damage - (int)(defenseMultiplier * second.defense);
            checkHealth();
            
            first.currentHealth -= second.damage - (int)(defenseMultiplier * first.defense);
            checkHealth();
    }

    public void paint(Graphics2D g){
        this.g = g;

        int x = 100;
        int y = 700;
        int width = gm.screenWidth - (gm.tileSize*4);
        int height = gm.tileSize*5;
        
        paintBattle();
        if(!stack.isEmpty()){
            if(state == dialogueState){
                paintBattleMessage(stack.peek(), pressEnter);
            }
        }
        else{
            state = sequenceState;
        }

        if(state == sequenceState){
            playerUI();
        }
    }

    public void paintBattle(){
        //Temp Background
        g.setColor(Color.gray);
        g.fillRect(0, 0, gm.screenWidth, gm.screenHeight);
        
        //Ai Oval
        g.drawImage(background, 1125, 150, 600, 360, null);
        
        //Ai Character 1300 100
        g.drawImage(ai.image, aiX, aiY, 250, 250, null);
        g.drawString(ai.name, 700, 50);

        //Ai Health Bar
        g.setColor(Color.white);
        g.drawRect(700, 100, 500, 50);

        
        g.fillRect(700, 100, (int)aiHealth, 50);
    }

    public void playerUI(){
        int x = 100;
        int y = 700;
        int width = gm.screenWidth - (gm.tileSize*4);
        int height = gm.tileSize*5;
        int wordX = gm.screenWidth - gm.tileSize*7;
        int wordY = 800;
        //Player UI Choice
        g.setColor(Color.black);
        g.fillRoundRect(x, y, width, height, 25, 25);

        //Border
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x + 5, y + 5, width-10, height-10, 25, 25);
        
        //Fight
        g.setFont(gm.ui.pixelFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
        g.drawString("FIGHT", wordX, wordY);

        //Run
        g.setFont(gm.ui.pixelFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
        g.drawString("RUN", wordX, wordY+150);

        if(choice == 0){
            //Fight choice
            g.drawString("X", wordX - 100, wordY);
        }
        else{
            //Run Choice
            g.drawString("X", wordX-100, wordY+150);
        }
    }

    public void paintBattleMessage(String text, boolean pressEnter){
        //Player UI Choice
        state = dialogueState;
        g.setColor(Color.black);
        g.fillRoundRect(100, 700, gm.screenWidth - (gm.tileSize*4), gm.tileSize*5, 25, 25);

        //Border
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(105, 705, gm.screenWidth - (gm.tileSize*4)-10, gm.tileSize*5-10, 25, 25);

        g.setFont(gm.ui.pixelFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 30));
        g.drawString(text, 200, 800);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String action = e.getActionCommand();

        if(action.equals("EntranceText")){
            if(gm.keyHandler.enterPressed){
                if(!stack.isEmpty()){
                    stack.pop();
                    gm.keyHandler.enterPressed = false; 
                }
                else{
                    state = sequenceState;
                    gm.keyHandler.enterPressed = false; 
                }
            }
        }

        if(action.equals("Ai Attack")){
            //Pause
            sleep(500);
            
            if(animationCounter == 0){
                aiX -= 50;
                animationCounter = 1;

                aiHealth = 500 * (ai.currentHealth / aiMaxHealth);
            }
            else{
                aiX += 50;
                animationCounter = 0;
                animation.stop();
            }


            
        }
    }

    public void sleep(int time){
        try { 
            Thread.sleep(time); 
        } catch (InterruptedException e1) {}
    }
    
}
