package com.smash2k17.game.logic;

import com.smash2k17.game.logic.RMI.IClientSignal;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by BePul on 28-5-2017.
 */
public class EntityData implements Serializable{

    private int ID;
    private double x;
    private double y;
    private IClientSignal signal;
    private int WorldID;
    private Entity.State state;
    private float delta;
    private boolean right;
    public EntityData(int ID, double x, double y, int worldID, Entity.State state, float delta, boolean right)
    {
        this.delta = delta;
        this.ID = ID;
        this.state = state;
        this.x = x;
        this.y = y;
        this.WorldID = worldID;
        this.right = right;
    }

    public EntityData(int ID, double x, double y, int worldID)
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

    public void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Entity.State getState()
    {
        return state;
    }

    public void setState(Entity.State state)
    {
        this.state = state;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
