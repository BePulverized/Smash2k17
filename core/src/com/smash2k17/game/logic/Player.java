package com.smash2k17.game.logic;

import Screens.PlayScreen;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public class Player extends Entity{


    private int lives;


    public Player(World world, PlayScreen screen) {
        super(world, screen);
        defineEntity();
        this.lives = 3;

    }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ Map.PPM, 32/ Map.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius( 20/ Map.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    @Override
    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() /2);
    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void Respawn(){
        if (hitPoints <= 0 && lives > 0){
            hitPoints = 100;
        }
    }
}
