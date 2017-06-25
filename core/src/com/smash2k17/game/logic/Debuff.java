package com.smash2k17.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.smash2k17.game.logic.RMI.ServerConnection;

import java.rmi.RemoteException;

/**
 * Created by BePulverized on 17-4-2017.
 */
public class Debuff extends ItemDrop {

    public Debuff(Map map, float x, float y, ServerConnection conn, EntityData data) {
        super(map, x, y, conn, data);
        setRegion(map.getTextureAtlas().findRegion("PLAYER"), 0, 0, 24,23);
        velocity = new Vector2(0,0);

    }

    @Override
    public void use(Player player) throws RemoteException {
        destroy();
        player.loseHealth(10);
        UserInterface.updateInfo(player);
    }

    @Override
    public void use(Enemy enemy) throws RemoteException {
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
