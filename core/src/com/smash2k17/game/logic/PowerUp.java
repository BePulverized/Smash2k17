package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by BePulverized on 11-4-2017.
 */
public class PowerUp extends ItemDrop {
    public PowerUp(Map map, float x, float y) {
        super(map, x, y);
        setRegion(map.getTextureAtlas().findRegion("Illusion_Walk"), 0, 0, 22,22);
        velocity = new Vector2(0.1f,0);

    }

    @Override
    protected void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/ World.PPM);
        fdef.filter.categoryBits = World.ITEM_BIT;
        fdef.filter.maskBits = World.PLAYER_BIT | World.OBJECT_BIT | World.GROUND_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Player player) {
        destroy();
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth() /2, body.getPosition().y - getHeight() /2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}
