package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by Martien on 21-May-17.
 */
public class ItemDropTest {
    World world;
    Map map;
    ItemDrop itemDrop;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() throws RemoteException {
        world = new  World();
        map =  new Map(new World(), new WorldData("test"), new Account(1, "benny@mail.nl", 1));
        itemDrop = new Debuff(map, 10, 10);
    }

    @Test
    public void defineItemTest() throws Exception {
        itemDrop.defineItem();
        assertTrue(true);
    }

    @Test
    public void updateTest() throws Exception {
        itemDrop.destroy();
        itemDrop.update(1);

        assertTrue(itemDrop.destroyed);
    }

    @Test
    public void destroyTest() throws Exception {
        itemDrop.destroy();
        assertTrue(itemDrop.toDestroy);
    }

    @Test
    public void spawnTest() throws Exception {
        thrown.expect(NotImplementedException.class);
        itemDrop.Spawn();
    }

    @Test
    public void useTest(){
        Player test = new Player(map,1 );
        itemDrop.use(test);
        assertEquals(0, test.getHealth());
    }
}