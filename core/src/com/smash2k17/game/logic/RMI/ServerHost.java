package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Entity;
import com.smash2k17.game.logic.EntityData;
import com.smash2k17.game.logic.Lobby;
import com.smash2k17.game.logic.WorldData;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by BePul on 28-5-2017.
 */
public class ServerHost implements IServer{

    ArrayList<WorldData> worlds;

    public ServerHost()
    {
        try{
            Registry rmiRegistry = LocateRegistry.createRegistry(1099);
            IServer rmiServer = (IServer) UnicastRemoteObject.exportObject(this,1099);
            rmiRegistry.bind("RmiService", rmiServer);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        worlds = new ArrayList<WorldData>();
        worlds.add(new WorldData("test"));
    }

    @Override
    public ArrayList<WorldData> getAvWorlds() throws RemoteException {
        return worlds;
    }

    @Override
    public ArrayList<EntityData> getPlayers() throws RemoteException {
        return null;
    }

    @Override
    public EntityData getPlayer(int id) throws RemoteException {
        return null;
    }

    @Override
    public void sendPlayerData(EntityData ent, IClientSignal signal) throws RemoteException {
        //entity in de list die gelijk is aan ent
        ent.setSignal(signal);
        WorldData playerWorld = null;
        for (WorldData world: worlds)
        {
            if(world.getID() == ent.getWorldID())
            {
                playerWorld = world;
            }
        }
        //Verander player in list
        for(EntityData entity: playerWorld.getPlayers())
        {
            if(entity.getID() == ent.getID())
            {
                entity.setPosition(ent.getPosition().getX(), ent.getPosition().getY());
            }
        }

        //signal
        for(EntityData lobby: playerWorld.getPlayers())
        {
            lobby.getSignal().signal(playerWorld, "playermovement");
        }
    }

    @Override
    public void newPlayer(EntityData ent, IClientSignal signal) throws RemoteException {
        ent.setSignal(signal);
        WorldData playerWorld = null;
        for (WorldData world: worlds)
        {
            if(world.getID() == ent.getWorldID())
            {
                playerWorld = world;
            }
        }
        //Voeg player toe in list
        playerWorld.addPlayer(ent);

        //signal
        for(EntityData lobby: playerWorld.getPlayers())
        {
            lobby.getSignal().signal(playerWorld, "playerjoin");
        }
    }


    public static void main(String[] args)
    {
        ServerHost host = new ServerHost();


    }
}
