package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by BePul on 28-5-2017.
 */
public interface IServer extends Remote {
    ArrayList<WorldData> getAvWorlds() throws RemoteException;
    ArrayList<EntityData> getPlayers() throws RemoteException;
    EntityData getPlayer(int id) throws RemoteException;
    void sendPlayerData(EntityData ent, IClientSignal signal) throws RemoteException;
    void newPlayer(EntityData ent, IClientSignal signal) throws RemoteException;
    void playerLeave(EntityData ent, IClientSignal signal) throws RemoteException;
    ArrayList<ItemDef> getItems(EntityData ent) throws RemoteException;
    void destroyItem(EntityData ent, float x, float y) throws RemoteException;
}
