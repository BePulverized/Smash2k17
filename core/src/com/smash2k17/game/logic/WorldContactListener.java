package com.smash2k17.game.logic;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by BePulverized on 17-4-2017.
 */
public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch(cDef)
        {
            case WorldData.ITEM_BIT | WorldData.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == WorldData.ITEM_BIT){
                    ((ItemDrop)fixA.getUserData()).use((Player) fixB.getUserData());
                }
                else{
                    ((ItemDrop)fixB.getUserData()).use((Player) fixA.getUserData());
                }
                break;
            case WorldData.ENEMY_BIT | WorldData.PLAYER_BIT:
                System.out.println("Enemy player touch");
                if(fixA.getFilterData().categoryBits == WorldData.ENEMY_BIT){
                    Enemy e = (Enemy)fixB.getUserData();
                    Player p = (Player) fixA.getUserData();
                    p.setTouchEnemy(e);
                }
                else{
                    Enemy e = (Enemy)fixA.getUserData();
                    Player p = (Player) fixB.getUserData();
                    p.setTouchEnemy(e);
                }
                break;

        }

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch(cDef)
        {
            case WorldData.ENEMY_BIT | WorldData.PLAYER_BIT:
                System.out.println("Enemy player touch end");
                if(fixA.getFilterData().categoryBits == WorldData.ENEMY_BIT){
                    Enemy e = (Enemy)fixB.getUserData();
                    Player p = (Player) fixA.getUserData();
                    p.setTouchEnemy(null);
                }
                else{
                    Enemy e = (Enemy)fixA.getUserData();
                    Player p = (Player) fixB.getUserData();
                    p.setTouchEnemy(e);
                }
                break;

        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
