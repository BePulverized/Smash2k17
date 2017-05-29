package com.smash2k17.game.desktop;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
		import com.smash2k17.game.logic.World;

		import java.rmi.RemoteException;

public class DesktopLauncher {
	public static void main (String[] arg) throws RemoteException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new World(), config);
	}
}
