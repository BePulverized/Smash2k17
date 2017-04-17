package com.smash2k17.game.logic;

import com.badlogic.gdx.physics.box2d.*;
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
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / com.smash2k17.game.logic.World.PPM, 200/ com.smash2k17.game.logic.World.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / com.smash2k17.game.logic.World.PPM);
        fdef.filter.categoryBits = com.smash2k17.game.logic.World.ENEMY_BIT;
        fdef.filter.maskBits = com.smash2k17.game.logic.World.GROUND_BIT | com.smash2k17.game.logic.World.OBJECT_BIT | com.smash2k17.game.logic.World.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

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
