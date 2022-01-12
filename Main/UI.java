package Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UI {

    Game gm;
    Graphics2D g;
    public Font pixelFont;

    public int battleStateChoice = 0;

    public UI(Game gm){
        this.gm = gm;

        //InputStream is = getClass().getResourceAsStream("Fonts\\ARCADECLASSIC.TTF");
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\PressStart2P.ttf"));
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g){
        this.g = g;

        if(gm.gamestate == gm.pauseState){
            paintPause("PAUSED");
        }
        else if(gm.gamestate == gm.loadingState){
            paintLoading("LOADING...");
        }
        else if(gm.gamestate == gm.fruitState){
            paintFruit();
        }

    }

    public void paintFruit(){
        System.out.println(gm.player.party.toString());
        g.setColor(Color.black);
        g.fillRect(0,0,gm.screenWidth,gm.screenHeight);
        //Main pause text
        g.setFont(new Font("MonoSpaced", Font.PLAIN, 75));
        g.setColor(Color.white);
    }

    public void paintPause(String text){
        int x = getCenterX(text);
        int y =gm.screenHeight/2;
        
        g.setColor(Color.black);
        g.fillRect(0,0,gm.screenWidth,gm.screenHeight);
        //Main pause text
        g.setFont(new Font("MonoSpaced", Font.PLAIN, 75));
        g.setColor(Color.white);
        g.drawString(text, x-100, y);

        //Instructions
        String instruct = "PRESS ESCAPE TO CONTINUE";
        x = getCenterX(instruct);
        g.setFont(new Font("MonoSpaced", Font.PLAIN, 30));
        g.setColor(Color.white);
        g.drawString(instruct, x+325, y+50);
    }

    public void paintLoading(String text){
        int x = getCenterX(text);
        int y =gm.screenHeight/2;
        
        g.setColor(Color.black);
        g.fillRect(0,0,gm.screenWidth,gm.screenHeight);
        //Main pause text
        g.setFont(new Font("MonoSpaced", Font.PLAIN, 75));
        g.setColor(Color.white);
        g.drawString(text, x-100, y);
    }

    public int getCenterX(String text){
        int length =(int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = gm.screenWidth/2 - length/2;

        return x;
    }

}
