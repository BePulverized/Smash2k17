package com.smash2k17.game.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Map {

    private String name;
    private int gridWidth;
    private int gridHeight;
    private GameMode gameMode;
    private ArrayList<Point> deathZones;
    private ArrayList<ItemDrop> itemDrops;
    private ArrayList<Entity> players;

    public Map(String name, int gridWidth, int gridHeight, GameMode gamemode)
    {
        this.name = name;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
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



}
