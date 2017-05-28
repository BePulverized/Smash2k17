package com.smash2k17.game.logic;

import com.smash2k17.game.logic.RMI.IClientSignal;
import org.lwjgl.util.Point;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by BePul on 28-5-2017.
 */
public class EntityData implements Serializable{

    private int ID;
    private int x;
    private int y;
    private IClientSignal signal;
    private int WorldID;
    public EntityData(int ID, int x, int y, int worldID)
    {
        this.ID = ID;

        this.x = x;
        this.y = y;
        this.WorldID = worldID;
    }

    public IClientSignal getSignal() {
        return signal;
    }

    public void setSignal(IClientSignal signal) {
        this.signal = signal;
    }

    public int getWorldID() {
        return WorldID;
    }

    public int getID() {
        return ID;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point getPosition()
    {
        return new Point(this.x, this.y);
    }
}
