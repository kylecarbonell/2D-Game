package Fruit;

import java.awt.image.*;

public class Fruit implements Cloneable{
    public String name;

    public int level = 1;

    public BufferedImage image;
    public int width;
    public int height;
    public int currentHealth;

    public boolean player = false;

    public int x;
    public int y;

    public int baseHealth;
    public int baseDamage;
    public int baseSpeed;
    public int baseDefense;

    public int ivHealth;
    public int ivDamage;
    public int ivSpeed;
    public int ivDefense;

    public int health;
    public int damage;
    public int speed;
    public int defense;

    public int attack;

    public double barHealth;
    public int maxHealth;

    public Fruit(){
        
    }

    public Fruit(Fruit fruit){
        copy(fruit);
    }

    public Fruit(Fruit fruit, boolean player){
        this.player = player;
        copy(fruit);
    }

    public void setStats(){
        health = baseHealth + (level * 3);
        damage = baseDamage + (level * 5);
        speed = baseSpeed + (level * 2);
        defense = baseDefense + (level * 4);
        currentHealth = health;
        if(!player){
            x = 1000;
            y = 25;
        }
        else{
            x = 650;
            y = 275;
            flipImage();
        }
    }
    
    public void copy(Fruit x){
        this.currentHealth = x.currentHealth;
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
        this.barHealth = 350 * (currentHealth / (double)health);
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

    public String toString(){
        return name;
    }

}
