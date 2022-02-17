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

    boolean battleFinished;
    boolean popped = false;

    public int choice = 0;
    public int playerChoice;

    Fruit player;
    Fruit ai;
    public int state;

    public final int entranceState = 0;
    public final int sequenceState = 1;
    public final int dialogueState = 2;

    public String[] healthDialogue = new String[2];

    BufferedImage background = null;
    int x = 225;
    int y = 550;
    int width;
    int height;

    Graphics2D g;

    String text = "";
    boolean pressEnter = true;

    Timer animation;
    int animationCounter = 0;
    boolean animationComplete = false;
    
    public Stack<String> stack;

    Fruit first;
    Fruit second;

    int tempFirst = 0;
    int tempSecond = 0;

    String endingText;

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

        this.width = gm.screenWidth - (int)(gm.tileSize * 7);
        this.height = (int)(gm.tileSize*3.5);
    }

    public Battle(Game gm, int i){
        this.gm = gm;
        state = 0;
        try {
            background = ImageIO.read(new File("Character Sprites\\Fruits\\BattleBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        animation = new Timer(100, this);
        stack = new Stack<String>();

        this.width = gm.screenWidth - (int)(gm.tileSize * 7);
        this.height = (int)(gm.tileSize*3.5);
        instantiateFruits(i);
        update();

    }

    public void instantiateFruits(int i){
        player = gm.player.getParty();
        ai = new Fruit(gm.fruits[i]);
        endingText = "";
        popped = false;
    }

    public void update(){
        enter();
    }

    public void enter(){
        stack.push("You have encountered a wild " + ai.name);        
        stack.push("FIGHT!");
        System.out.println(stack);
    }
    
    public void checkHealth(){
        if(!popped){
            if(ai.currentHealth <= 0){
                battleFinished = true;
                endingText = ai.name + " has fainted! You Win!";
                return;
            }
            else if(player.currentHealth <= 0){
                battleFinished = true;
                endingText = "Your " + player.name + " has fainted! You lose!"; 
                return;
            }
        }
    }

    public void catchFruit(){
        for(int i = 0; i < gm.player.party.length; i++){
            if(gm.player.party[i] == null){
                gm.player.party[i] = new Fruit(ai);
                gm.player.party[i].player = true;
                
                endingText = "You have caught " + ai.name;
                battleFinished = true;
                gm.party.addToParty(gm.player.party[i].getInfo(), i);
                break;
            }
        }
        
        stack.push("You have failed to catch " + ai.name);
    }

    public void run(){
        gm.stackState.pop();
    }

    public void fight(){        
        if(player.speed >= ai.speed){
            first = player;
            second = ai;
        }
        else{
            first = ai;
            second = player;
        }

        animation.setActionCommand("First Attack");
        animation.start();
        animation.setDelay(10);
    }

    public void attack(Fruit first, Fruit second){
        //Animate first attacker        
        sleep(500);
        second.currentHealth -= first.damage - (int)(defenseMultiplier * second.defense);
        if(second.currentHealth < 0){
            second.currentHealth = 0;
        }
        second.setBarHealth();
        paint(g);
        checkHealth();
    }

    public void paint(Graphics2D g){
        this.g = g;
        paintBattle();
        if(!stack.isEmpty()){
            paintBattleMessage(stack.peek());
            state = dialogueState;
        }
        else{
            state = sequenceState;
        }

        if(state == sequenceState){
            playerUI();
        }

        if(battleFinished && !popped){
            animation.setActionCommand("Battle Finished");
            animation.start();
            battleFinished = false;
            gm.party.updateParty();
        }
    }

    public void paintBattle(){
        //Temp Background
        g.setColor(Color.gray);
        g.fillRect(0, 0, gm.screenWidth, gm.screenHeight);
        
        //Ai Oval
        g.drawImage(background, 900, 100, 450, 270, null);
        
        //Ai Character
        g.drawImage(ai.image, ai.x, ai.y, 225, 225, null);
        g.drawString(ai.name, 700, 50);

        //Ai Health Bar
        g.setColor(Color.white);
        g.drawRect(550, 100, 350, 35);
        
        //Inside HealthBar
        g.fillRect(550, 100, (int)ai.barHealth, 35);

        //Player Oval
        g.drawImage(background, 300, 350, 450, 270, null);

        //Player Health Bar
        g.setColor(Color.white);
        g.drawRect(800, 350, 350, 35);

        g.fillRect(800, 350, (int)player.barHealth, 35);

        //Player Experience Bar
        g.setColor(Color.CYAN);
        g.drawRect(800, 385, 350, 15);

        g.fillRect(800, 385, (int)player.experienceBar, 15);

        //Player model
        g.drawImage(player.image, player.x, player.y, -225, 225, null);
    }

    public void playerUI(){
        int wordX = gm.screenWidth - gm.tileSize*7;
        int wordY = 625;
       
        //Player UI Choice 
        g.setColor(Color.black);
        g.fillRoundRect(x, y, width, height, 25, 25);

        //Border
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x + 5, y + 5, width-10, height-10, 25, 25);
        
        g.setFont(gm.ui.pixelFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 30));

        //Fight
        g.drawString("FIGHT", wordX, wordY);

        //Run
        g.drawString("RUN", wordX, wordY+100);

        //Catch
        g.drawString("CATCH", wordX, wordY+50);

        if(choice == 0){
            //Fight choice
            g.drawString("X", wordX - 100, wordY);
        }
        else if(choice == 1){
            g.drawString("X", wordX - 100, wordY+50);
        }
        else{
            //Run Choice
            g.drawString("X", wordX-100, wordY+100);
        }
    }

    public void paintBattleMessage(String text){
        //Player UI Choice
        g.setColor(Color.black);
        g.fillRoundRect(x, y, width, height, 25, 25);

        //Border
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x+5, y+5, width - 10, height-10, 25, 25);

        g.setFont(gm.ui.pixelFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
        g.drawString(text, 275, 600);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String action = e.getActionCommand();

        if(action.equals("First Attack")){
            if(animationCounter == 0){
                tempFirst = first.x;
                first.attack();
                animationCounter = 1;
            }
            else{
                first.x = tempFirst;
                animationCounter = 0;
                animationComplete = true;
            }

            if(animationComplete){
                checkHealth();
                attack(first, second);
                animationComplete = false;
                animation.stop();

                sleep(500);

                animation.setActionCommand("Second Attack");
                animation.start();
            }
        }

        if(action.equals("Second Attack")){
            if(animationCounter == 0){
                tempSecond = second.x;
                second.attack();
                animationCounter = 1;
            }
            else{
                second.x = tempSecond;
                animationCounter = 0;
                animationComplete = true;
            }

            if(animationComplete){
                checkHealth();
                attack(second, first);
                animationComplete = false;
                animation.stop();
            }
        }

        if(action.equals("Battle Finished")){
            if(!popped){
                stack.push(endingText);
                popped = true;
            }
            if(stack.isEmpty() && popped){
                gm.stackState.pop();
                popped = false;
                animation.stop();
            }
        }
    }

    public void sleep(int time){
        try { 
            Thread.sleep(time); 
        } catch (InterruptedException e1) {}
    }

    public void saveFruit(){
        //Add EXP to player pokemon
        
    }
    
}
