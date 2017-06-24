package com.smash2k17.game.logic.Database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class AccountTest {
    Account aTest = new Account(1, "test", 9999);
    @Test
    public void getEmail() throws Exception {
        assertNotNull(aTest.getEmail());
    }

    @Test
    public void getBalance() throws Exception {
        assertNotNull(aTest.getBalance());
    }

    @Test
    public void setBalance() throws Exception {
        try{
            aTest.setBalance(999);
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

    @Test
    public void getAvWorlds() throws Exception {
        assertNotNull(aTest.getAvWorlds());
    }

    @Test
    public void getId() throws Exception {
        assertNotNull(aTest.getId());
    }

}