package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.smash2k17.game.logic.Map;
import com.smash2k17.game.logic.Player;
import javafx.scene.text.Text;

/**
 * Created by BePul on 3-4-2017.
 */
public class PlayScreen implements Screen {

    private Map map;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private TextureAtlas atlas;




    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    //Sprites
    private Player player;

    public PlayScreen(Map map)
    {
        atlas = new TextureAtlas("core\\assets\\player_runnning.pack");
        this.map = map;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(map.getGridWidth() / map.getPPM(), map.getGridHeight() / map.getPPM(), gameCam);
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(Gdx.files.internal("core\\assets\\Map_1.tmx").file().getAbsolutePath());
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / map.getPPM());
        gameCam.position.set(gamePort.getWorldWidth() /2, gamePort.getWorldHeight() /2, 0);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / map.getPPM(), (rect.getY() + rect.getHeight() /2) / map.getPPM());

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() /2 / map.getPPM(), rect.getHeight() /2 / map.getPPM());
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        player = new Player(world, this);


    }

    public TextureAtlas getTextureAtlas()
    {
        return atlas;
    }

    public void update(float dt)
    {
        handleInput(dt);
        world.step(1/60f, 6, 2);
        player.update(dt);
        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }

    private void handleInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

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
        b2dr.render(world, gameCam.combined);
        map.batch.setProjectionMatrix(gameCam.combined);
        map.batch.begin();
        player.draw(map.batch);
        map.batch.end();
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
}
