package com.smash2k17.game.logic;

import java.awt.*;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity{
    public Enemy(String name, int hitPoints, Point position, int strength, int armor, Map map) {
        super(name, hitPoints, position, strength, armor, map);
    }

    public void lowerHitpoints(int attack){
        hitPoints -= attack;
    }
}
