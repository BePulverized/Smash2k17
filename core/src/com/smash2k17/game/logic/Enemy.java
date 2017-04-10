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

    public Enemy(Map map) {
        super(map);
    }

    @Override
    public void defineEntity() {

    }

    @Override
    public void update(float dt) {

    }

    public void lowerHitpoints(int attack) {
        hitPoints -= attack;
    }

    @Override
    public void Move(KeyEvent e) {

    }
}
