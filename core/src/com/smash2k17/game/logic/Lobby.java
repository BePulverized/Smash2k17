package com.smash2k17.game.logic;

import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Database.Store;
import fontyspublisher.RemotePublisher;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by BePul on 27-3-2017.
 */
//Serverhost
public class Lobby {

    private Store store;
    private Account activeAccount;
    private ArrayList<WorldData> worlds = new ArrayList<WorldData>();
    private RemotePublisher publisher;
    private int count = 0;
    public Lobby()
    {
        try{
            publisher = new RemotePublisher();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        Registry reg;
        try{
            reg = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        try{
            reg.rebind("publisher", publisher);
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        try{
            publisher.registerProperty("worlddata");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        store = new Store();
    }



    public void UpdateWorlds() throws RemoteException
    {

        if(worlds.size() < 3)
        {
            worlds.add(new WorldData("World " + Integer.toString(count)));
            count++;
        }
        try{
            publisher.inform("worlddata", null, worlds);
            System.out.println("Inform");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public boolean register(String eMail, String userName, String password)
    {
        throw new NotImplementedException();
    }

    public boolean createWorld()
    {
        throw new NotImplementedException();
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public static void main(String[] args)
    {
        Lobby lb = new Lobby();

        try{
            for(int i=0; i<10000;i++) {
                sleep(5000);
                System.out.println(i);
                lb.UpdateWorlds();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
