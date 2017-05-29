package com.smash2k17.game.logic.Database;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Stef on 18-4-2017.
 */
public class AccountRepository {

    private IAccount context;

    public AccountRepository(IAccount context)
    {
        this.context = context;
    }

    public Account logIn(String email, String password) throws SQLException, NoSuchAlgorithmException, RemoteException, UnsupportedEncodingException {
        return context.logIn(email, password);
    }

    public void addUser(String email, String password, double balance) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        context.addUser(email, password, balance);
    }

}




