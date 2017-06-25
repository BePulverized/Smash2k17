package com.smash2k17.game.logic.RMI;

import com.badlogic.gdx.utils.TimeUtils;
import com.smash2k17.game.logic.*;

import java.awt.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by BePul on 28-5-2017.
 */
public class ServerHost implements IServer{

    static ArrayList<WorldData> worlds;
    private static long lastBuffDropTime;
    private static long lastDebuffDropTime;

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
                entity.setPosition(ent.getX(), ent.getY());
                entity.setState(ent.getState());
                System.out.println(ent.getState().toString());
                entity.setDelta(ent.getDelta());
                entity.setRight(ent.getRight());
            }
        }

        //signal
        for(EntityData lobby: playerWorld.getPlayers())
        {
            if(lobby.getID() != ent.getID()) {
                lobby.getSignal().signal(playerWorld, "playermovement");
            }
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
        System.out.println("Player" + ent.getID() + "Added to server");

        //signal
        for(EntityData lobby: playerWorld.getPlayers())
        {
            if(lobby.getID() != ent.getID()) {
                lobby.getSignal().signal(playerWorld, "playerjoin");
            }
        }
    }

    @Override
    public void playerLeave(EntityData ent, IClientSignal signal) throws RemoteException {
        ent.setSignal(signal);
        WorldData playerWorld = null;
        for(WorldData world: worlds)
        {
            if(world.getID() == ent.getWorldID())
            {
                playerWorld = world;
            }
        }
        playerWorld.removePlayer(ent);
        System.out.println("Player" + ent.getID() + "removed from server");

        for(EntityData lobby: playerWorld.getPlayers())
        {
            if(lobby.getID() != ent.getID()) {
                lobby.getSignal().signal(playerWorld, "playerjoin");
            }
        }
    }

    // Item spawning
    public static void spawnItem(ItemDef idef)
    {
        for(WorldData worldData: worlds)
        {
            worldData.addItem(idef);
        }
        lastBuffDropTime = TimeUtils.millis();
        lastDebuffDropTime = TimeUtils.millis();
    }

    public static void handleItemSpawns(ArrayList<ItemDef> items)
    {
        Random rnddebuff = new Random();
        if(TimeUtils.millis() - lastDebuffDropTime > 5000 && items.size() < 2)
        {
            int randomx = rnddebuff.nextInt(6 + 1 - 1) + 1;
            int randomy = rnddebuff.nextInt(3 + 1 -1 ) + 1;
            spawnItem(new ItemDef(new Point(randomx, randomy), "Debuff"));
        }
        Random rndbuff = new Random();
        if(TimeUtils.millis() - lastBuffDropTime > 4000 && items.size() < 2)
        {
            int randomx = rndbuff.nextInt(6 + 1 -1) + 1;
            int randomy = rndbuff.nextInt(3 + 1 -1) + 1;
            spawnItem(new ItemDef(new Point(randomx, randomy), "Powerup"));
        }
    }

    public ArrayList<ItemDef> getItems(EntityData ent)
    {
        WorldData playerWorld = null;
        for (WorldData world: worlds)
        {
            if(world.getID() == ent.getWorldID())
            {
                playerWorld = world;
            }
        }

        return playerWorld.getItems();
    }

    public void destroyItem(EntityData ent, float x, float y)
    {
        WorldData playerWorld = null;
        for (WorldData world: worlds)
        {
            if(world.getID() == ent.getWorldID())
            {
                playerWorld = world;
            }
        }
        Iterator<ItemDef> iter = playerWorld.getItems().iterator();
        while(iter.hasNext()){
            ItemDef item = iter.next();
            if(item.getPosition().x == x && item.getPosition().y == y)
            {
                iter.remove();
            }
        }
        for(ItemDef item: playerWorld.getItems())
        {
            if(item.getPosition().x == x && item.getPosition().y == y)
            {
                playerWorld.removeItem(item);
            }
        }
    }


    public static void main(String[] args)
    {
        ServerHost host = new ServerHost();
        lastBuffDropTime = 0;
        lastDebuffDropTime = 0;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(WorldData worldData: worlds)
                {
                    handleItemSpawns(worldData.getItems());
                    System.out.println(worldData.toString() + " items: " + worldData.getItems().size());
                }
            }
        },0, 5000);


    }
}
