package com.smash2k17.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by BePulverized on 17-4-2017.
 */
public class UserInterface {
    public Stage stage;
    private Viewport port;
    private static Label health;
    private Label strength;

    public UserInterface(SpriteBatch sprite){

        port = new FitViewport(600, 300, new OrthographicCamera());
        stage = new Stage(port, sprite);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        health = new Label("Health: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(health).expandX().padTop(10);
        stage.addActor(table);
    }

    public static void updateInfo(Player player)
    {
        System.out.println(player.getHealth());
        health.setText("Health: " + Integer.toString(player.getHealth()));
    }
}
