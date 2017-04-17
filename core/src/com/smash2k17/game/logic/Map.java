package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Map implements Screen{

    private ArrayList<Point> deathZones;
    private ArrayList<ItemDrop> itemDrops;
    private ArrayList<Entity> players;
    private Texture img;
    private String name;
    private GameMode gameMode;

    //Game
    private World game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private TextureAtlas atlas;




    //Box2d
    private com.badlogic.gdx.physics.box2d.World worldlib;
    private Box2DDebugRenderer b2dr;

    //Sprites
    private Player player;

    public Map(World world)
    {
        this.name = name;
        players = new ArrayList<Entity>();
        this.gameMode = GameMode.TDM;
        atlas = new TextureAtlas("core\\assets\\PLAYER.pack");
        this.game = world;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(game.getGridWidth() / game.getPPM(), game.getGridHeight() / game.getPPM(), gameCam);
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(Gdx.files.internal("core\\assets\\Map_1.tmx").file().getAbsolutePath());
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / game.getPPM());
        gameCam.position.set(gamePort.getWorldWidth() /2, gamePort.getWorldHeight() /2, 0);
        worldlib = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / game.getPPM(), (rect.getY() + rect.getHeight() /2) / game.getPPM());

            body = worldlib.createBody(bdef);

            shape.setAsBox(rect.getWidth() /2 / game.getPPM(), rect.getHeight() /2 / game.getPPM());
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        player = new Player(this);

    }

    public String getName()
    {
        return name;
    }

    public GameMode getGameMode()
    {
        return gameMode;
    }

    public ArrayList<Point> getDeathzones()
    {
        return  deathZones;
    }

    public boolean dropItem()
    {
        return false;
    }

    public void end()
    {
        throw new NotImplementedException();
    }

    public ArrayList<Entity> getEntitys(){return players;}

    public TextureAtlas getTextureAtlas()
    {
        return atlas;
    }

    public void update(float dt)
    {
        player.handleInput(dt);
        worldlib.step(1/60f, 6, 2);
        player.update(dt);
        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(worldlib, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }


    public com.badlogic.gdx.physics.box2d.World getWorld() {
        return worldlib;
    }
}
