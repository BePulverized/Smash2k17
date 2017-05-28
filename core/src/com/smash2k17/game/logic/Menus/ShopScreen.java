package com.smash2k17.game.logic.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.RMI.ServerConnection;
import com.smash2k17.game.logic.World;
import com.sun.security.ntlm.Server;

import java.util.ArrayList;
import java.util.Random;

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
    private ServerConnection conn;

    public ShopScreen(World w, ServerConnection conn) {
        this.game = w;
        this.conn = conn;
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
        Table row1 = new Table(skin);
        Table row2 = new Table(skin);
        Table row3 = new Table(skin);
        main.setFillParent(true);

        ArrayList<ImageButton> items = new ArrayList<ImageButton>();
        for(int i = 0; i < 6; i++){
            ImageButton s = new ImageButton(new SpriteDrawable(new Sprite(new Texture("core\\assets\\kirbyshop.png"))));
            s.getImageCell().expand().fill();
            items.add(s);
        }

        TextButton tb = new TextButton("Back", skin);
        tb.right();
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenuScreen(game, conn));
            }
        });
        main.add("");
        main.add(new Label("WELCOME TO THE KIRBY SHOP!", skin));
        main.add(tb).right();
        main.row();

        int count = 1;
        Random rnd = new Random();
        for(ImageButton ib : items){
            switch(count){
                case 1: ib.getImage().setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1);
                        row1.add(ib).width(100).height(100);
                        row1.row();
                        row1.add(new Label("Kirby", skin));
                        row1.row();
                    break;
                case 2: ib.getImage().setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1);
                        row2.add(ib).width(100).height(100);
                        row2.row();
                        row2.add(new Label("Kirby", skin));
                        row2.row();
                    break;
                case 3: ib.getImage().setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1);
                        row3.add(ib).width(100).height(100);
                        row3.row();
                        row3.add(new Label("Kirby", skin));
                        row3.row();
                    break;
                default:
                    System.out.println("adding buttons messed up");
            }
            count++;
            if(count > 3) {
                count = 1;
            }
        }
        row1.top();
        row2.top();
        row3.top();
        main.add(row1);
        main.add(row2);
        main.add(row3);
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
