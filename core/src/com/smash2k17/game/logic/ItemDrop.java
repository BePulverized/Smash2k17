package com.smash2k17.game.logic;

import com.smash2k17.game.logic.interfaces.IPickable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public class ItemDrop implements IPickable {

    private Point position;
    private String name;
    private Effect effect;

    public ItemDrop(Point position, String name, Effect effect)
    {
        this.position = position;
        this.name = name;
        this.effect = effect;
    }

    @Override
    public void Spawn() {
        throw new NotImplementedException();
    }
}
