package com.smash2k17.game.logic;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public class Player extends Entity{

    private int lives;

    public Player(String name, int hitPoints, Point position, int strength, int armor, int lives) {
        super(name, hitPoints, position, strength, armor);
        this.lives = 3;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
