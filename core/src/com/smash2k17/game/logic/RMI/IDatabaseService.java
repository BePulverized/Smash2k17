package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Martien on 25-Jun-17.
 */
public interface IDatabaseService extends Remote{
    public Account login(String username, String password)throws RemoteException;
}
