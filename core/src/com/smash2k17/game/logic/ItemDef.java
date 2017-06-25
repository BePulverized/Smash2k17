package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by BePulverized on 11-4-2017.
 */
public class ItemDef implements Serializable{
    public Point position;
    public String type;

    public ItemDef(Point position, String type)
    {
        this.position = position;
        this.type = type;
    }

    public Point getPosition()
    {
        return position;
    }
}
