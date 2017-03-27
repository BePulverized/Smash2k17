package com.smash2k17.game.logic;

import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Account {

    private String email;
    private String userName;
    private String password;
    private double balance;
    private ArrayList<StoreItem> inventory;

    public Account(String email, String userName, String password)
    {
        this.email = email;
        this.userName = userName;
        this.password = password;
        balance = 0;
        inventory = new ArrayList<StoreItem>();
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
