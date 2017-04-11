package com.smash2k17.game.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.event.KeyEvent;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity {

    public Body b2body;
    private World world;
    private boolean touching = false;

    public Enemy(Map map) {
        super(map);
    }

    public void setTouching(boolean touching){
        this.touching = touching;
    }

    @Override
    public void defineEntity() {

    }

    @Override
    public void update(float dt) {

    }

    public void lowerHitpoints(int attack) {
        if(touching){
            hitPoints -= attack;
        }
    }

    @Override
    public void Move(KeyEvent e) {

    }
}
