package com.smash2k17.game.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by BePul on 27-3-2017.
 */
public class Lobby {

    private Store store;
    private Account activeAccount;

    public Lobby()
    {
        store = new Store();
    }

    public Account login(String userName, String Password)
    {
        throw new NotImplementedException();
    }

    public boolean register(String eMail, String userName, String password)
    {
        throw new NotImplementedException();
    }

    public boolean createWorld()
    {
        throw new NotImplementedException();
    }

    public Account getActiveAccount() {
        return activeAccount;
    }
}