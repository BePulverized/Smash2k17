package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.*;
import com.smash2k17.game.logic.Database.Account;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by BePul on 28-5-2017.
 */
public class ServerConnection extends UnicastRemoteObject implements IClientSignal {

    private ArrayList<WorldData> avWorlds;
    private IServer remoteService;
    private IDatabaseService databaseService;
    private WorldData playerWorld;
    private Enemy lastDestroyedEnemy;
    public ServerConnection() throws RemoteException {
        try{
            remoteService = (IServer) Naming.lookup("//localhost:1099/RmiService");
            databaseService = (IDatabaseService) Naming.lookup("//localhost:1100/databaseService");
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

    public void playerLeave(EntityData ent) throws RemoteException{
        try{
            remoteService.playerLeave(ent, this);
        }
        catch (Exception ex)
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
            break;
            case"playerdied":
                playerWorld = (WorldData) observable;
            break;

        }
    }

    public Account login(String email, String password) throws RemoteException {
        return databaseService.login(email, password);
    }

    public ArrayList<ItemDef> getItems(EntityData ent) throws RemoteException {
        return remoteService.getItems(ent);
    }

    public void destroyItem(EntityData ent, float x, float y) throws RemoteException {
        remoteService.destroyItem(ent, x, y);
    }


}
