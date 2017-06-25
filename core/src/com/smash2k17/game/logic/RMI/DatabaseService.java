package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Database.AccountContext;
import com.smash2k17.game.logic.Database.AccountRepository;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Martien on 25-Jun-17.
 */


public class DatabaseService extends UnicastRemoteObject implements IDatabaseService{
    private AccountRepository ar;

    public DatabaseService() throws RemoteException {
        ar =  new AccountRepository(new AccountContext());
    }

    @Override
    public Account login(String username, String password) throws RemoteException {
        try {
            return ar.logIn(username, password);
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
