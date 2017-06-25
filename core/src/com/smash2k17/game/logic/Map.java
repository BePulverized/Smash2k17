package com.smash2k17.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Database.Account;
import com.smash2k17.game.logic.Menus.LoginScreen;
import com.smash2k17.game.logic.RMI.ServerConnection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by BePul on 27-3-2017.
 */
public class Map implements Screen{

    private ArrayList<Point> deathZones;
    private ArrayList<ItemDrop> itemDrops;
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
    private boolean joined;
    // Interface
    private UserInterface ui;

    //Box2d
    private com.badlogic.gdx.physics.box2d.World worldlib;
    // Build
   // private Box2DBuild b2dr;
    //Debug
    private Box2DDebugRenderer b2dr;

    //items
    private Player player;
    private Enemy enemy;
    private Array<ItemDrop> items;
    private ArrayList<Enemy> enemies;
    private ArrayList<ItemDef> itemsToSpawn;
    private WorldData worldData;
    private Account activeAccount;
    private ServerConnection conn;

    public Map(World world, WorldData worldData, Account activeAccount, ServerConnection conn)
    {
        this.worldData = worldData;
        this.name = name;
        this.activeAccount =activeAccount;
        this.conn = conn;
        this.gameMode = GameMode.TDM;
        this.joined = false;
        atlas = new TextureAtlas("core\\assets\\PLAYER.pack");
        this.game = world;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(game.getGridWidth() / WorldData.PPM, game.getGridHeight() / WorldData.PPM, gameCam);
        ui = new UserInterface(new SpriteBatch());
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(Gdx.files.internal("core\\assets\\Map_1.tmx").file().getAbsolutePath());
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / WorldData.PPM);
        gameCam.position.set(gamePort.getWorldWidth() /2, gamePort.getWorldHeight() /2, 0);
        worldlib = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -10), true);
        //build
 //       b2dr = new Box2DBuild();
        //debug
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / WorldData.PPM, (rect.getY() + rect.getHeight() /2) / WorldData.PPM);
            body = worldlib.createBody(bdef);
            shape.setAsBox(rect.getWidth() /2 / WorldData.PPM, rect.getHeight() /2 / WorldData.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        player = new Player(this, activeAccount.getId());
        enemies = new ArrayList<>();

//        try {
//            LoginScreen.conn.newPlayer(player.getData(), 0);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        worldlib.setContactListener(new WorldContactListener());
        items = new Array<>();
        itemsToSpawn = new ArrayList<>();
        UserInterface.updateInfo(player);
    }

    public void handleSpawningItems() throws RemoteException {
        itemsToSpawn = conn.getItems(player.getData());
        if(itemsToSpawn.size() > 0)
        {
            ItemDef idef = itemsToSpawn.get(itemsToSpawn.size() - 1);
            if(idef.type.equals("Powerup"))
            {
                items.add(new PowerUp(this, idef.position.x, idef.position.y, conn, player.getData()));
            }
            if(idef.type.equals("Debuff"))
            {
                items.add(new Debuff(this, idef.position.x, idef.position.y, conn, player.getData()));
            }
            itemsToSpawn.remove(itemsToSpawn.size() -1);
        }
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

    public void pickUpItem(ItemDrop item){

        items.removeValue(item, true);}

    public void end()
    {
        throw new NotImplementedException();
    }


    public TextureAtlas getTextureAtlas()
    {
        return atlas;
    }

    public void update(float dt) throws RemoteException {
        WorldData incomingData = conn.getPlayerWorld();
        player.handleInput(dt);
        handleSpawningItems();
        if(incomingData != null) {
            for (EntityData ent : incomingData.getPlayers()) {
                if(!checkIfEnemyExists(ent)) {
                    if(ent.getID() != activeAccount.getId()) {
                        enemies.add(new Enemy(this, ent.getX(), ent.getY(), ent.getState(), ent.getRight(), ent.getDelta(), ent.getID()));
                    }
                }
            }

            for(EntityData ent : incomingData.getPlayers())
            {
                for(Enemy enemy: enemies)
                {
                    if(enemy.getId() == ent.getID())
                    {
                        enemy.setX(ent.getX());
                        enemy.setY(ent.getY());
                        enemy.setState(ent.getState());
                        enemy.setRight(ent.getRight());
                        enemy.update(ent.getDelta());
                    }
                }
            }
        }
        //getworlddata
        worldlib.step(1/60f, 6, 2);

        player.update(dt);
        if(joined == false)
        {
            conn.newPlayer(player.getData(), 0);
            joined = true;
        }
        if(player.currentState != Player.State.DEAD) {
            conn.playerLeave(player.getData());
            gameCam.position.x = player.b2body.getPosition().x;
        }
        gameCam.update();
        renderer.setView(gameCam);
        for(ItemDrop item : items)
        {
            item.update(dt);
        }


    }

    public boolean checkIfEnemyExists(EntityData ent)
    {
        int count = 0;
        for(Enemy enemy: enemies)
        {
            if(enemy.getId() == ent.getID())
            {
                count++;
            }
        }
        if(count > 0)
        {
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        try {
            update(delta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(worldlib, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for(Enemy entity : enemies)
        {
                entity.draw(game.batch);
        }
        for(ItemDrop item : items)
        {
            item.draw(game.batch);
        }
        game.batch.end();
        game.batch.setProjectionMatrix(ui.stage.getCamera().combined);
        ui.stage.draw();
        if(player.currentState == Player.State.DEAD)
            game.setScreen(new GameOver(game, worldData, activeAccount, conn));
            dispose();
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
