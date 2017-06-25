package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import com.sun.media.jfxmedia.events.PlayerEvent;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Martien on 22-May-17.
 */
public class PlayerTest {
    World world;
    Map map;
    Player player;


    @Before
    public void setUp() throws Exception {
        world = new World();
        map =  new Map(new World(), new WorldData("test"), new Account(1, "benny@mail.nl", 1));
        player = new Player(map, 1);
    }


}