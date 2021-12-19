package Main;

import java.awt.*;

public class UI {

    Game gm;
    Graphics2D g;
    Font font;

    public UI(Game gm){
        this.gm = gm;
    }

    public void paint(Graphics2D g){
        this.g = g;

        if(gm.gamestate == gm.pauseState){
            paintPause("PAUSED");
        }
        else if(gm.gamestate == gm.loadingState){
            paintLoading("LOADING...");
            
        }
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
