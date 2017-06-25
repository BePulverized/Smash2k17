package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martien on 29-May-17.
 */
public class PowerUpTest {
    private World world;
    private Map map;
    private PowerUp powerUp;

    @Before
    public void setUp() throws Exception {
        world = new World();
        map =  new Map(new World(), new WorldData("test"), new Account(1, "benny@mail.nl", 1));
        powerUp = new PowerUp(map,0,0);
    }

    @Test
    public void use() throws Exception {
        Player p1 = new Player(map,1 );
        powerUp.use(p1);
        assertEquals(110, p1.getHealth());
    }

    @Test
    public void update() throws Exception {
        powerUp.update(1);
        assertEquals(1,1);
    }

}