package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Entity;
import com.smash2k17.game.logic.PowerUp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by BePul on 15-5-2017.
 */
public interface IWorld extends Remote
{
    List<Entity> getAllPlayers() throws RemoteException;
    void deletePlayer() throws RemoteException;
    List<PowerUp> getAllBuffs() throws RemoteException;
    void deleteBuff() throws RemoteException;
    void addPlayer() throws RemoteException;
}
