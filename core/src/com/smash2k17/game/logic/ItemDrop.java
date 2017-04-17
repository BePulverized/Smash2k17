package com.smash2k17.game.logic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.smash2k17.game.logic.interfaces.IPickable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class ItemDrop extends Sprite implements IPickable {
    protected Map map;
    protected com.badlogic.gdx.physics.box2d.World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;
    private Point position;
    private String name;
    private Effect effect;

    public ItemDrop(Map map, float x, float y)
    {
        this.map = map;
        this.world = map.getWorld();
        setPosition(x, y);
        setBounds(getX(), getY(), 16 / World.PPM, 16/ World.PPM);
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
        shape.setRadius(6/ World.PPM);
        fdef.filter.categoryBits = World.ITEM_BIT;
        fdef.filter.maskBits = World.PLAYER_BIT | World.OBJECT_BIT | World.GROUND_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }
    public abstract void use(Player player);

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

    public void destroy() {
        toDestroy = true;

    }

    @Override
    public void Spawn() {
        throw new NotImplementedException();
    }
}
