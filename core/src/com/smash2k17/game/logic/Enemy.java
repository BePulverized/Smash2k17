package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity {

    //public Body b2body;
    private boolean touching = false;
    private double x;
    private double y;

    public Enemy(Map map, double x, double y) {
        super(map);
        this.x = x;
        this.y = y;
        setPosition((float)x , (float)y);

        //setBounds(getX(), getY(), 16 / com.smash2k17.game.logic.World.PPM, 16 / com.smash2k17.game.logic.World.PPM);
    }



    public void setTouching(boolean touching){
        this.touching = touching;
    }
    public boolean getTouching(){ return touching;}

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set((float)x/ WorldData.PPM,(float)y/WorldData.PPM );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / com.smash2k17.game.logic.WorldData.PPM);
        fdef.filter.categoryBits = com.smash2k17.game.logic.WorldData.ENEMY_BIT;
        fdef.filter.maskBits = com.smash2k17.game.logic.WorldData.GROUND_BIT | com.smash2k17.game.logic.WorldData.OBJECT_BIT | com.smash2k17.game.logic.WorldData.ITEM_BIT | com.smash2k17.game.logic.WorldData.PLAYER_BIT;

        fdef.shape = shape;
        fdef.restitution = 0.5f;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void update(float dt) {

    }



    public void lowerHitpoints(int attack) {
        if(touching){
            hitPoints -= attack;
            System.out.println(hitPoints);
        }
    }

    @Override
    public void move(KeyEvent e) {

    }

    @Override
    public void jump() {

    }

    @Override
    public void attackEnemy() {

    }

    @Override
    public void respawn() {

    }
}
