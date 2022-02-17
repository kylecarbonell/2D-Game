package Fruit;

import java.awt.image.*;

public class Fruit implements Cloneable{
    public String name;

    public int level;

    public BufferedImage image;
    public int width;
    public int height;
    public int currentHealth;
    public int currentExperience;
    public int experience;

    public boolean player = true;

    public int x;
    public int y;

    private int baseHealth;
    private int baseDamage;
    private int baseSpeed;
    private int baseDefense;
    private int baseExperience;

    private int ivHealth;
    private int ivDamage;
    private int ivSpeed;
    private int ivDefense;

    public int health;
    public int damage;
    public int speed;
    public int defense;

    public int attack;

    public double barHealth;
    public double experienceBar;

    public Fruit(int baseDamage, int baseDefense, int baseHealth, int baseSpeed, int baseExperience){
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
        this.baseHealth = baseHealth;
        this.baseSpeed = baseSpeed;
        this.baseExperience = baseExperience;
    }

    public Fruit(Fruit fruit){
        this.player = false;
        copy(fruit);
    }

    public Fruit(Fruit fruit, int level, int currentHealth, int currentExperience){
        copy(fruit);
        this.currentHealth = currentHealth;
        this.level = level; 
        this.currentExperience = experience;
        setBarHealth();
    }


    public void setStats(){
        health = baseHealth + (level * 3);
        damage = baseDamage + (level * 5);
        speed = baseSpeed + (level * 2);
        defense = baseDefense + (level * 4);
        currentHealth = health;
        experience = (int) Math.pow(5, level);
        //System.out.println(level);
        //System.out.println(experience);
        if(!player){
            x = 1000;
            y = 25;
        }
        else{
            flipImage();
            x = 650;
            y = 275;
        }
    }
    
    public void copy(Fruit x){
        this.currentHealth = x.currentHealth;
        this.currentExperience = x.currentExperience;
        this.name = x.name;
        this.level = x.level;
        this.image = x.image;
        
        this.baseHealth = x.baseHealth;
        this.baseDamage = x.baseDamage;
        this.baseSpeed = x.baseSpeed;
        this.baseDefense = x.baseDefense;
        this.width = 225;
        this.height = 225;
        setStats();
        setBarHealth();
    }

    public void setBarHealth(){
        this.barHealth = 350 * (currentHealth/(double)health);
        this.experienceBar = 350 * (currentExperience / (double)experience);
    }

    public void flipImage(){
        width *= -1;
    }

    public void attack(){
        if(player){
            x += 50;
        }
        else{
            x-= 50;
        }
    }

    public String[] getInfo(){
        String[] temp = {name, String.valueOf(currentHealth), String.valueOf(experience), String.valueOf(level)};
        return temp;
    }

    public String toString(){
        return name;
    }

}
