package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Martien on 25-Jun-17.
 */
public interface IDatabaseService extends Remote{
    Account login(String username, String password)throws RemoteException;
    void koopProduct(int idProduct, int idKlant) throws SQLException, RemoteException;
}
