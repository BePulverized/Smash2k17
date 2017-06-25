package com.smash2k17.game.logic.Database;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class StoreContextTest {
    StoreContext scTest = new StoreContext();
    @Test
    public void getProducts() throws Exception {
        try {
            scTest.getProducts();
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

    @Test
    public void koopProduct() throws Exception {
        try {
        scTest.koopProduct(1,2);
        assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

    @Test
    public void getPrijs() throws Exception {
        assertNotNull(scTest.getPrijs(1));
    }

}