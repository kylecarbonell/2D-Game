package Fruit;

import java.awt.image.*;

public class Fruit implements Cloneable{
    public String name;

    public int level = 1;

    public BufferedImage image;
    public int currentHealth;

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

    public Fruit(){

    }

    public Fruit(Fruit fruit){
        copy(fruit);
    }

    public void setStats(){
        health = baseHealth + (level * 3);
        damage = baseDamage + (level * 5);
        speed = baseSpeed + (level * 2);
        defense = baseDefense + (level * 4);
        currentHealth = health;
    }
    
    public void copy(Fruit x){
        this.currentHealth = x.currentHealth;
        this.name = x.name;
        this.level = x.level;
        this.image = x.image;
        
        this.baseHealth = x.baseHealth;
        System.out.println(this.name + this.baseHealth);
        this.baseDamage = x.baseDamage;
        this.baseSpeed = x.baseSpeed;
        this.baseDefense = x.baseDefense;
        setStats();
    }

}
