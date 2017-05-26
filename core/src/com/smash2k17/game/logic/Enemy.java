package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.event.KeyEvent;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity {

    //public Body b2body;
    private boolean touching = false;

    public Enemy(Map map) {
        super(map);
        //setPosition(250 / com.smash2k17.game.logic.World.PPM, 60/ com.smash2k17.game.logic.World.PPM);
        //setBounds(getX(), getY(), 16 / com.smash2k17.game.logic.World.PPM, 16 / com.smash2k17.game.logic.World.PPM);
    }

    public void setTouching(boolean touching){
        this.touching = touching;
    }

    public boolean getTouching(){ return touching; }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(300 / com.smash2k17.game.logic.World.PPM, 200/ com.smash2k17.game.logic.World.PPM);
        setPosition(bdef.position.x, bdef.position.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / com.smash2k17.game.logic.World.PPM);
        fdef.filter.categoryBits = com.smash2k17.game.logic.World.ENEMY_BIT;
        fdef.filter.maskBits = com.smash2k17.game.logic.World.GROUND_BIT | com.smash2k17.game.logic.World.OBJECT_BIT | com.smash2k17.game.logic.World.ITEM_BIT | com.smash2k17.game.logic.World.PLAYER_BIT;


        fdef.shape = shape;
        fdef.restitution = 0.5f;
        b2body.createFixture(fdef).setUserData(this);

    }

    public void update() {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //setRegion(getFrame(dt));
    }

    /*private TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = playerJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case ATTACK:
                region = playerAttack;
                break;
            case FALLING:
            case STANDING:
            default:
                region = playerStand;
                break;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return  region;
    }*/

    /*private State getState() {
        if(b2body.getPosition().y < 0 || getHealth() <= 0)
            return State.DEAD;
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            return State.ATTACK;
        else
            return State.STANDING;

    }*/

    public void lowerHitpoints(int s) {
        hitPoints -= s;
        System.out.println(hitPoints);

    }

    @Override
    public void move(KeyEvent e) {

    }

    @Override
    public void jump() {

    }
}
