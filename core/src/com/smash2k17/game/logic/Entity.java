package com.smash2k17.game.logic;

import com.smash2k17.game.logic.interfaces.IPlayable;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class Entity implements IPlayable{

    private String name;
    private int hitPoints;
    private Point position;
    private int strength;
    private int armor;

    public Entity(String name, int hitPoints, Point position, int strength, int armor)
    {
        this.name = name;
        this.hitPoints = 100;
        this.position = position;
        this.strength = 100;
        this.armor = 0;
    }

    public void Move(){

    }

    public void Jump(){

    }

    public void Attack(){

    }

    public void Respawn(){
        if(hitPoints <= 0){
            hitPoints = 100;
        }
    }
}
