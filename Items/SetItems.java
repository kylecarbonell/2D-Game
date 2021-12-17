package Items;

import Main.Game;

public class SetItems {
    
    Game gm;
    public SetItems(Game gm){
        this.gm = gm;
    }

    public void instantiate(){
        gm.obj[0] = new Key(gm, 24, 28, "Key1");
        
    }
}
