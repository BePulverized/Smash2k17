package com.smash2k17.game.logic.Database;
import com.smash2k17.game.logic.StoreItem;
import com.smash2k17.game.logic.WorldData;

import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Account{

    private int id;
    private String email;
    private String password;
    private double balance;
    private ArrayList<StoreItem> inventory;
    private ArrayList<WorldData> avWorlds;


    public Account(int id, String email, double balance) {
        this.id = id;
        this.email = email;
        this.balance = balance;
        inventory = new ArrayList<StoreItem>();//
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<WorldData> getAvWorlds(){ return avWorlds;}

    public int getId() {
        return id;
    }
}
