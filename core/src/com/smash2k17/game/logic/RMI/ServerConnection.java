package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Entity;
import com.smash2k17.game.logic.EntityData;
import com.smash2k17.game.logic.WorldData;


import java.beans.PropertyChangeEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by BePul on 28-5-2017.
 */
public class ServerConnection extends UnicastRemoteObject implements IClientSignal {

    private ArrayList<WorldData> avWorlds;
    private IServer remoteService;
    private WorldData playerWorld;

    public ServerConnection() throws RemoteException {
        try{
            remoteService = (IServer) Naming.lookup("//localhost:1099/RmiService");
        } catch (NotBoundException e) {

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<WorldData> getAvWorlds(){ return avWorlds;}

    public void sendPlayerData(EntityData ent) throws RemoteException {
        remoteService.sendPlayerData(ent, this);
    }

    public void newPlayer(EntityData ent, int id) throws RemoteException{
        try {
            remoteService.newPlayer(ent, this);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // getworlddata
    public WorldData getPlayerWorld()
    {
        return playerWorld;
    }

    @Override
    public void signal(Object observable, Object updateMsg) throws RemoteException {
        switch((String)updateMsg)
        {
            case "playerjoin":
                playerWorld = (WorldData) observable;
            break;
            case"playermovement":
                playerWorld = (WorldData) observable;

        }
    }
}
