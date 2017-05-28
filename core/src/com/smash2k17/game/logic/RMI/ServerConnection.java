package com.smash2k17.game.logic.RMI;

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
 * Created by BePul on 28-5-2017.
 */
public class ServerConnection extends UnicastRemoteObject implements IRemotePropertyListener {

    private ArrayList<WorldData> avWorlds;
    private static IRemotePublisherForListener publisher;

    public ServerConnection() throws RemoteException {
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        System.out.println("Update");
        switch(evt.getPropertyName())
        {
            case "worlddata":
                avWorlds = (ArrayList<WorldData>) evt.getNewValue();
            break;
            case "playermovement":

        }

        System.out.println(avWorlds.get(0).toString());
    }

    public ArrayList<WorldData> getAvWorlds(){ return avWorlds;}
}
