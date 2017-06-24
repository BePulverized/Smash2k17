package com.smash2k17.game.logic.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.RMI.ServerConnection;
import com.smash2k17.game.logic.World;
import com.smash2k17.game.logic.WorldData;
import com.sun.security.ntlm.Server;

import java.rmi.RemoteException;

/**
 * Created by Martien on 10-Apr-17.
 */
public class MainMenuScreen implements Screen {
    private ServerConnection conn;
    private World game;
    private SpriteBatch sb;
    private Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;

    private Sprite background;
    private TextureAtlas atlas;
    private Skin skin;
    private Account activeAccount;

    public MainMenuScreen(World w, ServerConnection conn, Account activeAccount) {
        this.game = w;
        this.conn = conn;
        this.activeAccount = activeAccount;
        atlas = new TextureAtlas("core\\assets\\uiskin\\uiskin.atlas");
        skin = new Skin(Gdx.files.internal("core\\assets\\uiskin\\uiskin.json"),atlas);
        background = new Sprite(new Texture("core\\assets\\menubackground.jpg"));
        sb =  new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, sb);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table header = new Table(skin);
        header.setFillParent(true);
        header.setBackground(new SpriteDrawable(background));
        header.top();
        header.center();

        Table main = new Table(skin);
        main.top();
        main.center();
        TextButton playBtn = new TextButton("Play", skin);
        TextButton leaderboardBtn = new TextButton("Leaderboard",skin);
        TextButton shopBtn = new TextButton("Shop",skin);
        TextButton lobbyBtn = new TextButton("Lobby", skin);
        TextButton optionsBtn = new TextButton("Options",skin);
        TextButton logoutBtn = new TextButton("Logout",skin);
        TextButton exitBtn = new TextButton("Exit",skin);

        Label head = new Label("SMASH2K17", skin);

        playBtn.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               Gdx.graphics.setWindowedMode(980,500);
               game.setScreen(new Map(game, new WorldData("test"), activeAccount, conn));
           }
        });
        logoutBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                try {
                    game.setScreen(new LoginScreen(game));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        shopBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ShopScreen(game,conn, activeAccount));
            }
        });
        lobbyBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new LobbyScreen(game, conn, activeAccount));
            }
        });
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.exit(0);
            }
        });
//        main.row().fillY();
//        main.row().fillX();
        header.add(head);
        header.row();
        main.row().left();
        main.add(playBtn).width(leaderboardBtn.getWidth()).pad(10,10,10,10);
        main.add(leaderboardBtn).pad(10,10,10,10);
        main.add(lobbyBtn).pad(10,10,10,10);
        main.row();
        main.add(shopBtn).width(leaderboardBtn.getWidth()).pad(10,10,10,10);
        main.add(optionsBtn).width(leaderboardBtn.getWidth()).pad(10,10,10,10);
        main.row();
        main.add(logoutBtn).width(leaderboardBtn.getWidth()).pad(10,10,10,10);
        main.add(exitBtn).width(leaderboardBtn.getWidth()).pad(10,10,10,10);
        header.row();
        header.add(main);
        stage.addActor(header);
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
