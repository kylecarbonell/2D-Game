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

    public void addToParty(String[] info, int id){
        String save = "INSERT INTO party VALUES (";
        save += "'" + info[0] + "', ";
        save += info[1] + ", " + info[2] + ", "+ info[3] + ", " + id;
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
        String getName = "SELECT * FROM party";
        String name = "";
        int level = 0;
        int health = 0;
        int experience = 0;
        int i = 1;
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(getName);
            while(result.next()){
                name = result.getString("name");
                health = result.getInt("currentHealth");
                experience = result.getInt("experience");
                level = result.getInt("level");

                System.out.println(health);
                
                gm.player.party[i-1] = new Fruit(gm.setFruit.getFruit(name), level, health, experience);
                i++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateParty(){
        String save;
        String[] info = new String[3];

        for(int i = 0; i < gm.player.party.length; i++){
            if(gm.player.party[i] != null){
                info = gm.player.party[i].getInfo();

                save = "UPDATE party SET currentHealth = " + info[1] + ", experience = " + 
                info[2] + ", level = " +  info[3] + " WHERE id = " + i;

                try {
                    Statement stmnt = con.createStatement();
                    stmnt.execute(save);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } 
        }

        
    }


}
