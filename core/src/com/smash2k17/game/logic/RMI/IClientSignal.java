package com.smash2k17.game.logic.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by BePul on 28-5-2017.
 */
public interface IClientSignal extends Remote {
    void signal(Object observable, Object updateMsg) throws RemoteException;

}
