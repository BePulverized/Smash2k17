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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.World;

import java.util.ArrayList;

/**
 * Created by Martien on 17-Apr-17.
 */
public class ShopScreen implements Screen{
    private World game;
    private SpriteBatch sb;
    private Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;

    private TextureAtlas atlas;
    private Skin skin;
    private TextButton.TextButtonStyle textButtonStyle;

    public ShopScreen(World w) {
        this.game = w;

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
        ArrayList<ImageButton> items = new ArrayList<ImageButton>();

        // database code om alle items in de lijst te krijgen
        items.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture("core\\assets\\badlogic.jpg")))));
        items.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture("core\\assets\\menubackground.jpg")))));
        items.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture("core\\assets\\badlogic.jpg")))));
        items.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture("core\\assets\\menubackground.jpg")))));

        main.add(new Label("WELCOME TO THE ITEM SHOP!", skin));
        main.row();

        int count = 0;
        for(ImageButton ib : items){
            main.add(ib).width(100).height(100);
            count++;
            if(count >= 3) {
                count = 0;
                main.row();
            }
        }
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
