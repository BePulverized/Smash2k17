package com.smash2k17.game.logic;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.smash2k17.game.logic.World;

public class MakeShiftLauncher {
    public MakeShiftLauncher() {
    }

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new World(), config);
    }
}