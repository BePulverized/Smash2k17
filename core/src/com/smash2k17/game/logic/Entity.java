package com.smash2k17.game.logic;

import com.smash2k17.game.logic.interfaces.IPlayable;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class Entity implements IPlayable{

    private String name;
    int hitPoints;
    private Point position;
    private int strength;
    private int armor;
    private Map map;

    public Entity(String name, int hitPoints, Point position, int strength, int armor, Map map)
    {
        this.name = name;
        this.hitPoints = 100;
        this.position = position;
        this.strength = 100;
        this.armor = 0;
        this.map = map;
    }
    public void Move(KeyEvent e){
        if(KeyEvent.getKeyText(e.getKeyCode()) == "a"){
            //als position groter is als het einde van de map
            if (position.x > 0){
                position.x --;
            }
        }
        if(KeyEvent.getKeyText(e.getKeyCode()) == "d"){
            //als position kleiner is als het einde van de map
            if(position.x < map.getGridWidth()){
                position.x ++;
            }
        }
    }

    public void Jump(){
        position.y += 20;
    }

    public void Attack(){
        for (Entity e : map.getEntitys()){
            if(position.x >= e.position.x && (e.position.x + 20) <= e.position.x && e != this){
                Enemy en = (Enemy)e;
                en.lowerHitpoints(strength);
            }
        }
    }

    public void Respawn(){}


}
