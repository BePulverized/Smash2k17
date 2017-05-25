package com.smash2k17.game.logic.RMI;

import com.smash2k17.game.logic.Entity;

import java.rmi.Remote;

/**
 * Created by BePul on 15-5-2017.
 */
public interface IPlayer extends Remote {
    Entity getPlayer();
}
