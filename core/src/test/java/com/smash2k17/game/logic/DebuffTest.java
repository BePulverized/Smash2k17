package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

/**
 * Created by Martien on 20-May-17.
 */
public class DebuffTest {
    Map map;
    Debuff debuff;

    @Before
    public void setUp() throws Exception {
        map =  new Map(new World(), new WorldData("test"), new Account(1, "benny@mail.nl", 1));
        debuff = new Debuff(map, 10, 10);
    }

    @Test
    public void useTest() throws Exception{
        Player test = new Player(map, 1);
        debuff.use(test);
        System.out.println(test.getHealth());
        assertEquals(0, test.getHealth());
    }

    // Vergadering maandag 22/5/'17
    @Test
    public void updateTest() throws Exception{
        throw new NotImplementedException();
    }
}