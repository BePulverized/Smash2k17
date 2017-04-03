package com.smash2k17.game.logic.interfaces;

import java.awt.event.KeyEvent;

/**
 * Created by BePul on 27-3-2017.
 */
public interface IPlayable {

    public void Move(KeyEvent e);
    public void Jump();
    public void Attack();
    public void Respawn();

}
