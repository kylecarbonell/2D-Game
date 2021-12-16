package Main;
import java.awt.event.*;

public class ActionHandler implements ActionListener{

    Game gm;
    public String code;
    public ActionHandler(Game gm){
        this.gm = gm;
        code = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(code.equals("Water")){
            System.out.println("Water");
        }
        // if(code.equals("Tree")){
        //     System.out.println("Tree");
        // }
        // if(code.equals("Stone")){
        //     System.out.println("Stone");
        // }
    }
    
}
