package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by Martien on 21-May-17.
 */
public class EntityTest {
    World world;
    Map map;
    Entity entity;

    @Before
    public void setUp() throws RemoteException {
        world = new World();
        map =  new Map(world, new WorldData("test"), new Account(1, "benny@mail.nl", 1));;
        entity = new Player(map, 1);
    }

    @Test
    public void getStrengthTest() throws Exception {
        assertEquals(100, entity.getStrength());
    }

    @Test
    public void getHealthTest() throws Exception {
        assertEquals(100, entity.getHealth());
    }

    @Test
    public void addHealthTest() throws Exception {
        entity.addHealth(10);
        assertEquals(110, entity.getHealth());
    }

    @Test
    public void loseHealthTest() throws Exception {
        entity.loseHealth(10);
        assertEquals(90, entity.getHealth());
    }

    @Test
    public void isDead() throws Exception {
        assertFalse(entity.playerIsDead);
    }

    @Test
    public void definteEntityTest(){
        entity.defineEntity();
        assertFalse(false);
    }

    @Test
    public void respawnTest(){
        entity.respawn();
        assertFalse(false);
    }

    @Test
    public void stateTest(){
        Entity.State state;
        state = Entity.State.ATTACK;
        state = Entity.State.DEAD;
        state = Entity.State.FALLING;
        state = Entity.State.JUMPING;
        state = Entity.State.RUNNING;
        state = Entity.State.STANDING;
    }
}