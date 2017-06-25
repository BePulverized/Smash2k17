package com.smash2k17.game.logic.Database;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Stef on 24-6-2017.
 */
public class AccountContextTest {

    AccountContext acTest = new AccountContext();

    @Test
    public void encryptTest() throws Exception {
        assertEquals("encryptie test",acTest.encrypt("admin"),"8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
    }

    @Test
    public void logIn() throws Exception {
        assertNotNull(acTest.logIn("admin", "admin"));
    }

    @Test
    public void addUser() throws Exception {
        try{
            acTest.addUser("test1", "test1", 9999);
            assertTrue(true);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }

}