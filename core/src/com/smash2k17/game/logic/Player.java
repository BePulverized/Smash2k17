package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.smash2k17.game.logic.Menus.LoginScreen;

import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

/**
 * Created by BePul on 27-3-2017.
 */
public class Player extends Entity {


    private int lives;
    private EntityData data;
    private Enemy touchEnemy;

    public void setTouchEnemy(Enemy te){ this.touchEnemy = te; }

    public Player(Map map, int id) {
        super(map);
        this.lives = 3;
        data = new EntityData(id, 200, 300, 1);
    }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(300 / com.smash2k17.game.logic.WorldData.PPM, 200/ com.smash2k17.game.logic.WorldData.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(18 / com.smash2k17.game.logic.WorldData.PPM);
        fdef.filter.categoryBits = WorldData.PLAYER_BIT;
        fdef.filter.maskBits = WorldData.GROUND_BIT | WorldData.OBJECT_BIT | WorldData.ITEM_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
        data = new EntityData(data.getID(), (double) b2body.getPosition().x, (double) b2body.getPosition().y, 1, currentState, dt, runningRight);
        try {
            LoginScreen.conn.sendPlayerData(data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private TextureRegion getFrame(float dt) {
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
    }



    private State getState() {
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

    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void move(KeyEvent e) {

    }

    public void handleInput(float dt)
    {
        if(currentState != State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2)
                b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2)
                b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                attack();
        }

    }

    @Override
    public void jump()
    {
        if(currentState != State.JUMPING)
        {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

    @Override
    public void attackEnemy() {

    }

    @Override
    public void respawn() {

    }

    public boolean attack()
    {
        if(currentState != State.ATTACK)
        {
            currentState = State.ATTACK;
            if(touchEnemy != null){
                touchEnemy.lowerHitpoints(getStrength());
            }
            return true;
        }
        return false;
    }

    @Override
    public void Respawn(){
        if (hitPoints <= 0 && lives > 0){
            hitPoints = 100;
        }
    }

    @Override
    public void Jump() {

    }


    public EntityData getData() {
        return data;
    }
}
