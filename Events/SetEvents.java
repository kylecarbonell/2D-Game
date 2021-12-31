package Events;

import Main.Game;

public class SetEvents{

    Game gm;
    public SetEvents(Game gm){
        this.gm = gm;
    }

    public void instantiate(){
        //Go to forest
        gm.events[0] = new ForestEntrance(gm, 47, 26, gm.townState);
        gm.events[1] = new ForestEntrance(gm, 47, 27, gm.townState);
        
    }
}