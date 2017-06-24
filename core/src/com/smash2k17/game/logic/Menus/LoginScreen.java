package com.smash2k17.game.logic.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Database.AccountContext;
import com.smash2k17.game.logic.Database.AccountRepository;
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.RMI.ServerConnection;
import com.smash2k17.game.logic.World;

import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Martien on 17-Apr-17.
 */
public class LoginScreen implements Screen {
    private World game;
    private SpriteBatch sb;
    private Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;

    private TextureAtlas atlas;
    private Skin skin;
    private TextButton.TextButtonStyle textButtonStyle;
    private Account activeAccount;
    public static ServerConnection conn;
    private static Account user;
    private AccountRepository accountRepo = new AccountRepository(new AccountContext());

    public LoginScreen(World w) throws RemoteException {
        this.game = w;
        atlas = new TextureAtlas("core\\assets\\uiskin\\uiskin.atlas");
        skin = new Skin(Gdx.files.internal("core\\assets\\uiskin\\uiskin.json"), atlas);
        conn = new ServerConnection();
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

        final TextField nameField = new TextField("",skin);
        final TextField passField = new TextField("", skin);
        passField.setPasswordMode(true);
        passField.setPasswordCharacter("*".charAt(0));
        TextButton loginBtn = new TextButton("Login", skin);
        TextButton exitBtn = new TextButton("Exit", skin);

        loginBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                try {
                    conn = new ServerConnection();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                /**try {

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }**/

                Random rnd = new Random();

                user = new Account(rnd.nextInt(10), "lol", 0);
                game.setScreen(new MainMenuScreen(game, conn, user));



            }
        });
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.exit(0);
            }
        });

        main.add(nameField);
        main.add(loginBtn).center();
        main.row();
        main.add(passField);
        main.add(exitBtn).width(loginBtn.getWidth()).center();
        main.row();
        stage.addActor(main);
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