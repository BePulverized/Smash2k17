package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;
import com.smash2k17.game.logic.Database.Account;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by pieni on 03/04/2017.
 */
public class Enemy extends Entity {

    private State state;
    //public Body b2body;
    private Player touchEnemy;
    private double x;
    private double y;
    private int id;
    private boolean right;

    public Enemy(Map map, double x, double y, State state, boolean right, float dt, int id, Account activeAccount) {
        super(map, activeAccount);
        this.x = x;
        this.y = y;
        this.state = state;
        this.right = right;
        this.id = id;
        setPosition((float)x , (float)y);
        setRegion(getFrame(dt));
        //setBounds(getX(), getY(), 16 / com.smash2k17.game.logic.World.PPM, 16 / com.smash2k17.game.logic.World.PPM);
    }


    public void setTouchEnemy(Player te){
        this.touchEnemy = te;
    }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(18 / com.smash2k17.game.logic.WorldData.PPM);
        fdef.filter.categoryBits = WorldData.ENEMY_BIT;
        fdef.filter.maskBits = com.smash2k17.game.logic.WorldData.GROUND_BIT | com.smash2k17.game.logic.WorldData.OBJECT_BIT | com.smash2k17.game.logic.WorldData.ITEM_BIT | WorldData.PLAYER_BIT;
        fdef.shape = shape;
        fdef.restitution = 0.5f;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {
        b2body.setTransform((float)x, (float)y, 0);
        setPosition((float)x - getWidth()/2 , (float)y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    private TextureRegion getFrame(float dt) {

        TextureRegion region;
        switch (state) {
            case JUMPING:
                region = playerJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case ATTACK:
                region = playerAttack;
                attackEnemy();
                break;
            case FALLING:
                if(currentState != State.FALLING)
                {
                    currentState = State.FALLING;
                }
            case STANDING:
                if(currentState != State.STANDING)
                {
                    currentState = State.STANDING;
                }
            default:
                region = playerStand;
                break;
        }
        if (!right && !region.isFlipX()) {
            region.flip(true, false);
        }
        else if ( right && region.isFlipX()) {
            region.flip(true, false);
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return  region;
    }

    @Override
    public void move(KeyEvent e) {

    }

    @Override
    public void jump() {

    }

    @Override
    public void attackEnemy() {
        if(currentState != State.ATTACK)
        {
            currentState = State.ATTACK;

            if(touchEnemy != null){
                touchEnemy.lowerHitpoints(getStrength());
                System.out.println("Attack!!!");
            }
        }
    }

    @Override
    public void respawn() {

    }

    @Override
    public void Jump() {
        b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
    }

    @Override
    public boolean equals(Object obj) {
        EntityData enemy = (EntityData) obj;
        if(enemy.getID() == this.getId())
        {
            return true;
        }
        return false;
    }

    public void die()
    {

    }

    public int getId() {
        return id;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
