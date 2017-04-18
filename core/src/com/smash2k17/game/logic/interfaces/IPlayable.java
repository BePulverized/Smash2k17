package com.smash2k17.game.logic.interfaces;

import java.awt.event.KeyEvent;

/**
 * Created by BePul on 27-3-2017.
 */
public interface IPlayable {

    void Move(KeyEvent e);
    void Jump();
    void Attack();
    void Respawn();

}
