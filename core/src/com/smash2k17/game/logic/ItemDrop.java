package com.smash2k17.game.logic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.smash2k17.game.logic.RMI.ServerConnection;
import com.smash2k17.game.logic.interfaces.IPickable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class ItemDrop extends Sprite implements IPickable {
    private final EntityData data;
    protected Map map;
    protected com.badlogic.gdx.physics.box2d.World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;
    private Point position;
    private String name;
    private Effect effect;
    private ServerConnection conn;
    public ItemDrop(Map map, float x, float y, ServerConnection conn, EntityData data)
    {
        this.map = map;
        this.world = map.getWorld();
        setPosition(x, y);
        this.conn = conn;
        this.data = data;
        setBounds(getX(), getY(), 16 / WorldData.PPM, 16/ WorldData.PPM);
        defineItem();
        toDestroy = false;
        destroyed = false;
    }

    protected void defineItem(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/ WorldData.PPM);
        fdef.filter.categoryBits = WorldData.ITEM_BIT;
        fdef.filter.maskBits = WorldData.PLAYER_BIT | WorldData.OBJECT_BIT | WorldData.GROUND_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }
    public abstract void use(Player player) throws RemoteException;

    public void update(float dt){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
            map.pickUpItem(this);
        }
    }

    public void draw(Batch batch){
        if(!destroyed)
            super.draw(batch);
    }

    public void destroy() throws RemoteException {
        toDestroy = true;
        conn.destroyItem(data, this.getX(), this.getY());
    }

    @Override
    public void Spawn() {
        throw new NotImplementedException();
    }
}
