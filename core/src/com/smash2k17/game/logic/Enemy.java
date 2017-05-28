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
    private int x;
    private int y;

    public Enemy(Map map, int x, int y) {
        super(map);
        this.x = x;
        this.y = y;
        setPosition(x / com.smash2k17.game.logic.WorldData.PPM, y/ com.smash2k17.game.logic.WorldData.PPM);

        //setBounds(getX(), getY(), 16 / com.smash2k17.game.logic.World.PPM, 16 / com.smash2k17.game.logic.World.PPM);
    }



    public void setTouching(boolean touching){
        this.touching = touching;
    }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/ WorldData.PPM,y/WorldData.PPM );
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
    public void Move(KeyEvent e) {

    }

    @Override
    public void Jump() {

    }
}
