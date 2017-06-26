package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Database.*;
import com.smash2k17.game.logic.WorldData;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Martien on 25-Jun-17.
 */


public class DatabaseService implements IDatabaseService{
    private AccountRepository ar;
    private StoreRepository sr;

    public DatabaseService() throws RemoteException {
        Registry rmiRegistry = LocateRegistry.createRegistry(1099);
        IDatabaseService databaseService = (IDatabaseService)UnicastRemoteObject.exportObject(this, 1099);
        rmiRegistry.rebind("databaseService",databaseService);
        ar =  new AccountRepository(new AccountContext());
        sr = new StoreRepository(new StoreContext());
    }

    @Override
    public Account login(String username, String password) throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return ar.logIn(username, password);
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void koopProduct(int idProduct, int idKlant) throws SQLException, RemoteException
    {
        sr.koopProduct(idProduct, idKlant);
    }

    public static void main(String[] args) throws RemoteException {
        DatabaseService service = new DatabaseService();

    }


}
