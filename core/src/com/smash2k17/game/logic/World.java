package com.smash2k17.game.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.smash2k17.game.logic.Menus.LoginScreen;
import com.smash2k17.game.logic.RMI.ServerConnection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by BePul on 27-3-2017.
 */
public class World extends Game {

    public SpriteBatch batch;
    private int gridWidth;
    private int gridHeight;

    public World() throws RemoteException {

        gridHeight = 350;
        gridWidth = 400;
    }

    public int getGridWidth()
    {
        return  gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
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

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        try {
            setScreen(new LoginScreen(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render()
    {
        super.render();
    }
}
