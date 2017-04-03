package com.smash2k17.game.logic;

import Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Map extends Game{

    public SpriteBatch batch;
    private String name;
    private int gridWidth;
    private int gridHeight;
    private GameMode gameMode;
    private ArrayList<Point> deathZones;
    private ArrayList<ItemDrop> itemDrops;
    private ArrayList<Entity> players;
    private Texture img;


    public Map()
    {
        this.name = name;
        this.gridWidth = 400;
        this.gridHeight = 208;
        this.gameMode = GameMode.TDM;
        players = new ArrayList<Entity>();
    }

    public String getName()
    {
        return name;
    }

    public int getGridWidth()
    {
        return  gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public GameMode getGameMode()
    {
        return gameMode;
    }

    public ArrayList<Point> getDeathzones()
    {
        return  deathZones;
    }

    public boolean dropItem()
    {
        return false;
    }

    public void end()
    {
        throw new NotImplementedException();
    }

    public ArrayList<Entity> getEntitys(){return players;}


    @Override
    public void create()
    {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));

    }
    @Override
    public void render()
    {
        super.render();
    }
}
