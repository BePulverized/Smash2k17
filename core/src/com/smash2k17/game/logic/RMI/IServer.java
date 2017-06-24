package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Entity;
import com.smash2k17.game.logic.EntityData;
import com.smash2k17.game.logic.World;
import com.smash2k17.game.logic.WorldData;

import javax.swing.*;
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
}
