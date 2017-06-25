package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Test;
import sun.java2d.opengl.WGLSurfaceData;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

/**
 * Created by Martien on 20-May-17.
 */
public class EnemyTest {
    Enemy enemy;
    Map map;

    @Before
    public void setUp() throws Exception{
        World world = new World();
        map =  new Map(new World(), new WorldData("test"), new Account(1, "benny@mail.nl", 1));
        enemy = new Enemy(map, 0, 0);
    }

    //functie onduidelijk
    @Test
    public void defineEntityTest(){
        enemy.defineEntity();
        assertTrue(true);
    }

    @Test
    public void setTouchingTest() throws Exception {
        enemy.setTouching(true);
        assertTrue(enemy.getTouching());
    }

    @Test
    public void lowerHitpointsTest() throws Exception {
        enemy.setTouching(true);
        enemy.lowerHitpoints(10);
        assertEquals(90, enemy.getHealth());
    }

    @Test
    public void jumpTest(){
        enemy.jump();
        assertTrue(true);
    }
}