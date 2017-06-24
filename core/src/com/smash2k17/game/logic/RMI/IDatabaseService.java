package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.Account;

import java.io.UnsupportedEncodingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Stef on 21-6-2017.
 */
public interface IDatabaseService extends Remote {

    Account login(String username, String password) throws RemoteException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException;
    void koopProduct(int idProduct, int idKlant) throws SQLException, RemoteException;
}
