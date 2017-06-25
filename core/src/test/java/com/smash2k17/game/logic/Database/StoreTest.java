package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.Skins;
import com.smash2k17.game.logic.StoreItem;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class StoreTest {
    Store sTest = new Store();

    @Test
    public void getItems() throws Exception {
        assertNotNull(sTest.getItems());
    }

    @Test
    public void addItems() throws Exception {
        try {
            sTest.addItems(new Skins(99,"test"));
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

}