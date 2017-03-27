package com.smash2k17.game.logic;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class Entity {

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
}
