package com.smash2k17.game.logic.Database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class StoreRepositoryTest {

    StoreRepository srTest = new StoreRepository(new StoreContext());
    @Test
    public void getProducts() throws Exception {
        try {
            srTest.getProducts();
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
            srTest.koopProduct(1,2);
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

}