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
public class Account extends UnicastRemoteObject implements IRemotePropertyListener {

    private int id;
    private String email;
    private String password;
    private double balance;
    private ArrayList<StoreItem> inventory;
    private ArrayList<WorldData> avWorlds;
    private static IRemotePublisherForListener publisher;

    public Account(int doid, String email, double balance) throws RemoteException {
        super();
        this.id = id;
        this.email = email;
        this.balance = balance;
        inventory = new ArrayList<StoreItem>();
        try{
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            System.out.println("Reg created");
            publisher = (IRemotePublisherForListener) reg.lookup("publisher");

        } catch (NotBoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            publisher.subscribeRemoteListener(this, "worlddata");
        }
        catch(RemoteException ex)
        {
            ex.printStackTrace();
        }
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        System.out.println("Update");
        avWorlds = (ArrayList<WorldData>) evt.getNewValue();
        System.out.println(avWorlds.get(0).toString());

    }
}
