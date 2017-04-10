package com.smash2k17.game.logic;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.smash2k17.game.logic.interfaces.IPlayable;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by BePul on 27-3-2017.
 */
public abstract class Entity extends Sprite implements IPlayable {

        public State currentState;;
    public State previousState;
    public World world;
    public Body b2body;
    public TextureRegion playerStand;
    public com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> playerRun;
    public com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> playerJump;
    public float stateTimer;
    public boolean runningRight;
    int hitPoints;
    private String name;
    private Point position;
    private int strength;
    private int armor;
    private Map map;
    public Entity(World world, PlayScreen screen)
    {
        super(screen.getTextureAtlas().findRegion("frame-1"));
        com.badlogic.gdx.utils.Array<TextureRegion> frames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        for(int i = 1; i < 2; i++)
        {
            frames.add(new TextureRegion(getTexture(), i * 500,  450));
            playerRun = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(0.1f, frames);
            frames.clear();
        }

        for(int i = 2; i < 6; i++)
        {
            frames.add(new TextureRegion(getTexture(), 500, 450));
            playerJump = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(0.1f, frames);

        }

        playerStand = new TextureRegion(getTexture(),0, 0, 500,450 );
        setBounds(0,30, 40/ Map.PPM, 40/ Map.PPM);
        setRegion(playerStand);
        this.world = world;
        defineEntity();
        this.name = name;
        this.hitPoints = 100;
        this.position = position;
        this.strength = 100;
        this.armor = 0;
        this.map = map;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;


    }

    public abstract void defineEntity();

    public abstract void update(float dt);

    public void Move(KeyEvent e){
        if(KeyEvent.getKeyText(e.getKeyCode()) == "a"){
            //als position groter is als het einde van de map
            if (position.x > 0){
                position.x --;
            }
        }
        if(KeyEvent.getKeyText(e.getKeyCode()) == "d"){
            //als position kleiner is als het einde van de map
            if(position.x < map.getGridWidth()){
                position.x ++;
            }
        }
    }

    public void Jump(){
        position.y += 20;
    }

    public void Attack(){
        for (Entity e : map.getEntitys()){
            if(position.x >= e.position.x && (e.position.x + 20) <= e.position.x && e != this){
                Enemy en = (Enemy)e;
                en.lowerHitpoints(strength);
            }
        }
    }

    public void Respawn(){}

public enum State{ FALLING, JUMPING, STANDING, RUNNING}



}
