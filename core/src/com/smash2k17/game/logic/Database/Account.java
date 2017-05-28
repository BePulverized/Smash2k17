package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.StoreItem;
import com.smash2k17.game.logic.World;
import com.smash2k17.game.logic.WorldData;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Account {

    private int id;
    private String email;
    private String password;
    private double balance;
    private ArrayList<StoreItem> inventory;


    public Account(int doid, String email, double balance) throws RemoteException {
        super();
        this.id = id;
        this.email = email;
        this.balance = balance;
        inventory = new ArrayList<StoreItem>();

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




}
