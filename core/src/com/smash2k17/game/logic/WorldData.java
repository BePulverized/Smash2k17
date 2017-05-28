package com.smash2k17.game.logic;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BePul on 26-5-2017.
 */
public class WorldData implements Serializable{

    public static final float PPM = 100;
    public static final short OBJECT_BIT = 32;
    public static final short ITEM_BIT = 256;
    public static final short PLAYER_BIT = 2;
    public static final short ENEMY_BIT = 2;
    public static final short GROUND_BIT = 1;
    private Date dateCreated;
    private Date dateEnded;
    private Chat chat;
    private String name;

    //Players
    private ArrayList<Entity> players;

    public WorldData(String name){

        this.name = name;
        players = new ArrayList<Entity>();

        players.add(new Enemy(new Point(300,75)));
        players.add(new Enemy(new Point(250, 75)));
    }

    public ArrayList<Entity> getPlayers()
    {
        return players;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public Date getDateEnded()
    {
        return dateEnded;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
