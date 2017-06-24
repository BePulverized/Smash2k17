package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.*;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Stef on 21-6-2017.
 */
public class DatabaseService extends UnicastRemoteObject implements IDatabaseService{
    private AccountRepository accountRepo = new AccountRepository(new AccountContext());
    private StoreRepository storeRepo = new StoreRepository(new StoreContext());

    public Account login(String username, String password) throws RemoteException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException {
        Account user = accountRepo.logIn(username,password);
        return user;
    }

    public DatabaseService() throws RemoteException
    {

    }

    public void koopProduct(int idProduct, int idKlant) throws SQLException, RemoteException
    {
        storeRepo.koopProduct(idProduct, idKlant);
    }




}
