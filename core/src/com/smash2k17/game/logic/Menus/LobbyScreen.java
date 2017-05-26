package com.smash2k17.game.logic.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Database.AccountContext;
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.World;

import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * Created by Martien on 17-Apr-17.
 */
public class LobbyScreen implements Screen {
    private World game;
    private SpriteBatch sb;
    private Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;

    private TextureAtlas atlas;
    private Skin skin;
    private TextButton.TextButtonStyle textButtonStyle;
    private AccountContext ctxt;
    private ScrollPane scrollPane;
    private List<String> list;
    private Account activeAccount;
    public LobbyScreen(World w, Account activeAccount) {
        this.game = w;
        this.activeAccount = activeAccount;
        ctxt = new AccountContext();
        atlas = new TextureAtlas("core\\assets\\uiskin\\uiskin.atlas");
        skin = new Skin(Gdx.files.internal("core\\assets\\uiskin\\uiskin.json"), atlas);

        sb = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, sb);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table main = new Table(skin);
        main.setFillParent(true);


        TextButton joinBtn = new TextButton("Login", skin);
        list = new List<String>(skin);

        list.setItems(activeAccount.getAvWorlds().toString());
        scrollPane = new ScrollPane(list);
        scrollPane.setBounds(0, 0, 200, 500 + 100);
        scrollPane.setSmoothScrolling(false);
        scrollPane.setPosition(200 / 2 - scrollPane.getWidth() / 4,
                500 / 2 - scrollPane.getHeight() / 4);
        scrollPane.setTransform(true);
        scrollPane.setScale(0.5f);

        joinBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){


            }
        });

        main.row();
        stage.addActor(main);
        stage.addActor(scrollPane);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        System.out.println(width + ", " + height);
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
        skin.dispose();
        atlas.dispose();
    }
}