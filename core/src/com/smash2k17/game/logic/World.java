package com.smash2k17.game.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

/**
 * Created by BePul on 27-3-2017.
 */
public class World {

    private Date dateCreated;
    private Date dateEnded;
    private Chat chat;

    public World()
    {

    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public Date getDateEnded()
    {
        return dateEnded;
    }

    public void endWorld()
    {
        throw new NotImplementedException();
    }

    public void displayChat()
    {
        throw new NotImplementedException();
    }

    public void removePlayer()
    {

    }
}
