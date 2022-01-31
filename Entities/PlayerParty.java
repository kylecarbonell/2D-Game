package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import Fruit.Fruit;
import Main.Game;

public class PlayerParty {
    Connection con;
    Game gm;

    public PlayerParty(Game gm){
        this.gm = gm;
        loadDriver();
    }

    public void loadDriver(){
        //Creates a user specific database
        String url = "jdbc:mysql://localhost:3306/playerparty";
        String user = "dazaichan";
        String password = "Lu2nglu2.";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to databse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveParty(String[] info){
        String save = "INSERT INTO party VALUES (";
        for(String i : info){
            save += "'" + i + "'" + ", ";
        }
        save += ")";

        try {
            Statement stmnt = con.createStatement();
            stmnt.execute(save);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getFruit(){
        //FINISH THIS LATER
        String getName = "SELECT * FROM party WHERE currentHealth > 0 LIMIT 1";
        String name = "";
        int level = 0;
        int health = 0;
        int experience = 0;
        int i = 1;
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(getName);
            while(result.next()){
                name = result.getString(i);
                health = result.getInt(i+1);
                experience = result.getInt(i+2);
                level = result.getInt(i+3);

                System.out.println(health);
                
                gm.player.party[i-1] = new Fruit(gm.setFruit.getFruit(name), level, health, experience);
                //gm.player.party[i-1] = gm.setFruit.getFruit(name);
                i++;
            }

            // for(Fruit fruit : gm.player.party){
            //     System.out.println(fruit.name + fruit.currentHealth + fruit.experience);
            // }
            //return name;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
