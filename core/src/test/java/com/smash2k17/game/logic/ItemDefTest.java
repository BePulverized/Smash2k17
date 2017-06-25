package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martien on 29-May-17.
 */
public class ItemDefTest {
    private ItemDef itemDef;

    @Before
    public void setUp(){
        itemDef = new ItemDef(Vector2.Zero, Debuff.class);
    }

    @Test
    public void testy(){
        assertTrue(itemDef.position.isZero());
    }
}