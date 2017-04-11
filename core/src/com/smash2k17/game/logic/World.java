package com.smash2k17.game.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

/**
 * Created by BePul on 27-3-2017.
 */
public class World extends Game{

    public static final float PPM = 100;
    public SpriteBatch batch;
    private Date dateCreated;
    private Date dateEnded;
    private Chat chat;
    private int gridWidth;
    private int gridHeight;

    public World()
    {
        this.gridWidth = 400;
        this.gridHeight = 208;
    }

    public int getGridWidth()
    {
        return  gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public float getPPM() {
        return PPM;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public Date getDateEnded()
    {
        return dateEnded;
    }

    public void endWorld()
    {
        throw new NotImplementedException();
    }

    public void displayChat()
    {
        throw new NotImplementedException();
    }

    public void removePlayer()
    {

    }

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        setScreen(new Map(this));
    }

    @Override
    public void render()
    {
        super.render();
    }
}
