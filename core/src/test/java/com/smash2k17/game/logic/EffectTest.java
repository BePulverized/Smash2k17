package com.smash2k17.game.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martien on 29-May-17.
 */
public class EffectTest {
    private Effect effect;

    @Test
    public void healthTest(){
        effect = Effect.Health;
    }

    @Test
    public void strengthTest(){
        effect = Effect.Strength;
    }

    @Test
    public void armorTest(){
        effect = Effect.Armor;
    }

    @Test
    public void livesTest(){
        effect = Effect.Lives;
    }
}