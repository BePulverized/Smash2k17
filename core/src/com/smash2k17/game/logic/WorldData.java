package com.smash2k17.game.logic;

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
    private int ID;

    //Players
    private ArrayList<EntityData> players;
    //Items
    private ArrayList<ItemDef> items;
    //Enemy that last died
    private EntityData dieingEnemy;
    private EntityData dieingPlayer;

    public WorldData(String name){
        this.ID = 1;
        this.name = name;
        players = new ArrayList<EntityData>();
        items = new ArrayList<ItemDef>();
    }

    public ArrayList<EntityData> getPlayers()
    {
        return players;
    }

 /**   public void changePlayerPosition(Entity entity)
    {
        for(Entity ent: players)
        {
            if(ent.name == entity.name)
            {
                ent.position = entity.position;
            }
        }
    }
**/
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

    public void addPlayer(EntityData ent) {
        players.add(ent);
    }

    public int getID() {
        return ID;
    }

    public void removePlayer(EntityData ent){players.remove(ent);}

    public void addItem(ItemDef item)
    {
        items.add(item);
    }

    public void removeItem(ItemDef item)
    {
        items.remove(item);
    }

    public ArrayList<ItemDef> getItems() {
        return items;
    }

    public EntityData getDieingEnemy()
    {
        return dieingEnemy;
    }

    public void setdieingPlayer(EntityData dieingPlayer) {
        this.dieingPlayer = dieingPlayer;
    }
}
