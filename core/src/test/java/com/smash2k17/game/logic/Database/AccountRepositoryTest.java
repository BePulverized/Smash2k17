package com.smash2k17.game.logic.Database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class AccountRepositoryTest {

    AccountRepository arTest = new AccountRepository(new AccountContext());

    @Test
    public void logIn() throws Exception {
        assertNotNull(arTest.logIn("admin", "admin"));
    }

    @Test
    public void addUser() throws Exception {
        try{
            arTest.addUser("test2", "test2", 9999);
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

}