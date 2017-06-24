package com.smash2k17.game.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Menus.MainMenuScreen;
import com.smash2k17.game.logic.RMI.ServerConnection;

import java.rmi.RemoteException;

/**
 * Created by BePulverized on 17-4-2017.
 */
public class GameOver implements Screen {

    private ServerConnection conn;
    private Viewport viewport;
    private Stage stage;
    private World game;
    private Account activeAccount;
    private WorldData worldData;
    public GameOver(World game, WorldData worldData, Account activeAccount, ServerConnection conn){
        this.game = game;
        this.worldData = worldData;
        this.activeAccount =activeAccount;
        this.conn = conn;
        viewport = new FitViewport(600, 300, new OrthographicCamera());
        stage = new Stage(viewport, ((World) game).batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("YOU DIED!", font);
        table.add(gameOverLabel).expandX();
        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game, conn, activeAccount));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

