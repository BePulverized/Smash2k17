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
        setRegion(map.getTextureAtlas().findRegion("PLAYER"), 88, 0, 24,23);
        velocity = new Vector2(0,0);

    }



    @Override
    public void use(Player player) {
        destroy();
        player.addHealth(10);
        UserInterface.updateInfo(player);
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth() /2, body.getPosition().y - getHeight() /2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}
