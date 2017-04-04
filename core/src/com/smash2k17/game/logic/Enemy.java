package com.smash2k17.game.logic;

import Screens.PlayScreen;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity {

    private World world;
    public Body b2body;

    public Enemy(World world, PlayScreen screen) {
        super(world, screen);
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
}
