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
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.World;

/**
 * Created by Martien on 10-Apr-17.
 */
public class MainMenuScreen implements Screen {
    private World game;
    private SpriteBatch sb;
    private Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;

    private Sprite background;
    private TextureAtlas atlas;
    private Skin skin;
    private TextButtonStyle textButtonStyle;

    public MainMenuScreen(World w) {
        this.game = w;

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
        main.row().padLeft(10); main.row().padRight(10);

        TextButton playBtn = new TextButton("Play", skin);
        TextButton leaderboardBtn = new TextButton("Leaderboard",skin);
        TextButton shopBtn = new TextButton("Shop",skin);
        TextButton logoutBtn = new TextButton("Logout",skin);
        TextButton exitBtn = new TextButton("Exit",skin);

        Label head = new Label("SMASH2K17", skin);

        playBtn.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               game.setScreen(new Map(game));
           }
        });
        logoutBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new LoginScreen(game));
            }
        });
        shopBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ShopScreen(game));
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
        main.add(playBtn);
        main.add(leaderboardBtn); main.row();
        main.add(shopBtn);
        main.add(logoutBtn);
        main.add(exitBtn);
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
