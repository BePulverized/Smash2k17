package com.smash2k17.game.logic.Database;

import java.sql.SQLException;

/**
 * Created by Stef on 18-4-2017.
 */
public interface IAccount {

    public Account logIn(String email, String password) throws SQLException;

    public void addUser(String email, String password, double balance) throws SQLException;
}
