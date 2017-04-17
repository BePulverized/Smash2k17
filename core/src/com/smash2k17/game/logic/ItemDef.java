package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by BePulverized on 11-4-2017.
 */
public class ItemDef {
    public Vector2 position;
    public Class<?> type;

    public ItemDef(Vector2 position, Class<?> type)
    {
        this.position = position;
        this.type = type;
    }
}
