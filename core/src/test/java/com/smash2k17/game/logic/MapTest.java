package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Martien on 21-May-17.
 */
public class MapTest {
    World world;
    Map map;

    @Before
    public void setUp() throws RemoteException {
        world = new World();
        map =  new Map(world, new WorldData("test"), new Account(1, "benny@mail.nl", 1));
    }

    @Test
    public void spawnItemTest() throws Exception{
        ItemDef newItem = new ItemDef(new Vector2(10,10), Debuff.class);
        map.spawnItem(newItem);
        assertTrue(map.itemsToSpawn.contains(newItem));
    }

    @Test
    public void handleSpawningItemsTest() throws Exception {
        map.handleSpawningItems();
        Thread.sleep(6000);
        map.handleSpawningItems();
        assertTrue(map.items.get(0) != null);
    }

    @Test
    public void getNameTest(){
        assertTrue(map.getName() == "map");
    }

    @Test
    public void getGameModeTest(){
        assertTrue(map.getGameMode() == GameMode.TDM);
    }

    @Test
    public void GetDeathzonesTest(){
        map.getDeathzones();
        assertTrue(true);
    }

    @Test
    public void dropItemTest(){
        assertFalse(map.dropItem());
    }

    @Test
    public void pickUpItemTest(){
        ItemDrop newItem = new Debuff(map, 10, 10);
        map.items.add(newItem);
        map.pickUpItem(newItem);

        assertTrue(map.items.get(0) == null);
    }


}